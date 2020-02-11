package com.radius.property;

import com.radius.property.search.PropertySpatialPoint;

public class Property {
    private int id, noOfBedrooms, noOfBathrooms;
    private double price;

    private PropertySpatialPoint point;

    /**
     * CTOR.
     */
    public Property(int id,
                    int noOfBedrooms,
                    int noOfBathrooms,
                    double latitude,
                    double longitude,
                    double price) {
        this.id = id;
        this.noOfBedrooms = noOfBedrooms;
        this.noOfBathrooms = noOfBathrooms;
        this.price = price;
        point = new PropertySpatialPoint(this, latitude, longitude);
    }

    public int getId() {
        return id;
    }

    public int getNoOfBedrooms() {
        return noOfBedrooms;
    }

    public int getNoOfBathrooms() {
        return noOfBathrooms;
    }

    public double getPrice() {
        return price;
    }

    public PropertySpatialPoint getSpatialPoint() {
        return point;
    }

    @Override
    public String toString() {
        return "Id: " + id +
               " Bedrooms: " + noOfBedrooms +
               " Bathrooms: " + noOfBathrooms +
               " Price: " + price +
               " Latitude: " + point.getLatitude() +
               " Longitude: " + point.getLongitude();
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if(!(obj instanceof Property))
            return false;
        Property other = (Property) obj;
        return this.id == other.getId();
    }
}