package ru.niklogik.io;

import ru.niklogik.osm.Osm;
import ru.niklogik.osm.OsmData;

import java.io.BufferedWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Map;
import java.util.StringJoiner;

import static ru.niklogik.csv.FacilitiesCSVConstants.*;

public class FacilitiesCSVWriter extends AttractionObjectWriter {

    private String file_name;

    private static final String DELIMITTER = ",";

    private static final String HEADER = "lan,lon,type,name,capacity,visit_level,number_of_shifts,1_open,1_close,1_cap,2_open,2_close,2_cap,3_open,3_close,3_cap";

    public FacilitiesCSVWriter(OsmData data, String file_name) {
        super(data);
        this.file_name = file_name;
    }

    public void write() throws IOException {
    Path path = Paths.get(file_name + ".csv");
    BufferedWriter writer = Files.newBufferedWriter(path);
        writer.write(HEADER);
        writer.newLine();
        for (Osm.Node node : data.getNodes().values()) {
            Map<String, String> tags = node.getTags();
            if (tags.get(TYPE) != null) {
                StringJoiner joiner = new StringJoiner(DELIMITTER);
                double x = node.getCoord().getX();
                double y = node.getCoord().getY();
                joiner.add(String.valueOf(y));
                joiner.add(String.valueOf(x));
                joiner.add(tags.get(TYPE));
                joiner.add(tags.get(NAME));
                joiner.add(tags.get(CAPACITY));
                joiner.add(tags.get(VISIT_LEVEL));
                joiner.add(tags.get(NUMBER_OF_SHIFTS));
                joiner.add(tags.get(_1_OPEN));
                joiner.add(tags.get(_1_CLOSE));
                joiner.add(tags.get(_1_CAP));
                joiner.add(tags.get(_2_OPEN));
                joiner.add(tags.get(_2_CLOSE));
                joiner.add(tags.get(_2_CAP));
                joiner.add(tags.get(_3_OPEN));
                joiner.add(tags.get(_3_CLOSE));
                joiner.add(tags.get(_3_CAP));
                System.out.println("Write line " + joiner);
                writer.write(joiner.toString());
                writer.newLine();
            }
        }
    }
}
