package ru.niklogik.csv;

import org.matsim.api.core.v01.Id;
import ru.niklogik.facility.AttractionObjectContainer;
import ru.niklogik.facility.FacilityTableTypes;
import ru.niklogik.facility.CSVTagsValidator;
import ru.niklogik.io.OsmFileReader;
import ru.niklogik.osm.Osm;
import ru.niklogik.osm.OsmData;

import java.util.*;

@Deprecated
public class CSVDataImpl implements AttractionObjectContainer {

    private OsmData data;

    private List<Long> nodes_id = new ArrayList<>();

    public CSVDataImpl(OsmData data) {
        this.data = data;
        modifyNodesFromWays();
        clearUnusefullNodes();
        transformNodes();
    }

    public void transformNodes() {
        for (Osm.Node node : data.getNodes().values()){
            String osm_key = node.getTags().keySet().stream().filter(key -> Osm.Key.ATTR_KEYS.contains(key)).findFirst().get();
            String type = node.getTags().get(osm_key);
            AttractionType key = null;
            for (Map.Entry<AttractionType, List<String>> entries : AttractionType.TYPE_MAP.entrySet()){
                if (entries.getValue().contains(type)) {
                    key = entries.getKey();
                    node.getTags().put("type", key.toString());
                    break;
                }
            }
            if (key != null){
                CSVTagsValidator.addCSVTags(node, key);
            }
        }
    }

    public void modifyNodesFromWays(){
        for (OsmFileReader.ParsedWay way : data.getParsedWays().values()){
            long id = way.nodes.get(0);
            Id<Osm.Node> nodeId = Id.create(id, Osm.Node.class);
            Osm.Node node = data.getNodes().get(nodeId);
            node.setTags(way.getTags());
            nodes_id.add(id);
        }
    }

    private void clearUnusefullNodes() {
        Iterator<Map.Entry<Id<Osm.Node>, Osm.Node>> iterator = data.getNodes().entrySet().iterator();
        while (iterator.hasNext()){
            Map.Entry<Id<Osm.Node>, Osm.Node> next = iterator.next();
            if (!nodes_id.contains(next.getKey())){
                if (next.getValue().getTags().isEmpty()){
                    iterator.remove();
                }
            }
        }

        iterator = data.getNodes().entrySet().iterator();
        while (iterator.hasNext()){
            Map.Entry<Id<Osm.Node>, Osm.Node> next = iterator.next();
            Set<String> strings = next.getValue().getTags().keySet();
            if (strings.stream().noneMatch(s-> Osm.Key.ATTR_KEYS.contains(s))){
                iterator.remove();
            }
        }
    }

    public enum AttractionType{
        work,
        school,
        education,
        store,
        shopping;

        public static final Map<AttractionType, List<String>> TYPE_MAP = new HashMap<AttractionType, List<String>>() {{
            put(work, FacilityTableTypes.work_cat);
            put(school, FacilityTableTypes.school_cat);
            put(education, FacilityTableTypes.education_cat);
            put(store, FacilityTableTypes.store_cat);
            put(shopping, FacilityTableTypes.shopping_cat);
        }};
    }


    @Override
    public Map<Id<Osm.Node>, Osm.Node> getAllNodes() {
        return data.getNodes();
    }

    @Override
    public List<Osm.Node> getNodeByAttType(String att_type) {
        return null;
    }

    @Override
    public boolean removeNodeById(Id<Osm.Node> id) {
        return false;
    }

    @Override
    public boolean removeNodeByAttType(String att_type) {
        return false;
    }

}
