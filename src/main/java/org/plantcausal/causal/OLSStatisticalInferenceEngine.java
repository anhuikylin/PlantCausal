package org.plantcausal.causal;

/**
 * Complete OLS inference calculation layer.
 * Provides residual, SSE, SST and R2 calculations.
 */
public class OLSStatisticalInferenceEngine {

    public double[] residuals(double[][] x, double[] y, double[] beta) {
        double[] result = new double[y.length];
        for (int i = 0; i < y.length; i++) {
            double predicted = 0.0;
            for (int j = 0; j < beta.length; j++) {
                predicted += x[i][j] * beta[j];
            }
            result[i] = y[i] - predicted;
        }
        return result;
    }

    public double sse(double[] residuals) {
        double value = 0.0;
        for (double r : residuals) {
            value += r * r;
        }
        return value;
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
