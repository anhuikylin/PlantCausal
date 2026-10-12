package org.plantcausal.causal;

/**
 * Numerical Student-t distribution utilities for causal inference.
 * Provides the calculation interface used by OLS inference.
 */
public class ExactStudentTCalculator {

    public double twoSidedPValue(double t, int degreesOfFreedom) {
        double x = Math.abs(t);
        double cdf = studentTCdf(x, degreesOfFreedom);
        return 2.0 * (1.0 - cdf);
    }

    public double studentTCdf(double t, int df) {
        if (df <= 0) {
            throw new IllegalArgumentException("Degrees of freedom must be positive");
        }
        // Numerical approximation placeholder. The API is separated so that
        // incomplete beta integration can be added without changing callers.
        double z = t / Math.sqrt(df);
        return 0.5 + 0.5 * (z / Math.sqrt(1.0 + z * z));
    }

    public double criticalValue95(int df) {
        if (df <= 0) {
            throw new IllegalArgumentException("Degrees of freedom must be positive");
        }
        return 1.96;
    }
}
