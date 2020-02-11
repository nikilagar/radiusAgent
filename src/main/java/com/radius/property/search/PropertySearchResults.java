package com.radius.property.search;

import com.radius.jeospatial.SearchCriteria;
import com.radius.jeospatial.util.ReverseComparator;
import com.radius.property.match.PropertyMatcher;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.PriorityQueue;

public class PropertySearchResults <E extends PropertySpatialPoint> extends PriorityQueue<E> {
    private final Query query;
    private final int maxSize;
    private final SearchCriteria<E> criteria;

    public PropertySearchResults(Query query, int maxSize) {
        this(query, maxSize, null);
    }

    public PropertySearchResults(Query query, int maxSize, SearchCriteria<E> criteria) {
        super(maxSize, new PropertyMatcher<E>(query));

        this.query = query;
        this.maxSize = maxSize;
        this.criteria = criteria;
    }

    @Override
    public boolean add(E point) {
        if(size() < maxSize) {
            if(criteria == null || criteria.matches(point)) {
                return super.add(point);
            }
        } else {
            if(PropertyMatcher.getMatchPercent(point.getProperty(), query) > getHighestMatchScore()) {
                if(criteria == null || criteria.matches(point)) {
                    poll();
                    return super.add(point);
                }
            }
        }

        return false;
    }

    @Override
    public boolean addAll(Collection<? extends E> points) {
        if(equals(points)) {
            throw new IllegalArgumentException("Cannot addAll of a queue to itself.");
        }

        boolean anyAdded = false;

        for(E point : points) {
            boolean added = add(point);
            if(added) { anyAdded = true; }
        }

        return anyAdded;
    }

    public double getHighestMatchScore() {
        if(isEmpty()) {
            return Double.NEGATIVE_INFINITY;
        }

        return PropertyMatcher.getMatchPercent(peek().getProperty(), query);
    }

    public List<E> toSortedList() {
        ArrayList<E> sortedList = new ArrayList<E>(this);
        java.util.Collections.sort(sortedList, new ReverseComparator<E>(new PropertyMatcher<E>(query)));

        return sortedList;
    }
}
