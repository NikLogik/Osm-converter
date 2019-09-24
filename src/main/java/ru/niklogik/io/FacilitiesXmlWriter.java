package ru.niklogik.io;

import org.matsim.core.utils.collections.Tuple;
import ru.niklogik.csv.FacilitiesCSVConstants;
import ru.niklogik.osm.Osm;
import ru.niklogik.osm.OsmData;
import static ru.niklogik.xml.FacilitiesXMLConstants.*;

import java.time.LocalTime;
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
        if (tags.get(FacilitiesCSVConstants.NUMBER_OF_SHIFTS) != null)
            writeOpenTime(tags);
        writeEndTag(ACTIVITY);
    }

    private void writeCapacity(Map<String, String> tags){
        List<Tuple<String, String >> attributes = new ArrayList<>();
        attributes.add(createTuple(VALUE, tags.get(FacilitiesCSVConstants.CAPACITY)));
        writeStartTag(CAPACITY, attributes, true);
    }

    private void writeOpenTime(Map<String, String> tags){
        int count = (int) tags.keySet().stream().filter(key -> key.endsWith("open")).count();
        for (int i = 1; i <= count; i++){
            List<Tuple<String, String >> attributes = new ArrayList<>();
            String open = tags.get(i + "_open");
            String close = tags.get(i + "_close");
            System.out.println(tags.get(FacilitiesCSVConstants.NAME));
            String[] arr = validateShiftTime(open, close);
            for (int j = 0; j < arr.length; j +=2){
                attributes.add(createTuple(DAY, WEEK));
                attributes.add(createTuple(START_TIME, arr[j]));
                attributes.add(createTuple(END_TIME, arr[j+1]));
                writeStartTag(OPEN_TIME, attributes, true);
                attributes.clear();
            }
        }
    }

    private String[] validateShiftTime(String open, String close) {
        String[] strings;
        if (close.equals("00:00") || close.equals("00:00:00")){
            close = "24:00";
            strings = new String[]{open, close};
        } else {
            LocalTime time_open = LocalTime.parse(open);
            LocalTime time_close = LocalTime.parse(close);
            if (time_close.compareTo(time_open) < 0){
                strings = new String[]{open, "24:00:00", "00:00:00", close};
            } else {
                strings =  new String[]{open,close};
            }
        }
        return strings;
    }
}
