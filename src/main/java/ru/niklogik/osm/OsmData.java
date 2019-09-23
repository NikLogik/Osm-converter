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

import org.matsim.api.core.v01.Id;
import ru.niklogik.io.OsmFileReader;
import ru.niklogik.osm.Osm;

import java.util.Map;

/**
 * Interface to load an osm-xml file. Is used by the osm
 * network converter (similar to GtfsFeed and GtfsConverter)
 *
 * Should be called OSMMap since it represents the map/network
 * but that's a bit redundant
 *
 * OSM data is read via {@link OsmFileReader}. After reading the file
 * new elements cannot be added directly (indirectly via handlers).
 *
 * @author polettif
 */
public interface OsmData {

	Map<Id<Osm.Node>, Osm.Node> getNodes();

	/**
	 * Defines how a node should be handled in {@link OsmFileReader}
	 */
	void handleParsedNode(OsmFileReader.ParsedNode parsedNode);

	void handleParsedRelation(OsmFileReader.ParsedRelation parsedRelation);

	Map<Long, OsmFileReader.ParsedWay> getParsedWays();

	/**
	 * Removes the node from the osm data set. The node is removed from ways
	 * and relations as well. This might lead to inconsistencies.
	 */
	void removeNode(Id<Osm.Node> id);

	Map<Id<Osm.Way>, Osm.Way> getWays();

	void handleParsedWay(OsmFileReader.ParsedWay currentWay);

}