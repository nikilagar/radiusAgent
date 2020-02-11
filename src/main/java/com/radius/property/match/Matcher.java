package com.radius.property.match;

public class Matcher {
    private static double getDeprecatedValue(double error, double allowableError)
    {
        return (allowableError + 1 - error) / (allowableError + 1);
    }

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
                return getDeprecatedValue(actual - expectedMax, errorRight);
            }
            else {
                return getDeprecatedValue(expectedMin - actual, errorRight);
            }
        }
    }
}
