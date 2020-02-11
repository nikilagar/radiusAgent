package com.radius.property.search;

import com.jeospatial.util.SimpleGeospatialPoint;
import com.radius.property.Property;

/**
 * Simple point in a plane that can store corresponding Property object.
 */
public class PropertySpatialPoint extends SimpleGeospatialPoint {
    private final Property property;

    public PropertySpatialPoint(Property property,
                                double latitude,
                                double longitude)
    {
        super(latitude, longitude);
        this.property = property;
    }

    public Property getProperty() {
        return property;
    }

    @Override
    public boolean equals(Object obj) {
        return property.equals(obj);
    }
}
