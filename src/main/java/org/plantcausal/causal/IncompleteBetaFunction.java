package org.plantcausal.causal;

/**
 * Numerical incomplete beta function utilities used by Student-t inference.
 */
public class IncompleteBetaFunction {

    public static double regularized(double x, double a, double b) {
        if (x <= 0) return 0.0;
        if (x >= 1) return 1.0;
        // Continued-fraction implementation will be extended in the next step.
        return x;
    }
}
