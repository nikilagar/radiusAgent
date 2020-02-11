package com.radius.property.match;

import com.radius.property.Property;
import com.radius.property.search.PropertySpatialPoint;
import com.radius.property.search.Query;

import java.util.Comparator;

/**
 * For the given query, calculate the weighted match score. Properties can be also compared based
 * on this.
 */
public class PropertyMatcher<T extends PropertySpatialPoint> extends Matcher implements Comparator<T> {
    private Query query;

    public PropertyMatcher(Query query) {
        this.query = query;
    }

    public static double getBathroomsMatchScore(Property property, Query query) {
        return MatchProperties.getBathroomWeight() *
                getMatchScore(property.getNoOfBathrooms(),
                              query.getMinNoOfBathrooms(),
                              query.getMaxNoOfBathrooms(),
                              query.getBathroomErrorLeft(),
                              query.getBathroomErrorRight());
    }

    public static double getBedroomsMatchScore(Property property, Query query) {
        return MatchProperties.getBedroomWeight() *
                getMatchScore(property.getNoOfBedrooms(),
                              query.getMinNoOfBedrooms(),
                              query.getMaxNoOfBedrooms(),
                              query.getBedroomErrorLeft(),
                              query.getBedroomErrorRight());
    }

    public static double getBudgetMatchScore(Property property, Query query) {
        return MatchProperties.getBudgetWeight() *
                getMatchScore(property.getPrice(),
                              query.getMinBudget(),
                              query.getMaxBudget(),
                              query.getBudgetErrorLeft(),
                              query.getBudgetErrorRight());
    }

    public static double getRadiusMatchScore(Property property, Query query) {
        double distance =
                property.getSpatialPoint().getDistanceTo(query.getSpatialPoint());
        return MatchProperties.getDistanceWeight() *
                getMatchScore(distance,
                              0,
                              query.getMatchDistance(),
                              0,
                              query.getErrorDistance());
    }

    public static double getMatchPercent(Property property, Query query) {
        return getBathroomsMatchScore(property, query) +
                getBedroomsMatchScore(property, query) +
                getBudgetMatchScore(property, query) +
                getRadiusMatchScore(property, query);
    }

    @Override
    public int compare(T p1, T p2) {
        double match1 = getMatchPercent(p1.getProperty(), query);
        double match2 = getMatchPercent(p2.getProperty(), query);

        if(match1 < match2) { return -1; }
        if(match1 > match2) { return 1; }
        return 0;
    }
}
