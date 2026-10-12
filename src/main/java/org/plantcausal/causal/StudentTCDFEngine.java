package org.plantcausal.causal;

/**
 * Student-t cumulative distribution engine.
 *
 * Computes p-values through the regularized incomplete beta formulation.
 */
public class StudentTCDFEngine {

    public double cdf(double t, int degreesOfFreedom) {
        if (degreesOfFreedom <= 0) {
            throw new IllegalArgumentException("degreesOfFreedom must be positive");
        }
        double x = degreesOfFreedom / (degreesOfFreedom + t * t);
        double beta = regularizedBeta(x, degreesOfFreedom / 2.0, 0.5);
        if (t >= 0) {
            return 1.0 - 0.5 * beta;
        }
        return 0.5 * beta;
    }

    public double twoSidedPValue(double t, int degreesOfFreedom) {
        return 2.0 * (1.0 - cdf(Math.abs(t), degreesOfFreedom));
    }

    private double regularizedBeta(double x, double a, double b) {
        // Continued-fraction implementation will replace this numerical core.
        if (x <= 0) return 0.0;
        if (x >= 1) return 1.0;
        return Math.pow(x, a) * Math.pow(1 - x, b);
    }
}
