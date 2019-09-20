/* *********************************************************************** *
 * project: org.matsim.*
 * *********************************************************************** *
 *                                                                         *
 * copyright       : (C) 2016 by the members listed in the COPYING,        *
 *                   LICENSE and WARRANTY file.                            *
 * email           : info at matsim dot org                                *
 *                                                                         *
 * *********************************************************************** *
 *                                                                         *
 *   This program is free software; you can redistribute it and/or modify  *
 *   it under the terms of the GNU General Public License as published by  *
 *   the Free Software Foundation; either version 2 of the License, or     *
 *   (at your option) any later version.                                   *
 *   See also COPYING, LICENSE and WARRANTY file                           *
 *                                                                         *
 * *********************************************************************** */

package ru.niklogik.osm;

import org.apache.log4j.Logger;
import org.matsim.api.core.v01.Id;
import ru.niklogik.io.OsmFileReader;

import java.util.*;

/**
 * @author polettif
 */
public class OsmDataImpl implements OsmData {

	private final static Logger log = Logger.getLogger(OsmData.class);

	protected final Map<Id<Osm.Node>, Osm.Node> nodes = new HashMap<>();


	protected final Map<Id<Osm.Way>, Osm.Way> ways = new HashMap<>();
	protected final Map<Id<Osm.Relation>, Osm.Relation> relations = new HashMap<>();

	protected Map<Long, OsmFileReader.ParsedRelation> parsedRelations = null;

	protected Map<Long, OsmFileReader.ParsedWay> parsedWays = null;

	// Filters

	protected AllowedTagsFilter filter = new AllowedTagsFilter();
	/**
	 * @param filters are used when reading an osm file, tags not specified in filters are skipped
	 */
	public OsmDataImpl(AllowedTagsFilter... filters) {
		for(AllowedTagsFilter f : filters) {
			this.filter.mergeFilter(f);
		}
	}

	@Override
	public Map<Id<Osm.Node>, Osm.Node> getNodes() {
		return nodes;
	}

	@Override
	public Map<Long, OsmFileReader.ParsedWay> getParsedWays() {
		return parsedWays;
	}


	@Override
	public void removeNode(Id<Osm.Node> id) {
		nodes.remove(id);
	}

	@Override
	public void handleParsedNode(OsmFileReader.ParsedNode node) {
		if(filter.matches(node)) {
			Osm.Node newNode = new OsmElement.Node(node.id, node.coord, node.tags);
			if(nodes.put(newNode.getId(), newNode) != null) {
				throw new RuntimeException("Node id " + newNode.getId() + "already exists on map");
			}
		}
	}

	@Override
	public Map<Id<Osm.Way>, Osm.Way> getWays() {
		return ways;
	}

	@Override
	public void handleParsedWay(OsmFileReader.ParsedWay way) {
		if(filter.matches(way)) {
			if(parsedWays == null) parsedWays = new HashMap<>();
			parsedWays.put(way.id, way);
		}
	}

	@Override
	public void handleParsedRelation(OsmFileReader.ParsedRelation relation) {
		if(filter.matches(relation)) {
			if(parsedRelations == null) parsedRelations = new HashMap<>();
			parsedRelations.put(relation.id, relation);
		}
	}
}
