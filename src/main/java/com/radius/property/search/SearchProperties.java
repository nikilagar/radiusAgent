package com.radius.property.search;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

public class SearchProperties {
    private static final int BEDROOM_ERROR;
    private static final int BATHROOM_ERROR;
    private static final double BUDGET_ERROR;
    private static final double DISTANCE_ERROR;

    static {
        String rootPath = Thread.currentThread().getContextClassLoader().getResource("").getPath();
        String appConfigPath = rootPath + "search.properties";

        Properties appProps = new Properties();
        try {
            appProps.load(new FileInputStream(appConfigPath));
        } catch (IOException e) {
            throw new IllegalArgumentException("Properties file not found.");
        }

        String bedroomError = appProps.getProperty("com.radius.property.search.bedroomError");
        if (bedroomError != null) {
            BEDROOM_ERROR = Integer.parseInt(bedroomError);
        }
        else {
            BEDROOM_ERROR = 0;
        }

        String bathroomError = appProps.getProperty("com.radius.property.search.bathroomError");
        if (bathroomError != null) {
            BATHROOM_ERROR = Integer.parseInt(bathroomError);
        }
        else {
            BATHROOM_ERROR = 0;
        }

        String budgetError = appProps.getProperty("com.radius.property.search.budgetError");
        if (budgetError != null) {
            BUDGET_ERROR = Integer.parseInt(budgetError);
        }
        else {
            BUDGET_ERROR = 0;
        }

        String distanceError = appProps.getProperty("com.radius.property.search.distanceError");
        if (distanceError != null) {
            DISTANCE_ERROR = Double.parseDouble(distanceError);
        }
        else {
            DISTANCE_ERROR = 0;
        }
    }

    public static int getBedroomError() {
        return BEDROOM_ERROR;
    }

    public static int getBathroomError() {
        return BATHROOM_ERROR;
    }

    public static double getBudgetError() {
        return BUDGET_ERROR;
    }

    public static double getDistanceErrorInMeters() {
        return DISTANCE_ERROR * 1.60934 * 1000;
    }
}
