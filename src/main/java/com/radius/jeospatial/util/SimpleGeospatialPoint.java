package com.radius.jeospatial.util;

import com.radius.jeospatial.GeospatialPoint;

/**
 * <p>A simple geospatial point implementation. Simple geospatial points
 * calculate distance to other points using the Haversine Formula.</p>
 * 
 * @author <a href="mailto:jon.chambers@gmail.com">Jon Chambers</a>
 */
public class SimpleGeospatialPoint implements GeospatialPoint {
	/**
	 * <p>The radius of the earth in meters; all implementing whose distance
	 * calculations rely upon the radius of the earth should reference this
	 * value.</p>
	 *
	 * <p>The earth, for the purposes of this library, is considered to be a
	 * sphere with a radius of {@value #EARTH_RADIUS} meters.</p>
	 *
	 * @see GeospatialPoint#getDistanceTo(GeospatialPoint)
	 */
	private static final double EARTH_RADIUS = 6371 * 1000; // meters

	private double latitude;
	private double longitude;
	
    /**
     * Constructs a new geospatial point at the given latitude and longitude
     * coordinates.
     * 
     * @param latitude the latitude of this point in degrees
     * @param longitude the longitude of this point in degrees
     * 
     * @throws IllegalArgumentException
     *             if the given latitude is outside of the allowable range
     * 
     * @see GeospatialPoint#setLatitude(double)
     */
	public SimpleGeospatialPoint(double latitude, double longitude) {
		this.setLatitude(latitude);
		this.setLongitude(longitude);
	}
	
	/**
	 * Constructs a new geospatial point at the same coordinates as the given
	 * point.
	 * 
	 * @param p the point whose location should be used for this point
	 */
	public SimpleGeospatialPoint(GeospatialPoint p) {
		this(p.getLatitude(), p.getLongitude());
	}
	
    /**
     * Sets the latitude of this point.
     * 
     * @param latitude the latitude of this point in degrees
     * 
     * @throws IllegalArgumentException
     *             if the given latitude is outside of the allowable range
     * 
     * @see GeospatialPoint#setLatitude(double)
     */
	@Override
	public void setLatitude(double latitude) {
	    if(latitude < -90 || latitude > 90) {
	        throw new IllegalArgumentException("Latitude must be in the range -90 (inclusive) to +90 (inclusive).");
	    }
	    
		this.latitude = latitude;
	}
	
	/**
	 * Returns the latitude of this point.
	 * 
	 * @return the latitude of this point in degrees
	 */
	@Override
	public double getLatitude() {
		return this.latitude;
	}
	
	/**
	 * Sets the longitude of this point.
	 * 
	 * @param longitude the longitude of this point in degrees
	 */
	@Override
	public void setLongitude(double longitude) {
		this.longitude = ((longitude + 180) % 360) - 180;
	}
	
	/**
	 * Returns the longitude of this point.
	 * 
	 * @return the longitude of this point in degrees
	 */
	@Override
	public double getLongitude() {
		return this.longitude;
	}
	
	/**
	 * Returns the "great circle" distance to another geospatial point.
	 * 
	 * @param otherPoint the other point to which to calculate distance 
	 * 
	 * @return the great circle distance, in meters, between the two points
	 */
	public double getDistanceTo(GeospatialPoint otherPoint) {
		return this.getDistanceTo(otherPoint.getLatitude(), otherPoint.getLongitude());
	}
	
    /**
     * Returns the "great circle" distance to another geospatial point.
     * 
     * @param latitude
     *            the latitude, in degrees, of the other point to which to
     *            calculate distance
     * @param longitude
     *            the longitude, in degrees, of the other point to which to
     *            calculate distance
     * 
     * @return the great circle distance, in meters, between the two points
     */
	@Override
    public double getDistanceTo(double latitude, double longitude) {
	    double lat1 = Math.toRadians(this.getLatitude());
        double lon1 = Math.toRadians(this.getLongitude());
        double lat2 = Math.toRadians(latitude);
        double lon2 = Math.toRadians(longitude);
        
        double angle = 2 * Math.asin(Math.min(1, Math.sqrt(this.haversine(lat2 - lat1) + Math.cos(lat1) * Math.cos(lat2) * this.haversine(lon2 - lon1))));
        
        return angle * EARTH_RADIUS;
    }

    /**
	 * Returns the haversine of the given angle.
	 * 
	 * @param theta the angle, in radians, for which to calculate the haversine
	 * 
	 * @return the haversine of the given angle
	 * 
	 * @see http://en.wikipedia.org/wiki/Versine
	 */
	private double haversine(double theta) {
	    double x = Math.sin(theta / 2);
	    
	    return (x * x);
	}

	/**
	 * Returns a human-readable {@code String} representation of this point.
	 * 
	 * @return a {@code String} representation of this point
	 */
	@Override
	public String toString() {
		return "SimpleGeospatialPoint [latitude=" + latitude + ", longitude="
				+ longitude + "]";
	}

	/**
	 * Generates a hash code value for this point.
	 * 
	 * @return a hash code value for this point
	 */
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		long temp;
		temp = Double.doubleToLongBits(this.getLatitude());
		result = prime * result + (int) (temp ^ (temp >>> 32));
		temp = Double.doubleToLongBits(this.getLongitude());
		result = prime * result + (int) (temp ^ (temp >>> 32));
		return result;
	}
	
    /**
     * Compares this point to another object. The other object is considered
     * equal if it is not {@code null}, is also a {@code SimpleGeospatialPoint}
     * (or a subclass thereof), and has the same latitude and longitude as this
     * point.
     * 
     * @return {@code true} if the other object is equal to this point or
     *         {@code false} otherwise
     */
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if(!(obj instanceof GeospatialPoint))
		    return false;
		GeospatialPoint other = (GeospatialPoint) obj;
		if (Double.doubleToLongBits(latitude) != Double
				.doubleToLongBits(other.getLatitude()))
			return false;
		if (Double.doubleToLongBits(longitude) != Double
				.doubleToLongBits(other.getLongitude()))
			return false;
		return true;
	}
}
