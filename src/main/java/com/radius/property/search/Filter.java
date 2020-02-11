package com.radius.property.search;

/**
 * Filter used to filter out bad results.
 */
public class Filter {

    /**
     * @return true if the given values are valid i.e. lies between the extreme values.
     */
    public static boolean isWithinLimits(double actual,
                                         double expectedMin,
                                         double expectedMax,
                                         double errorLeft,
                                         double errorRight)
    {
        return actual >= expectedMin - errorLeft && actual <= expectedMax + errorRight;
    }

    public static boolean isWithinLimits(int actual,
                                         int expectedMin,
                                         int expectedMax,
                                         int errorLeft,
                                         int errorRight)
    {
        return actual >= expectedMin - errorLeft && actual <= expectedMax + errorRight;
    }
}
