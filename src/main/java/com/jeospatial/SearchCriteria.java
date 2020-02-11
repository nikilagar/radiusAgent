package com.jeospatial;

/**
 * {@code SearchCriteria} can be applied to nearest-neighbor searches in a
 * {@link GeospatialPointDatabase} to filter the returned list of nearest
 * neighbors by arbitrary criteria beyond the built-in/implicit distance
 * criteria. Points in a {@link GeospatialPointDatabase} that match
 * supplementary search criteria may be included in the list of points returned
 * by a call to one of the database's search methods while points that do not
 * match the criteria will not be included.
 * 
 * @author <a href="mailto:jon.chambers@gmail.com">Jon Chambers</a>
 * 
 * @see GeospatialPointDatabase#getNearestNeighbor(GeospatialPoint, SearchCriteria)
 * @see GeospatialPointDatabase#getNearestNeighbor(GeospatialPoint, double, SearchCriteria)
 * @see GeospatialPointDatabase#getNearestNeighbors(GeospatialPoint, int, SearchCriteria)
 * @see GeospatialPointDatabase#getNearestNeighbors(GeospatialPoint, int, double, SearchCriteria)
 * @see GeospatialPointDatabase#getAllNeighborsWithinDistance(GeospatialPoint, double, SearchCriteria)
 */
public interface SearchCriteria<T extends GeospatialPoint> {
    /**
     * Tests whether an individual point matches an arbitrary set of
     * supplementary criteria. If the point matches and this method returns
     * {@code true}, the point may be included in search results from a
     * {@link GeospatialPointDatabase}. If the point does not match and this
     * method returns {@code false}, the point will not be included.
     * 
     * @param point
     *            the point to test against these criteria
     * 
     * @return {@code true} if the point matches these criteria or {@code false}
     *         otherwise
     */
    public boolean matches(T point);
}
