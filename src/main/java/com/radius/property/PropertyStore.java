package com.radius.property;

import com.radius.jeospatial.util.SimpleGeospatialPoint;
import com.radius.jeospatial.vptree.VPTree;
import com.radius.property.match.PropertyMatcher;
import com.radius.property.search.*;

import java.time.Instant;
import java.util.*;

public class PropertyStore {
    private VPTree<PropertySpatialPoint> propertyPointsTree = new VPTree<>();

    public boolean addProperty(Property property) {
        return propertyPointsTree.add(property.getSpatialPoint());
    }

    public void matchProperties(Query query, int maxResults) {
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
        for (PropertySpatialPoint point: results.toSortedList()) {
            System.out.print("Match %" + PropertyMatcher.getMatchPercent(point.getProperty(), query) + " " + point.getProperty());
            System.out.println(" Bath% " + PropertyMatcher.getBathroomsMatchScore(point.getProperty(), query) +
                               " Bed% " + PropertyMatcher.getBedroomsMatchScore(point.getProperty(),query) +
                                " Budget% " + PropertyMatcher.getBudgetMatchScore(point.getProperty(),query) +
                                " Dis% " + PropertyMatcher.getRadiusMatchScore(point.getProperty(), query));
        }
    }

    public boolean removeProperty(Property property) {
        return propertyPointsTree.remove(property.getSpatialPoint());
    }
}
