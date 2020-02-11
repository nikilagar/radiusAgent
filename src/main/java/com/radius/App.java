package com.radius;

import com.radius.property.Property;
import com.radius.property.match.PropertyMatcher;
import com.radius.property.search.PropertySpatialPoint;
import com.radius.property.search.Query;
import com.radius.property.PropertyStore;

import java.util.List;
import java.util.concurrent.ThreadLocalRandom;

public class App {

    /**
     * Load random points to the database.
     */
    private static void loadRandomProperties(PropertyStore store) {
        long loadStartTime = System.currentTimeMillis();

        System.out.println("Loding random properties:");

        for (int id = 0; id < 1000000; id++) {
            final int noOfBathrooms = ThreadLocalRandom.current().nextInt(1, 5);
            final int noOfBedrooms = ThreadLocalRandom.current().nextInt(1, 5);
            final double latitude = ThreadLocalRandom.current().nextDouble(40.4, 50.5);
            final double longitude = ThreadLocalRandom.current().nextDouble(8.2, 8.3);
            final double price = ThreadLocalRandom.current().nextDouble(1000, 2000);
            Property property = new Property(id, noOfBedrooms, noOfBathrooms, latitude, longitude, price);
            store.addProperty(property);
        }

        // store.addProperty(new Property(1000000, 7, 3, 50.5, 8.2, 1000));
        System.out.println("Loading time:" + (System.currentTimeMillis() - loadStartTime) + "ms");
    }

    public static void main(String args[]) {
        PropertyStore store = new PropertyStore();
        Query[] query = new Query[2];
        query[0] = new Query(5, -1,  5, -1, 50.5, 8.2, 1000, -1);
        query[1] = new Query(5, 7,  2, 4, 50.5, 8.2, 1000, 2000);

        loadRandomProperties(store);

        // Peformance testing.
//        for (int i = 0; i < 100; i++) {
//            Query testQuery = new Query(ThreadLocalRandom.current().nextInt(1, 5),
//                                    -1,
//                                    ThreadLocalRandom.current().nextInt(1, 5),
//                                    -1,
//                                    ThreadLocalRandom.current().nextDouble(40.4, 50.5),
//                                    ThreadLocalRandom.current().nextDouble(8.2, 8.3),
//                                    ThreadLocalRandom.current().nextDouble(500, 2500),
//                                    -1);
//            store.matchProperties(testQuery, 10);
//        }

        for (int i = 0; i < query.length; i++) {
            List<PropertySpatialPoint> results = store.matchProperties(query[i], 10);

            System.out.println("Query[" + i + "] results.");
            for (PropertySpatialPoint point: results) {
                System.out.print("Match %" + PropertyMatcher.getMatchPercent(point.getProperty(), query[i]) + " " + point.getProperty());
                System.out.println(" Bath% " + PropertyMatcher.getBathroomsMatchScore(point.getProperty(), query[i]) +
                        " Bed% " + PropertyMatcher.getBedroomsMatchScore(point.getProperty(),query[i]) +
                        " Budget% " + PropertyMatcher.getBudgetMatchScore(point.getProperty(),query[i]) +
                        " Dis% " + PropertyMatcher.getRadiusMatchScore(point.getProperty(), query[i]));
            }
        }
    }
}
