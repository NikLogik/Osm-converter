package ru.niklogik.osm;

import org.matsim.api.core.v01.Coord;
import org.matsim.api.core.v01.Id;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class OsmElement {

    public static class Node implements Osm.Node {

        private final Id<Osm.Node> id;

        private Map<String, String> tags;

        private Coord coord;
        public Node(long id, Coord coord) {
            this.id = Id.create(id, Osm.Node.class);
            this.coord = coord;
            this.tags = new HashMap<>();
        }

        public Node(final long id, final Coord coord, Map<String, String> tags) {
            this.id = Id.create(id, Osm.Node.class);
            this.coord = coord;
            this.tags = tags;
        }

        @Override
        public Id<Osm.Node> getId() {
            return id;
        }

        @Override
        public void setTags(Map<String, String> tags) {
            this.tags = tags;
        }

        @Override
        public Coord getCoord() {
            return coord;
        }

        @Override
        public void setCoord(Coord coord) {
            this.coord = coord;
        }

        @Override
        public Map<String, String> getTags() {
            return tags;
        }

        @Override
        public String getValue(String key) {
            return tags.get(key);
        }

        @Override
        public Osm.ElementType getType() {
            return Osm.ElementType.NODE;
        }

        @Override
        public boolean equals(Object obj) {
            if(this == obj)
                return true;
            if(obj == null)
                return false;
            if(!(obj instanceof Osm.Node))
                return false;

            Osm.Node other = (Osm.Node) obj;
            return getId().equals(other.getId());
        }

        @Override
        public int hashCode() {
            return getId().hashCode();
        }

    }

    /**
     * OSM way
     */
    public static class Way implements Osm.Way {

        private final Id<Osm.Way> id;
        private final List<Osm.Node> nodes;
        private Map<String, String> tags;

        private final Map<Id<Osm.Relation>, Osm.Relation> relations = new HashMap<>();

        public Way(long id, List<Osm.Node> nodes, Map<String, String> tags) {
            this.id = Id.create(id, Osm.Way.class);
            this.nodes =  nodes;
            this.tags = tags;
        }

        @Override
        public Id<Osm.Way> getId() {
            return id;
        }

        @Override
        public List<Osm.Node> getNodes() {
            return nodes;
        }

        @Override
        public Map<String, String> getTags() {
            return tags;
        }

        @Override
        public String getValue(String key) {
            return tags.get(key);
        }

        @Override
        public Osm.ElementType getType() {
            return Osm.ElementType.WAY;
        }

        /*pckg*/ void addRelation(Osm.Relation rel) {
            relations.put(rel.getId(), rel);
        }

        @Override
        public boolean equals(Object obj) {
            if(this == obj)
                return true;
            if(obj == null)
                return false;
            if(!(obj instanceof Way))
                return false;

            Way other = (Way) obj;
            return getId().equals(other.getId());
        }

        @Override
        public int hashCode() {
            return getId().hashCode();
        }

        public Map<Id<Osm.Relation>, Osm.Relation> getRelations() {
            return relations;
        }
    }

    /**
     * OSM relation
     */
    public static class Relation implements Osm.Relation {

        private final Id<Osm.Relation> id;
        private final Map<String, String> tags;
        private final Map<Id<Osm.Relation>, Osm.Relation> relations = new HashMap<>();
        private List<Osm.Element> members;
        private Map<Osm.Element, String> memberRoles;

        public Relation(long id, Map<String, String> tags) {
            this.id = Id.create(id, Osm.Relation.class);
            this.tags = tags;
        }

        @Override
        public Id<Osm.Relation> getId() {
            return id;
        }

        @Override
        public List<Osm.Element> getMembers() {
            return members;
        }

        @Override
        public Map<String, String> getTags() {
            return tags;
        }

        @Override
        public String getValue(String key) {
            return tags.get(key);
        }

        @Override
        public Osm.ElementType getType() {
            return Osm.ElementType.RELATION;
        }

        /*pckg*/ void addRelation(Osm.Relation currentRel) {
            relations.put(currentRel.getId(), currentRel);
        }

        @Override
        public Map<Id<Osm.Relation>, Osm.Relation> getRelations() {
            return relations;
        }

        public String getMemberRole(Osm.Element member) {
            return memberRoles.get(member);
        }

        /*pckg*/ void setMembers(List<Osm.Element> memberList, Map<Osm.Element, String> memberRoles) {
            if(members != null) {
                throw new IllegalArgumentException("Relation members have already been set");
            }
            this.members = memberList;
            this.memberRoles = memberRoles;
        }

        @Override
        public boolean equals(Object obj) {
            if(this == obj)
                return true;
            if(obj == null)
                return false;
            if(!(obj instanceof Relation))
                return false;

            Relation other = (Relation) obj;
            return getId().equals(other.getId());
        }

        @Override
        public int hashCode() {
            return getId().hashCode();
        }
    }
}
