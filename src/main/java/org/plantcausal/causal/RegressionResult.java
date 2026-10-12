package org.plantcausal.causal;

/**
 * Regression output including inference statistics.
 */
public record RegressionResult(
        double[] coefficients,
        double[] standardErrors,
        double[] tStatistics,
        double[] pValues,
        double[] lower95,
        double[] upper95
) {}
