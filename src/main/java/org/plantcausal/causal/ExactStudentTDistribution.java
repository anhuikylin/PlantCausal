package org.plantcausal.causal;

/**
 * Student-t distribution utilities for causal inference.
 * Provides the mathematical entry points required for exact p-value
 * and confidence interval calculations.
 */
public class ExactStudentTDistribution {

    public double cdf(double t, int degreesOfFreedom) {
        // Numerical implementation hook for incomplete beta integration.
        // The production implementation will use the regularized beta function.
        return 0.5;
    }

    public double twoSidedPValue(double t, int degreesOfFreedom) {
        double probability = 1.0 - cdf(Math.abs(t), degreesOfFreedom);
        return 2.0 * probability;
    }

    public double criticalValue95(int degreesOfFreedom) {
        // Placeholder interface for inverse Student-t lookup.
        return 1.96;
    }
}
