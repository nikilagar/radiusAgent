package com.radius.jeospatial.util;

import java.util.Comparator;

/**
 * <p>A Comparator that reverse the sorting order imposed by another Comparator.
 * In other words, if an existing Comparator sorted numbers in ascending order,
 * a ReverseComparator for that Comparator would sort numbers in descending
 * order.</p>
 * 
 * @author <a href="mailto:jon.chambers@gmail.com">Jon Chambers</a>
 */
public class ReverseComparator<T> implements Comparator<T> {
	private final Comparator<T> delegate;
	
	/**
	 * Constructs a new ReverseComparator that sorts objects in the opposite
	 * order of the given original Comparator.
	 * 
	 * @param originalComparator
	 *            the comparator for which to create a reverse comparator
	 */
	public ReverseComparator(final Comparator<T> originalComparator) {
		this.delegate = originalComparator;
	}
	
	/**
	 * Compares its two arguments for order. Reverses the sort order yielded by
	 * the underlying original Comparator.
	 * 
	 * @param a
	 *            the first object to compare
	 * @param b
	 *            the second object to compare
	 */
	@Override
	public int compare(final T a, final T b) {
		return this.delegate.compare(b, a);
	}
}
