package ru.niklogik;

import org.matsim.api.core.v01.Id;
import ru.niklogik.csv.CSVDataImpl;
import ru.niklogik.io.AttractionObjectWriter;
import ru.niklogik.io.OsmFileReader;
import ru.niklogik.osm.*;

import java.io.IOException;

public class FacilitiesOsmToCsvConverter {

    private static final String PREFIX = "src/main/resources/";
    private static final String INPUT_OSM = PREFIX + "map.osm";
    private static final String OUTPUT_CSV = PREFIX + "text.csv";

    public static void main(String[] args) {
        run(INPUT_OSM, OUTPUT_CSV);
    }

    private static void run(String inputOsm, String outputCsv) {
        AllowedTagsFilter filter = new AllowedTagsFilter();
        Osm.Key.ATTR_KEYS.stream().forEach(value -> filter.add(Osm.ElementType.WAY, value, null));
        OsmData data = new OsmDataImpl(filter);
        new OsmFileReader(data).readFile(INPUT_OSM);
        new CSVDataImpl(data).transformNodes();

        try {
            new AttractionObjectWriter(OUTPUT_CSV, data).write();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

}
