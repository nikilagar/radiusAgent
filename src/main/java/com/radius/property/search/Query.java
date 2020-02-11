package com.radius.property.search;

import com.jeospatial.util.SimpleGeospatialPoint;
import com.radius.property.match.MatchProperties;

/**
 * Make query based on the parameters. Parameters in which either max or min is given,
 * pseudo max and min values are calculated and errors are updated accordingly.
 * Eg. if min budget is 1000 and there is no max budget with 10% as full match and +/-25% as error.
 * We update budget min budget as 900 and max budget as 1100 and error as 750 and 1250 respectively.
 */
public class Query {
    private int minNoOfBedrooms;
    private int maxNoOfBedrooms;
    private int minNoOfBathrooms;
    private int maxNoOfBathrooms;
    private double minBudget, maxBudget;
    private SimpleGeospatialPoint point;

    private int bathroomErrorRight;
    private int bedroomErrorRight;
    private int bathroomErrorLeft;
    private int bedroomErrorLeft;
    private double budgetErrorRight;
    private double budgetErrorLeft;
    private double matchDistance;
    private double errorDistance;

    /**
     * Initialize the query with the permissible errors that a parameter can take.
     * -1 denotes that the value is absent.
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
        if (maxNoOfBathrooms == -1) {
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

        if (maxNoOfBedrooms == -1) {
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

        if (maxBudget < 0) {
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

    public int getBathroomErrorLeft() {
        return bathroomErrorLeft;
    }

    public int getBathroomErrorRight() {
        return bathroomErrorRight;
    }

    public int getBedroomErrorLeft() {
        return bedroomErrorLeft;
    }

    public int getBedroomErrorRight() {
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
