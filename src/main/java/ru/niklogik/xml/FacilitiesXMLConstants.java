package ru.niklogik.xml;

public class FacilitiesXMLConstants {

    //root tag
    public static final String FACILITIES = "facilities";

    //child tags
    public static final String FACILITY = "facility";
    public static final String ACTIVITY = "activity";
    public static final String CAPACITY = "capacity";
    public static final String OPEN_TIME = "opentime";
    public static final String ATTRIBUTES = "attributes";
    public static final String ATTRIBUTE = "attribute";

    //attributes for tags
    public static final String ID = "id";
    public static final String NAME = "name";
    public static final String TYPE = "type";
    public static final String X = "x";
    public static final String Y = "y";
    public static final String LINK_ID = "linkId";
    public static final String DESCRIPTION = "desc";
    public static final String VALUE = "value";
    public static final String DAY = "day";
    public static final String START_TIME = "start_time";
    public static final String END_TIME = "end_time";
    public static final String CLASS = "class";

    //values for attribute DAY
    public static final String MONDAY = "mon";
    public static final String TUESDAY = "tue";
    public static final String WEDNESDAY = "wed";
    public static final String THURSDAY = "thu";
    public static final String FRIDAY = "fri";
    public static final String SATURDAY = "sat";
    public static final String SUNDAY = "sun";
    // mon - fri
    public static final String WEEK_DAYS = "wkday";
    //sat-sun
    public static final String WEEK_END = "wkend";
    //whole week
    public static final String WEEK = "wk";

}
