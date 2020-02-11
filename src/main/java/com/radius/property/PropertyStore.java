package com.radius.property;

import com.jeospatial.vptree.VPTree;
import com.radius.property.search.*;

import java.time.Instant;
import java.util.*;

/**
 * VP Tree based store of Geo Spatial points.
 */
public class PropertyStore {
    private VPTree<PropertySpatialPoint> propertyPointsTree = new VPTree<>();

    public boolean addProperty(Property property) {
        return propertyPointsTree.add(property.getSpatialPoint());
    }

    /**
     * @return the top matched maxResults properties in sorted order.
     */
    public List<PropertySpatialPoint> matchProperties(Query query, int maxResults) {
        long queryStartTime = Instant.now().getNano();
        PropertySearchResults<PropertySpatialPoint> results =
                (PropertySearchResults<PropertySpatialPoint>) propertyPointsTree.getAllNeighborsWithinDistance(
                query.getSpatialPoint(),
                SearchProperties.getDistanceErrorInMeters(),
                new PropertySearchResults<PropertySpatialPoint>(
                        query,
                        maxResults,
                        new PropertySearchCriteria(query)));
        System.out.println("Query time:" + (Instant.now().getNano() - queryStartTime) / 1000 + "us");
        return results.toSortedList();
    }

    public boolean removeProperty(Property property) {
        return propertyPointsTree.remove(property.getSpatialPoint());
    }
}
