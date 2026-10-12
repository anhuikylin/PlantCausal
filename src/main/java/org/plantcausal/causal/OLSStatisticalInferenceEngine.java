package org.plantcausal.causal;

/**
 * OLS statistical inference layer.
 * Produces RegressionResult from fitted coefficients.
 */
public class OLSStatisticalInferenceEngine {

    public RegressionResult infer(double[][] x, double[] y, double[] beta, double[][] xtxInverse) {
        double[] residuals = residuals(x, y, beta);
        double variance = residualVariance(x, residuals);
        double[][] covariance = covarianceMatrix(xtxInverse, variance);
        double[] se = standardErrors(covariance);
        double[] t = new double[beta.length];
        double[] p = new double[beta.length];
        double[][] ci = new double[beta.length][2];
        StudentTInferenceEngine engine = new StudentTInferenceEngine();
        int df = Math.max(1, x.length - x[0].length);
        for (int i = 0; i < beta.length; i++) {
            t[i] = tStatistic(beta[i], se[i]);
            p[i] = engine.twoSidedPValue(t[i], df);
            ci[i] = confidenceInterval(beta[i], se[i], 1.96);
        }
        return new RegressionResult(beta, se, t, p, ci, rSquared(y, residuals));
    }

    public double[] residuals(double[][] x, double[] y, double[] beta) {
        double[] result = new double[y.length];
        for (int i = 0; i < y.length; i++) {
            for (int j = 0; j < beta.length; j++) result[i] += x[i][j] * beta[j];
            result[i] = y[i] - result[i];
        }
        return result;
    }

    public double residualVariance(double[][] x, double[] residuals) {
        double sse = 0;
        for (double r : residuals) sse += r*r;
        return sse / Math.max(1, x.length - x[0].length);
    }

    public double[][] covarianceMatrix(double[][] inv, double variance) {
        double[][] c = new double[inv.length][inv.length];
        for (int i=0;i<inv.length;i++) for(int j=0;j<inv.length;j++) c[i][j]=inv[i][j]*variance;
        return c;
    }

    public double[] standardErrors(double[][] c) {
        double[] s=new double[c.length];
        for(int i=0;i<c.length;i++) s[i]=Math.sqrt(Math.max(0,c[i][i]));
        return s;
    }

    public double tStatistic(double b,double se){return se==0?0:b/se;}

    public double[] confidenceInterval(double b,double se,double critical){return new double[]{b-critical*se,b+critical*se};}

    public double rSquared(double[] y,double[] residuals){
        double mean=0; for(double v:y) mean+=v; mean/=y.length;
        double sst=0,sse=0; for(int i=0;i<y.length;i++){sst+=(y[i]-mean)*(y[i]-mean);sse+=residuals[i]*residuals[i];}
        return 1.0-sse/sst;
    }
}
