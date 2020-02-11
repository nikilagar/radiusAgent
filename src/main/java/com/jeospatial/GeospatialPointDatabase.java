package com.jeospatial;

import java.util.Collection;
import java.util.PriorityQueue;

/**
 * A GeospatialPointDatabase is a collection of geospatial points that can be
 * queried to locate points that are spatially close to a given query point.
 * 
 * @author <a href="mailto:jon.chambers@gmail.com">Jon Chambers</a>
 */
public interface GeospatialPointDatabase<E extends GeospatialPoint> extends Collection<E> {
    /**
     * Returns a list of all points within a given distance to a query point
     * that meet a set of search criteria.
     * 
     * @param query
     *            the point for which to find neighbors
     * @param maxDistance
     *            the maximum allowable distance, in meters, from the query
     *            point; points farther away than {@code maxDistance} will not
     *            be included in the returned list
     *
     * @return a list of all points within the given distance to the query point
     *         that meet the given search criteria; the returned list is sorted
     *         in order of increasing distance from the query point
     */
    public PriorityQueue<E> getAllNeighborsWithinDistance(GeospatialPoint query,
                                                          double maxDistance,
                                                          PriorityQueue<E> results);
    
    /**
     * Moves a point in the database to the given coordinates. Points in a
     * database should only ever be moved via this method (or its counterparts)
     * rather than the point's {@code setLatitude} or {@code setLongitude}
     * methods.
     * 
     * @param point the point to move
     * @param latitude the new latitude for the given point
     * @param longitude the new longitude for the given point
     * 
     * @see GeospatialPoint#setLatitude(double)
     * @see GeospatialPoint#setLongitude(double)
     */
    public void movePoint(E point, double latitude, double longitude);
    
    /**
     * Moves a point in the database to position of the given destination point.
     * Points in a database should only ever be moved via this method (or its
     * counterparts) rather than the point's {@code setLatitude} or
     * {@code setLongitude} methods.
     * 
     * @param point the point to move
     * @param destination the location to which the given point should be moved
     * 
     * @see GeospatialPoint#setLatitude(double)
     * @see GeospatialPoint#setLongitude(double)
     */
    public void movePoint(E point, GeospatialPoint destination);
}
