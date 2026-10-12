package org.plantcausal.causal;

/**
 * Student-t inference engine.
 *
 * Provides two-sided p-value calculation and delegates the numerical tail
 * probability to the regularized incomplete beta implementation when it is
 * available.
 */
public class StudentTInferenceEngine {

    public double twoSidedPValue(double t, int degreesOfFreedom) {
        if (degreesOfFreedom <= 0) {
            throw new IllegalArgumentException("Degrees of freedom must be positive");
        }
        double x = Math.abs(t);
        if (x == 0.0) return 1.0;
        double z = degreesOfFreedom / (degreesOfFreedom + x * x);
        return Math.max(0.0, Math.min(1.0, regularizedBeta(z, degreesOfFreedom / 2.0, 0.5)));
    }

    private double regularizedBeta(double x, double a, double b) {
        if (x <= 0) return 0.0;
        if (x >= 1) return 1.0;
        double term = 1.0;
        double sum = term;
        for (int n = 1; n < 200; n++) {
            term *= ((a + n - 1) * x) / n;
            sum += term;
            if (Math.abs(term) < 1e-14) break;
        }
        double scale = Math.pow(x, a) * Math.pow(1 - x, b);
        return scale * sum;
    }
}
