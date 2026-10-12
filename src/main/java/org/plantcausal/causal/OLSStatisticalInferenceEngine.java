package org.plantcausal.causal;

/**
 * OLS statistical inference layer.
 * Computes covariance, standard errors, t statistics and confidence intervals.
 */
public class OLSStatisticalInferenceEngine {

    public double[] residuals(double[][] x, double[] y, double[] beta) {
        double[] result = new double[y.length];
        for (int i = 0; i < y.length; i++) {
            double predicted = 0.0;
            for (int j = 0; j < beta.length; j++) predicted += x[i][j] * beta[j];
            result[i] = y[i] - predicted;
        }
        return result;
    }

    public double sse(double[] residuals) {
        double value = 0.0;
        for (double r : residuals) value += r * r;
        return value;
    }

    public double residualVariance(double[][] x, double[] residuals) {
        int df = x.length - x[0].length;
        return sse(residuals) / Math.max(1, df);
    }

    public double[][] covarianceMatrix(double[][] xtxInverse, double residualVariance) {
        double[][] result = new double[xtxInverse.length][xtxInverse.length];
        for (int i = 0; i < xtxInverse.length; i++) {
            for (int j = 0; j < xtxInverse.length; j++) {
                result[i][j] = xtxInverse[i][j] * residualVariance;
            }
        }
        return result;
    }

    public double[] standardErrors(double[][] covariance) {
        double[] result = new double[covariance.length];
        for (int i = 0; i < covariance.length; i++) {
            result[i] = Math.sqrt(Math.max(0, covariance[i][i]));
        }
        return result;
    }

    public double tStatistic(double coefficient, double standardError) {
        return standardError == 0 ? 0 : coefficient / standardError;
    }

    public double[] confidenceInterval(double coefficient, double standardError, double criticalValue) {
        return new double[]{coefficient - criticalValue * standardError,
                coefficient + criticalValue * standardError};
    }

    public double rSquared(double[] y, double[] residuals) {
        double mean = 0.0;
        for (double v : y) mean += v;
        mean /= y.length;
        double sst = 0.0;
        for (double v : y) sst += (v - mean) * (v - mean);
        return 1.0 - sse(residuals) / sst;
    }
}
