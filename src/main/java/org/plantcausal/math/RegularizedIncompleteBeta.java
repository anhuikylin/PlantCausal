package org.plantcausal.math;

/** Regularized incomplete beta function. */
public class RegularizedIncompleteBeta {
    public static double evaluate(double x,double a,double b){
        if(x<=0)return 0; if(x>=1)return 1;
        double bt=Math.exp(GammaFunction.logGamma(a+b)-GammaFunction.logGamma(a)-GammaFunction.logGamma(b)+a*Math.log(x)+b*Math.log(1-x));
        if(x<(a+1)/(a+b+2)) return bt*BetaContinuedFraction.evaluate(a,b,x)/a;
        return 1-bt*BetaContinuedFraction.evaluate(b,a,1-x)/b;
    }
}
