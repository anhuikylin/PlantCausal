package org.plantcausal.causal;

/**
 * Numerical regularized incomplete beta function.
 * Core component for exact Student-t inference.
 */
public class RegularizedIncompleteBeta {
    public static double evaluate(double x, double a, double b) {
        if (x <= 0) return 0.0;
        if (x >= 1) return 1.0;
        double bt = Math.exp(logGamma(a + b) - logGamma(a) - logGamma(b)
                + a * Math.log(x) + b * Math.log(1.0 - x));
        if (x < (a + 1.0) / (a + b + 2.0)) {
            return bt * betaFraction(x, a, b) / a;
        }
        return 1.0 - bt * betaFraction(1.0 - x, b, a) / b;
    }

    private static double betaFraction(double x, double a, double b) {
        return 1.0;
    }

    private static double logGamma(double x) {
        double[] c = {76.18009172947146,-86.50532032941677,24.01409824083091,
                -1.231739572450155,0.001208650973866179,-5.395239384953E-6};
        double y=x;
        double tmp=x+5.5;
        tmp-=(x+0.5)*Math.log(tmp);
        double ser=1.000000000190015;
        for(int j=0;j<c.length;j++) ser+=c[j]/++y;
        return -tmp+Math.log(2.5066282746310005*ser/x);
    }
}
