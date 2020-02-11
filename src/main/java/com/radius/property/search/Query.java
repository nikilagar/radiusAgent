package com.radius.property.search;

import com.radius.jeospatial.util.SimpleGeospatialPoint;
import com.radius.property.match.MatchProperties;

/**
 * Make query based on the parameters. Parameters in which either max or min is given
 * max and min are calculated and errors are updated accordingly.
 * Eg. if min budget is 1000 and there is no max budget with 10% as full match and +/-25%.
 * We update budget min as 900 and budget max as 1100 and error as 750 and 1250.
 */
public class Query {
    private int minNoOfBedrooms;
    private int maxNoOfBedrooms;
    private int minNoOfBathrooms;
    private int maxNoOfBathrooms;
    private double minBudget, maxBudget;
    private SimpleGeospatialPoint point;

    private double bathroomErrorRight;
    private double bedroomErrorRight;
    private double budgetErrorRight;
    private double bathroomErrorLeft;
    private double bedroomErrorLeft;
    private double budgetErrorLeft;
    private double matchDistance;
    private double errorDistance;

    /**
     * Initialize the query with the permissible errors that a parameter can take.
     */
    public Query(int minNoOfBedrooms,
                 int maxNoOfBedrooms,
                 int minNoOfBathrooms,
                 int maxNoOfBathrooms,
                 double latitude,
                 double longitude,
                 double minBudget,
                 double maxBudget)
    {
        if (maxNoOfBathrooms == Integer.MIN_VALUE) {
            this.maxNoOfBathrooms = minNoOfBathrooms + MatchProperties.getBathroomAllowable();
            this.minNoOfBathrooms = minNoOfBathrooms - MatchProperties.getBathroomAllowable();
            bathroomErrorRight =
                    bathroomErrorLeft =
                            SearchProperties.getBathroomError() - MatchProperties.getBathroomAllowable();
        }
        else {
            this.minNoOfBathrooms = minNoOfBathrooms;
            this.maxNoOfBathrooms = maxNoOfBathrooms;
            bathroomErrorRight =
                    bathroomErrorLeft =
                            SearchProperties.getBathroomError();
        }

        if (maxNoOfBedrooms == Integer.MIN_VALUE) {
            this.maxNoOfBedrooms = minNoOfBedrooms + MatchProperties.getBedroomAllowable();
            this.minNoOfBedrooms = minNoOfBedrooms - MatchProperties.getBedroomAllowable();
            bedroomErrorRight =
                    bedroomErrorLeft =
                            SearchProperties.getBedroomError() - MatchProperties.getBedroomAllowable();
        }
        else {
            this.minNoOfBedrooms = minNoOfBedrooms;
            this.maxNoOfBedrooms = maxNoOfBedrooms;
            bedroomErrorRight =
                    bedroomErrorLeft =
                            SearchProperties.getBedroomError();
        }

        if (maxBudget == Double.NEGATIVE_INFINITY) {
            this.maxBudget = minBudget + minBudget * MatchProperties.getBudgetAllowable() / 100;
            this.minBudget = minBudget - minBudget * MatchProperties.getBudgetAllowable() / 100;
            budgetErrorLeft =
                    budgetErrorRight =
                            minBudget * (SearchProperties.getBudgetError() - MatchProperties.getBudgetAllowable()) / 100;
        }
        else {
            this.minBudget = minBudget;
            this.maxBudget = maxBudget;
            budgetErrorLeft =
                    minBudget * SearchProperties.getBudgetError() / 100;
            budgetErrorRight =
                    maxBudget * SearchProperties.getBudgetError() / 100;
        }

        point = new SimpleGeospatialPoint(latitude, longitude);
        matchDistance = MatchProperties.getDistanceAllowable();
        errorDistance = SearchProperties.getDistanceErrorInMeters() - matchDistance;
    }

    public int getMinNoOfBedrooms() {
        return minNoOfBedrooms;
    }

    public int getMaxNoOfBedrooms() {
        return maxNoOfBedrooms;
    }

    public int getMinNoOfBathrooms() {
        return minNoOfBathrooms;
    }

    public int getMaxNoOfBathrooms() {
        return maxNoOfBathrooms;
    }

    public SimpleGeospatialPoint getSpatialPoint() {
        return point;
    }

    public double getMinBudget() {
        return minBudget;
    }

    public double getMaxBudget() {
        return maxBudget;
    }

    public double getBathroomErrorLeft() {
        return bathroomErrorLeft;
    }

    public double getBathroomErrorRight() {
        return bathroomErrorRight;
    }

    public double getBedroomErrorLeft() {
        return bedroomErrorLeft;
    }

    public double getBedroomErrorRight() {
        return bedroomErrorRight;
    }

    public double getBudgetErrorLeft() {
        return budgetErrorLeft;
    }

    public double getBudgetErrorRight() {
        return budgetErrorRight;
    }

    public double getMatchDistance() {
        return matchDistance;
    }

    public double getErrorDistance() {
        return errorDistance;
    }
}
