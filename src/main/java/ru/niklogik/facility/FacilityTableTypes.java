package ru.niklogik.facility;

import java.util.Arrays;
import java.util.List;
import java.util.StringJoiner;

public class FacilityTableTypes {

    private FacilityTableTypes() {} // Do not instantiate

    // Older coarse classification
    public static final String HOME = "home";
    public static final String WORK = "work";
    public static final String EDUCATION = "education";
    public static final String LEISURE = "leisure";
    public static final String SHOPPING = "shop";
    public static final String POLICE = "police";
    public static final String MEDICAL = "medical";
    public static final String OTHER = "other";
    public static final String IGNORE = "ignore";

    // Special cases
    public static final String AIRPORT = "airport";

    // Added for better amenity classification, 2018-12-12
    public static final String BAR = "bar";
    public static final String CAFE = "cafe";
    public static final String CLINIC = "clinic";
    public static final String DRINKING_WATER = "drinking_water";
    public static final String FAST_FOOD = "fast_food";
    public static final String ICE_CREAM = "ice_cream";
    public static final String RESTAURANT = "restaurant";
    public static final String HIGHER_EDUCATION = "higher_education";
    public static final String HOSPITAL = "hospital";
    public static final String LIBRARY = "library";
    public static final String KINDERGARTEN = "kindergarten";
    public static final String CHARGING_STATION = "charging_station";;
    public static final String FUEL_STATION = "fuel_station";
    public static final String ATM = "atm";
    public static final String BANK = "bank";
    public static final String PHARMACY = "pharmacy";
    public static final String PHYSICIAN = "physician";
    public static final String CINEMA = "cinema";
    public static final String POST_BOX = "post_box";
    public static final String POST_OFFICE = "post_office";
    public static final String SCHOOL = "school";
    public static final String THEATRE = "theatre";
    public static final String TOILETS = "toilets";
    public static final String WORSHIP = "worhsip";

    // Added for better leisure site classification, 2018-12-12
    public static final String PARK = "park";
    public static final String SPORT = "sport";
    public static final String GARDEN = "garden";
    public static final String GOLF = "golf";
    public static final String ICE_RINK = "ice_rink";
    public static final String MINIATURE_GOLF = "miniature_golf";
    public static final String SPORT_PUBLIC = "sport_public";
    public static final String PLAYGROUND = "playground";
    public static final String STADIUM = "stadium";
    public static final String SWIMMING = "swimming";

    // Added for better tourism site classification, 2018-12-12
    public static final String HOLIDAY_HOME = "holiday_home";
    public static final String ATTRACTION = "attraction";
    public static final String ARTWORK = "artwork";
    public static final String CAMPING = "camping";
    public static final String GALLERY = "gallery";
    public static final String GUEST_HOUSE = "guest_house";
    public static final String HOSTEL = "hostel";
    public static final String HOTEL = "hotel";
    public static final String TOURIST_INFO = "tourist_info";
    public static final String MOTEL = "motel";
    public static final String MUSEUM = "museum";
    public static final String PICNIC_SITE = "picnic_site";
    public static final String THEME_PARK = "theme_park";
    public static final String VIEWPOINT = "viewpoint";
    public static final String ZOO = "zoo";

    // Added for better shop classification, 2018-12-12
    public static final String SHOP_BEVERAGES = "beverage_shop";
    public static final String BAKERY = "bakery";
    public static final String SHOP_HOBBY = "hobby_shop";
    public static final String BUTCHER = "butcher";
    public static final String SHOP_FOOD = "food_shop";
    public static final String DEPARTMENT_STORE = "department_store";
    public static final String KIOSK = "kiosk";
    public static final String MALL = "mall";
    public static final String SHOP_CLOTHING = "clothing_shop";
    public static final String SUPERMARKET = "supermarket";
    public static final String WHOLESALE = "wholesale_store";
    public static final String JEWELRY = "jewelry";
    public static final String SHOP_BEAUTY = "beauty_shop";
    public static final String SHOP_MEDICAL = "medical_shop";
    public static final String HAIRDRESSER = "hairdresser";
    public static final String SHOP_EROTIC = "erotic_shop";
    public static final String OPTICIAN = "optician";
    public static final String SHOP_TATTOO = "tattoo_shop";
    public static final String SHOP_DIY = "diy_shop";
    public static final String FLORIST = "florist";
    public static final String SHOP_GARDEN = "garden_shop";
    public static final String SHOP_FURNITURE = "furtniture_shop";
    public static final String SHOP_ELECTRONICS = "electronics_shop";
    public static final String SHOP_BICYCLE = "bicycle_shop";
    public static final String SHOP_AUTOMOTIVE = "automotive_shop";
    public static final String SHOP_SPORT = "sport_shop";
    public static final String SHOP_BOOKS = "book_shop";
    public static final String NEWSAGENT = "newsagent";

    //Added 18-09-2019
    public static final String CONVENIENCE = "convenience";
    public static final String ALCOHOL = "alcohol";
    public static final String COLLEGE = "college";
    public static final String FUEL = "fuel";
    public static final String DENTIST = "dentist";
    public static final String DOCTORS = "doctors";
    private static final String SOCIAL_CENTER = "social_center";
    private static final String PUBLIC_SERVICE = "public_service";
    private static final String TOWNHALL = "townhall";
    private static final String VETERINARY = "veterinary";
    private static final String PUBLIC_BUILDING = "public_building";
    private static final String FIRE_STATION = "fire_station";
    private static final String RECYCLING = "recycling";

    private static final String COMMUNITY_CENTRE = "community_centre";
    private static final String MARKETPLACE = "marketplace";
    private static final String PET = "pet";
    private static final String GIFT = "gift";
    private static final String BOOKS = "books";
    private static final String CLOTHES = "clothes";
    private static final String CAR_REPAIR = "car_repair";
    private static final String BEAUTY = "beauty";
    private static final String CAR_WASH = "car_wash";
    private static final String NIGHTCLUB = "nightclub";
    public static final String SPORTS = "sports";

    //Additional list of tags for converting from Osm types to CSV AttrationObjects types
    public static final List<String> work_cat = Arrays.asList(BANK, FUEL_STATION, FUEL, HOSPITAL, DENTIST, DOCTORS, POLICE, POST_OFFICE,
            SOCIAL_CENTER, PUBLIC_SERVICE, TOWNHALL, VETERINARY, LIBRARY, PUBLIC_BUILDING, FIRE_STATION, RECYCLING, CLINIC, PHARMACY);
    public static final List<String> store_cat = Arrays.asList(SUPERMARKET, PHARMACY, CONVENIENCE, ALCOHOL);
    public static final List<String> shopping_cat = Arrays.asList(COMMUNITY_CENTRE, BAR, RESTAURANT, FAST_FOOD, MARKETPLACE, CAFE, THEATRE,
            CAR_WASH, NIGHTCLUB, BUTCHER, JEWELRY, PET, GIFT, BOOKS, CLOTHES, CAR_REPAIR, BEAUTY, SPORTS, BAKERY, HAIRDRESSER);
    public static final List<String> education_cat = Arrays.asList(COLLEGE);
    public static final List<String> school_cat = Arrays.asList(SCHOOL, KINDERGARTEN);
}
