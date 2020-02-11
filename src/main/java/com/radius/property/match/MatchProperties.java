package com.radius.property.match;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

public class MatchProperties {
    private static final int BEDROOM_WEIGHT;
    private static final int BATHROOM_WEIGHT;
    private static final double BUDGET_WEIGHT;
    private static final double DISTANCE_WEIGHT;
    private static final int BEDROOM_ALLOWABLE;
    private static final int BATHROOM_ALLOWABLE;
    private static final double BUDGET_ALLOWABLE;
    private static final double DISTANCE_ALLOWABLE;
    private static final double VALID_MATCH_PERCENTAGE;

    static {
        String rootPath = Thread.currentThread().getContextClassLoader().getResource("").getPath();
        String appConfigPath = rootPath + "match.properties";

        Properties appProps = new Properties();
        try {
            appProps.load(new FileInputStream(appConfigPath));
        } catch (IOException e) {
            throw new IllegalArgumentException("Properties file not found.");
        }

        String bedroomWeight = appProps.getProperty("com.radius.property.match.bedroom.weight");
        if (bedroomWeight != null) {
            BEDROOM_WEIGHT = Integer.parseInt(bedroomWeight);
        }
        else {
            BEDROOM_WEIGHT = 0;
        }

        String bathroomWeight = appProps.getProperty("com.radius.property.match.bathroom.weight");
        if (bathroomWeight != null) {
            BATHROOM_WEIGHT = Integer.parseInt(bathroomWeight);
        }
        else {
            BATHROOM_WEIGHT = 0;
        }

        String budgetWeight = appProps.getProperty("com.radius.property.match.budget.weight");
        if (budgetWeight != null) {
            BUDGET_WEIGHT = Integer.parseInt(budgetWeight);
        }
        else {
            BUDGET_WEIGHT = 0;
        }

        String radiusWeight = appProps.getProperty("com.radius.property.match.distance.weight");
        if (radiusWeight != null) {
            DISTANCE_WEIGHT = Double.parseDouble(radiusWeight);
        }
        else {
            DISTANCE_WEIGHT = 0;
        }

        String bedroomAllowable = appProps.getProperty("com.radius.property.match.bedroom.allowable");
        if (bedroomAllowable != null) {
            BEDROOM_ALLOWABLE = Integer.parseInt(bedroomAllowable);
        }
        else {
            BEDROOM_ALLOWABLE = 0;
        }

        String bathroomAllowable = appProps.getProperty("om.radius.property.match.bathroom.allowable");
        if (bathroomAllowable != null) {
            BATHROOM_ALLOWABLE = Integer.parseInt(bathroomAllowable);
        }
        else {
            BATHROOM_ALLOWABLE = 0;
        }

        String budgetAllowable = appProps.getProperty("com.radius.property.match.budget.allowable");
        if (budgetAllowable != null) {
            BUDGET_ALLOWABLE = Integer.parseInt(budgetAllowable);
        }
        else {
            BUDGET_ALLOWABLE = 0;
        }

        String radiusAllowable = appProps.getProperty("com.radius.property.match.distance.allowable");
        if (radiusAllowable != null) {
            DISTANCE_ALLOWABLE = Double.parseDouble(radiusAllowable);
        }
        else {
            DISTANCE_ALLOWABLE = 0;
        }

        String validMatchPercentage = appProps.getProperty("com.radius.property.match.valid.percentage");
        if (validMatchPercentage != null) {
            VALID_MATCH_PERCENTAGE = Double.parseDouble(validMatchPercentage);
        }
        else {
            VALID_MATCH_PERCENTAGE = 0;
        }
    }

    public static int getBedroomAllowable() {
        return BEDROOM_ALLOWABLE;
    }

    public static int getBathroomAllowable() {
        return BATHROOM_ALLOWABLE;
    }

    public static double getBudgetAllowable() {
        return BUDGET_ALLOWABLE;
    }

    public static double getDistanceAllowable() {
        return DISTANCE_ALLOWABLE;
    }

    public static int getBedroomWeight() {
        return BEDROOM_WEIGHT;
    }

    public static int getBathroomWeight() {
        return BATHROOM_WEIGHT;
    }

    public static double getBudgetWeight() {
        return BUDGET_WEIGHT;
    }

    public static double getDistanceWeight() {
        return DISTANCE_WEIGHT;
    }

    public static double getRadiusErrorInMeters() {
        return DISTANCE_WEIGHT * 1.60934 * 1000;
    }

    public static double getValidMatchPercentage() {
        return VALID_MATCH_PERCENTAGE;
    }
}
