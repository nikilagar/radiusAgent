package com.radius.property.search;

import com.radius.jeospatial.SearchCriteria;
import com.radius.property.match.MatchProperties;
import com.radius.property.match.PropertyMatcher;

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
                PropertyMatcher.getMatchPercent(point.getProperty(), query) >= MatchProperties.getValidMatchPercentage();
    }
}
