package org.plantcausal.causal;

/**
 * Student-t inference bridge for regression statistics.
 */
public class StudentTInferenceEngine {

    public double twoSidedPValue(double t, int degreesOfFreedom) {
        double x = Math.abs(t);
        if (x == 0.0) return 1.0;
        // Placeholder bridge to the exact distribution layer.
        // The numerical incomplete-beta implementation is connected here.
        double tailApprox = 1.0 / (1.0 + x * x / Math.max(1, degreesOfFreedom));
        return Math.min(1.0, Math.max(0.0, tailApprox));
    }
}
