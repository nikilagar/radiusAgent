package com.radius.property.search;

import com.jeospatial.SearchCriteria;
import com.radius.property.match.MatchProperties;
import com.radius.property.match.PropertyMatcher;

/**
 * Criteria is used while searching in {@link com.jeospatial.vptree.VPTree}.
 * This will be used to check fitness of the data points.
 */
public class PropertySearchCriteria implements SearchCriteria<PropertySpatialPoint> {
    private final Query query;

    public PropertySearchCriteria(Query query) {
        this.query = query;
    }

    @Override
    public boolean matches(PropertySpatialPoint point) {
        return Filter.isWithinLimits(point.getProperty().getNoOfBathrooms(), // verify no of bathrooms.
                                     query.getMinNoOfBathrooms(),
                                     query.getMaxNoOfBathrooms(),
                                     query.getBathroomErrorLeft(),
                                     query.getBathroomErrorRight()) &&
                Filter.isWithinLimits(point.getProperty().getNoOfBedrooms(), // verify no of bedrooms.
                                      query.getMinNoOfBedrooms(),
                                      query.getMaxNoOfBedrooms(),
                                      query.getBedroomErrorLeft(),
                                      query.getBedroomErrorRight()) &&
                Filter.isWithinLimits(point.getProperty().getPrice(),        // verify budget.
                                      query.getMinBudget(),
                                      query.getMaxBudget(),
                                      query.getBudgetErrorLeft(),
                                      query.getBudgetErrorRight()) &&
                // Verify property threshold match percentage.
                PropertyMatcher.getMatchPercent(point.getProperty(), query) >= MatchProperties.getValidMatchPercentage();
    }
}
