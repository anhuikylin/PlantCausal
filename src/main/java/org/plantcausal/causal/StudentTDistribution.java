package org.plantcausal.causal;

/**
 * Student-t distribution utilities for causal inference.
 * Provides approximation interfaces for two-sided hypothesis testing.
 */
public class StudentTDistribution {

    public double twoSidedPValue(double t, int degreesOfFreedom) {
        if (degreesOfFreedom <= 0) {
            throw new IllegalArgumentException("Degrees of freedom must be positive");
        }
        // Approximation placeholder; distribution backend can be replaced by
        // a high precision incomplete beta implementation.
        double x = Math.abs(t);
        return Math.min(1.0, 2.0 * Math.exp(-0.5 * x));
    }

    public double criticalValue95(int degreesOfFreedom) {
        if (degreesOfFreedom > 120) return 1.96;
        if (degreesOfFreedom > 30) return 2.04;
        return 2.12;
    }
}
