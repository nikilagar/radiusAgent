package com.radius.property.search;

/**
 * Return if the given values are valid i.e. lies between the extreme values.
 */
public class Filter {
    public static boolean isWithinLimits(double actual,
                                         double expectedMin,
                                         double expectedMax,
                                         double errorLeft,
                                         double errorRight)
    {
        return actual >= expectedMin - errorLeft && actual <= expectedMax + errorRight;
    }
}
