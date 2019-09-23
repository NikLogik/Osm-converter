package ru.niklogik;

import ru.niklogik.io.AttractionObjectWriter;
import ru.niklogik.io.FacilitiesCSVWriter;
import ru.niklogik.io.FacilitiesXmlWriter;
import ru.niklogik.io.OsmFileReader;
import ru.niklogik.facility.AttractionObjectContainerImpl;
import ru.niklogik.osm.OsmData;
import ru.niklogik.osm.*;

import java.io.IOException;

public class FacilitiesOsmConverter {

    private static final String PREFIX = "src/main/resources/";
    private static final String INPUT_OSM = PREFIX + "map.osm";
    private static final String OUTPUT_NAME = PREFIX + "text";

    public static void main(String[] args) {
        run(INPUT_OSM, OUTPUT_NAME);
    }

    private static void run(String inputOsm, String outputCsv) {
        AllowedTagsFilter filter = new AllowedTagsFilter();
        Osm.Key.ATTR_KEYS.stream().forEach(value -> filter.add(Osm.ElementType.WAY, value, null));
        OsmData data = new OsmDataImpl(filter);
        new OsmFileReader(data).readFile(INPUT_OSM);

        new AttractionObjectContainerImpl(data).transformNodes();

        try {
            AttractionObjectWriter writer = new FacilitiesCSVWriter(data, OUTPUT_NAME);
            writer.write();
            writer = new FacilitiesXmlWriter(data, OUTPUT_NAME);
            writer.write();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

}
