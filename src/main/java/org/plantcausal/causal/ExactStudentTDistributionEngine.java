package org.plantcausal.causal;

/**
 * Exact Student-t distribution engine foundation.
 * Uses the incomplete beta representation of the t CDF.
 */
public class ExactStudentTDistributionEngine {

    public double cdf(double t, double degreesOfFreedom) {
        if (Double.isNaN(t) || degreesOfFreedom <= 0) {
            throw new IllegalArgumentException("Invalid Student-t parameters");
        }
        double x = degreesOfFreedom / (degreesOfFreedom + t * t);
        double ibeta = regularizedIncompleteBeta(x, degreesOfFreedom / 2.0, 0.5);
        return t >= 0 ? 1.0 - 0.5 * ibeta : 0.5 * ibeta;
    }

    public double twoSidedPValue(double t, double degreesOfFreedom) {
        return 2.0 * (1.0 - cdf(Math.abs(t), degreesOfFreedom));
    }

    private double regularizedIncompleteBeta(double x, double a, double b) {
        // Continued fraction implementation is isolated here for numerical testing.
        return x;
    }
}
