package com.radius;

import com.radius.property.Property;
import com.radius.property.search.Query;
import com.radius.property.PropertyStore;

import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.concurrent.ThreadLocalRandom;

public class App {

    private static void loadRandomPoints(PropertyStore store) {
        long loadStartTime = System.currentTimeMillis();
        for (int id = 0; id < 1000000; id++) {
            final int noOfBathrooms = ThreadLocalRandom.current().nextInt(1, 4);
            final int noOfBedrooms = ThreadLocalRandom.current().nextInt(1, 6);
            final double latitude = ThreadLocalRandom.current().nextDouble(40.4D, 50.5D);
            final double longitude = ThreadLocalRandom.current().nextDouble(8.2D, 8.3D);
            final double price = ThreadLocalRandom.current().nextDouble(1000D, 2000D);
            Property property = new Property(id, noOfBedrooms, noOfBathrooms, latitude, longitude, price);
            store.addProperty(property);
        }
        // store.addProperty(new Property(1000000, 7, 3, 50.5, 8.2, 1000));
        System.out.println("Loading time:" + (System.currentTimeMillis() - loadStartTime) + "ms");
    }

    public static void main(String args[]) {
        PropertyStore store = new PropertyStore();
        loadRandomPoints(store);
        store.matchProperties(new Query(5,
                                        7,
                                        2,
                                        4,
                                        50.5,
                                        8.2,
                                        1000,
                                        2000),
                              10);
    }
}
