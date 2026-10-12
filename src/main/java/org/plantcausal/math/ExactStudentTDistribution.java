package org.plantcausal.math;

public class ExactStudentTDistribution {
    public static double twoSidedP(double t,int df){
        if(df<=0) throw new IllegalArgumentException("df");
        if(t==0) return 1.0;
        double x=df/(df+t*t);
        return RegularizedIncompleteBeta.evaluate(x,df/2.0,0.5);
    }
}
