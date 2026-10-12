package org.plantcausal.causal;

/**
 * Statistical inference utilities for OLS coefficients.
 */
public class OLSConfidenceInterval {

    public double[] confidenceInterval(double beta, double se, double criticalValue) {
        return new double[]{
            beta - criticalValue * se,
            beta + criticalValue * se
        };
    }

    public double twoSidedPValue(double tStatistic, int degreesOfFreedom) {
        // Placeholder distribution adapter.
        // Student-t implementation will be provided by the statistics module.
        return Double.NaN;
    }
}
