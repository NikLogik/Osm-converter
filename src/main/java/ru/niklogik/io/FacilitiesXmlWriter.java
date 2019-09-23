package ru.niklogik.io;

import org.matsim.core.utils.collections.Tuple;
import ru.niklogik.csv.FacilitiesCSVConstants;
import ru.niklogik.osm.Osm;
import ru.niklogik.osm.OsmData;
import static ru.niklogik.xml.FacilitiesXMLConstants.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class FacilitiesXmlWriter extends AttractionObjectWriter{

    private static String POSTFIX = ".xml";

    private String file_name;

    public FacilitiesXmlWriter(final OsmData data, final String file_name) {
        super(data);
        this.file_name = file_name + POSTFIX;
    }

    public void write() {
        this.openFile(file_name);
            writeData();
    }

    private void writeData() {
        this.writeXmlHead();
        this.writeDoctype(FACILITIES, "http://www.matsim.org/files/dtd/facilities_v1.dtd");
        this.writeStartTag(FACILITIES, null);
        for (Osm.Node node : data.getNodes().values()){
            if (node.getTags().get(FacilitiesCSVConstants.TYPE) != null)
                writeFacility(node);
        }
        this.writeEndTag(FACILITIES);
        this.close();
    }

    private void writeFacility(Osm.Node node){
        List<Tuple<String, String >> attributes = new ArrayList<>();
        attributes.add(createTuple(ID, node.getId().toString()));
        attributes.add(createTuple(X, String.valueOf(node.getCoord().getX())));
        attributes.add(createTuple(Y, String.valueOf(node.getCoord().getY())));
        String name = node.getTags().get(FacilitiesCSVConstants.NAME);
        if (name != null)
            attributes.add(createTuple(DESCRIPTION, name));
        writeStartTag(FACILITY, attributes);

        Map<String, String> tags = node.getTags();
        writeActivity(tags);
        writeEndTag(FACILITY);
    }

    private void writeActivity(Map<String, String> tags) {
        List<Tuple<String, String >> attributes = new ArrayList<>();

        attributes.add(createTuple(TYPE, tags.get(FacilitiesCSVConstants.TYPE)));
        try {
            writeStartTag(ACTIVITY, attributes);

        }catch (NullPointerException e){
            System.out.println(tags);
        }

        String capacity = tags.get(FacilitiesCSVConstants.CAPACITY);
        if (capacity!= null){
            writeCapacity(tags);
        }
        String shifts = tags.get(FacilitiesCSVConstants.NUMBER_OF_SHIFTS);
        if (shifts != null){
            int times = (int) Double.parseDouble(shifts);
            for (int i = 1; i <= times; i++) {
                writeOpenTime(tags, i);
            }
        }
        writeEndTag(ACTIVITY);
    }

    private void writeCapacity(Map<String, String> tags){
        List<Tuple<String, String >> attributes = new ArrayList<>();
        attributes.add(createTuple(VALUE, tags.get(FacilitiesCSVConstants.CAPACITY)));
        writeStartTag(CAPACITY, attributes, true);
    }

    private void writeOpenTime(Map<String, String> tags, int i){
        List<Tuple<String, String >> attributes = new ArrayList<>();
        attributes.add(createTuple(DAY, WEEK));
        String open = tags.get(i + "_open");
        String close = tags.get(i + "_close");
        attributes.add(createTuple(START_TIME, open));
        attributes.add(createTuple(END_TIME, close));
        writeStartTag(OPEN_TIME, attributes, true);
    }
}
