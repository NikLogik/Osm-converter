package ru.niklogik.csv;

import java.util.Arrays;
import java.util.List;

public class CSVTableElements {

    public static final String COORD_LAN = "lan";
    public static final String COORD_LEN = "lon";
    public static final String TYPE = "type";
    public static final String NAME = "name";
    public static final String CAPACITY = "capacity";
    public static final String VISIT_LEVEL = "visit_level";
    public static final String NUMBER_OF_SHIFTS= "number_of_shifts";
    public static final String _1_OPEN = "1_open";
    public static final String _1_CLOSE= "1_close";
    public static final String _1_CAP = "1_cap";
    public static final String _2_OPEN = "2_open";
    public static final String _2_CLOSE= "2_close";
    public static final String _2_CAP = "2_cap";
    public static final String _3_OPEN = "3_open";
    public static final String _3_CLOSE = "3_close";
    public static final String _3_CAP = "3_cap";

    public static List<String> csv_columns = Arrays.asList(COORD_LAN, COORD_LEN, TYPE, NAME, CAPACITY, VISIT_LEVEL, NUMBER_OF_SHIFTS,
                                                            _1_OPEN, _1_CLOSE, _1_CAP, _2_OPEN, _2_CLOSE, _2_CAP, _3_OPEN, _3_CLOSE, _3_CAP);
}
