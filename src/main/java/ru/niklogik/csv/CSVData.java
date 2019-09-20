package ru.niklogik.csv;

import org.matsim.api.core.v01.Id;
import ru.niklogik.osm.Osm;

import java.util.List;
import java.util.Map;

public interface CSVData {

    Map<Id<Osm.Node>, Osm.Node> getAllNodes();

    List<Osm.Node> getNodeByAttType(String att_type);

    boolean removeNodeById(Id<Osm.Node> id);

    boolean removeNodeByAttType(String att_type);
}
