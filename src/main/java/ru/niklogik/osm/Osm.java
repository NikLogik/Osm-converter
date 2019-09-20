package ru.niklogik.osm;

import org.matsim.api.core.v01.Coord;
import org.matsim.api.core.v01.Id;
import org.matsim.api.core.v01.Identifiable;

import com.google.common.base.Joiner;

import java.util.Arrays;
import java.util.List;
import java.util.Map;

/**
 * Definitions for OpenStreetMap (OSM) including interfaces for elements
 *
 * @author polettif
 */
public final class Osm {

    /**
     * OSM tags used by the converters
     */
    public enum ElementType {
        NODE("node"),
        WAY("way"),
        RELATION("relation");

        public final String name;

        ElementType(String name) {
            this.name = name;
        }

        public String toString() {
            return name;
        }
    }

    /**
     * Parent class for a basic OSM element node, way or relation
     */
    public interface Element {

        ElementType getType();

        Map<String, String> getTags();

        /**
         * @return the value associated with the given key
         */
        String getValue(String key);
    }

    /**
     * OSM node
     */
    public interface Node extends Element, Identifiable<Node> {

        void setTags(Map<String, String> tags);

        Coord getCoord();

        void setCoord(Coord coord);

    }

    /**
     * OSM way
     */
    public interface Way extends Element, Identifiable<Way> {


        List<Node> getNodes();

        /**
         * @return the relations of which this node is a member
         */
        Map<Id<Relation>, Relation> getRelations();
    }

    /**
     * OSM relation
     */
    public interface Relation extends Element, Identifiable<Relation> {
        List<Element> getMembers();

        /**
         * @return the role the given member has. <tt>null</tt> if element is not
         * a member or no role is assigned
         */
        String getMemberRole(Element member);

        /**
         * @return the relations of which this node is a member
         */
        Map<Id<Relation>, Relation> getRelations();
    }

    /**
     * OSM tags used by the converters
     */
    public static final class Key {

        public static final String AMENITY = "amenity";
        public static final String LEISURE = "leisure";
        public static final String TOURISM = "tourims";
        public static final String SHOP = "shop";

        public static final List<String> DEFAULT_KEYS = Arrays.asList(
                AMENITY, LEISURE, TOURISM, SHOP);

        public static final List<String> ATTR_KEYS = Arrays.asList(AMENITY, SHOP);

        public static String combinedKey(String... keyParts) {
            return Joiner.on(":").join(keyParts);
        }
    }
}
