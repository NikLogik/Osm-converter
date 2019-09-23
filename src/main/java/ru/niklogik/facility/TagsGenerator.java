package ru.niklogik.facility;

import ru.niklogik.csv.CSVDataImpl;
import ru.niklogik.osm.Osm;

import java.util.*;

import static ru.niklogik.csv.FacilitiesCSVConstants.*;

public class TagsGenerator {

    private static Random random = new Random();

    public static void addCSVTags(Osm.Node node, CSVDataImpl.AttractionType att_type) {

        Map<String, String> tags = node.getTags();

        if (!tags.keySet().contains(VISIT_LEVEL))
            tags.put(VISIT_LEVEL,"any");

        switch (att_type){
            case work:
            case shopping:
                if (!tags.keySet().contains(CAPACITY)){
                    String cap = String.valueOf(random.nextInt(10) + 10);
                    tags.put(CAPACITY, cap);
                }
                String working_time = tags.get("opening_hours");
                if (working_time == null){
                    generateShiftsTime(tags,1);
                    tags.put(NUMBER_OF_SHIFTS, "1");
                } else if (working_time.equals("24/7")){
                    generateShiftsTime(tags, 0);
                    tags.put(NUMBER_OF_SHIFTS, "3");
                } else {
                    parseShiftsTime(tags, working_time);
                }
                break;
            case school:
            case education:
                if (!tags.keySet().contains(CAPACITY)){
                    String cap = String.valueOf(random.nextInt(300) + 400);
                    tags.put(CAPACITY, cap);
                }
                break;
            case store:
                if (!tags.keySet().contains(CAPACITY)){
                    String cap = "0";
                    tags.put(CAPACITY, cap);
                }
                break;
        }
    }

    private static void generateShiftsTime(Map<String, String> tags, int shifts) {
        if (shifts==1){
            int index = random.nextInt(2);
            String[] start_time = new String[]{"09:00:00", "09:30:00", "10:00:00"};
            String[] end_time = new String[]{"18:00:00", "18:30:00", "19:00:00"};
            tags.put(_1_OPEN, start_time[index]);
            tags.put(_1_CLOSE, end_time[index]);
            tags.put(_1_CAP, String.valueOf(shifts));
        } else if (shifts == 0){
            tags.put("1_open", "07:00:00");
            tags.put("1_close", "15:00:00");
            tags.put("1_cap", "0.4");
            tags.put("2_open", "15:00:00");
            tags.put("2_close", "23:00:00");
            tags.put("2_cap", "0.4");
            tags.put("3_open", "23:00:00");
            tags.put("3_close", "07:00:00");
            tags.put("3_cap", "0.2");
        }
    }

    private static void parseShiftsTime(Map<String, String> tags, String working_time){
        ArrayList<String> times;

        if (working_time.contains(";")) {
            times = new ArrayList<>(Arrays.asList(working_time.split(";")[0]));
        }else {
            times = new ArrayList<>(Arrays.asList(new String[]{working_time}));
        }

        for(int i = 0; i < times.size(); i++){
            String item = times.get(i);
            if (item.contains(" ")) {
                times.set(i, item.split(" ")[1]);
            }
        }
        for (int i = 0; i < times.size(); i++){
            String item = times.get(i);
            if (item.contains(",")){
                String[] arr = item.split(",");
                times.addAll(Arrays.asList(arr));
                times.remove(i);
            }
        }
        double shifts = 1 / (double)times.size();
        int count = 0;
        for (int i = 0; i < times.size(); i++){
            String item = times.get(i);
            if (item.contains("-")) {
                String[] shift = times.get(i).split("-");
                tags.put((i + 1) + "_open", shift[0]);
                tags.put((i + 1) + "_close", shift[1]);
                tags.put((i + 1) + "_cap", String.valueOf(shifts));
                count++;
            }
        }
        tags.put(NUMBER_OF_SHIFTS, String.valueOf(count));
    }
}
