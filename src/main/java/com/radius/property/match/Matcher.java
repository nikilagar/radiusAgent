package com.radius.property.match;

/**
 * Logic for calculating match percentage based on difference between the expected
 * and actual value.
 */
public class Matcher {
    /**
     * Calculate the score based on actual and expected value diff. Follow linear
     * depreciation.
     */
    private static double getReducedScore(double error, double allowableError)
    {
        return (allowableError + 1 - error) * 1.0 / (allowableError + 1);
    }

    /**
     * @return 0 <= x <= 1, 1 if full match.
     */
    public static double getMatchScore(double actual,
                                       double expectedMin,
                                       double expectedMax,
                                       double errorLeft,
                                       double errorRight)
    {
        if (actual >= expectedMin && actual <= expectedMax) {
            return 1;
        }
        else {
            if (actual >= expectedMin) {
                return getReducedScore(actual - expectedMax, errorRight);
            }
            else {
                return getReducedScore(expectedMin - actual, errorRight);
            }
        }
    }

    public static double getMatchScore(int actual,
                                       int expectedMin,
                                       int expectedMax,
                                       int errorLeft,
                                       int errorRight)
    {
        if (actual >= expectedMin && actual <= expectedMax) {
            return 1;
        }
        else {
            if (actual >= expectedMin) {
                return getReducedScore(actual - expectedMax, errorRight);
            }
            else {
                return getReducedScore(expectedMin - actual, errorRight);
            }
        }
    }
}
