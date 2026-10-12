package org.plantcausal.causal;

/**
 * Regularized incomplete beta integration layer used by Student-t inference.
 *
 * I_x(a,b) = B(x;a,b) / B(a,b)
 */
public class RegularizedIncompleteBetaEngine {

    private final BetaContinuedFraction continuedFraction = new BetaContinuedFraction();

    public double evaluate(double x, double a, double b) {
        if (x <= 0.0) return 0.0;
        if (x >= 1.0) return 1.0;

        double bt = Math.exp(
                LanczosGamma.logGamma(a + b)
                - LanczosGamma.logGamma(a)
                - LanczosGamma.logGamma(b)
                + a * Math.log(x)
                + b * Math.log(1.0 - x)
        );

        if (x < (a + 1.0) / (a + b + 2.0)) {
            return bt * continuedFraction.evaluate(x, a, b) / a;
        }
        return 1.0 - bt * continuedFraction.evaluate(1.0 - x, b, a) / b;
    }
}
