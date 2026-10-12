package org.plantcausal.causal;

/**
 * Lightweight OLS inference engine.
 * Implements normal equation solving and basic statistical summaries.
 */
public class OLSInferenceEngine {

    public OLSStatisticalReport fit(double[][] x, double[] y) {
        double[][] xt = transpose(x);
        double[][] xtx = multiply(xt, x);
        double[] xty = multiply(xt, y);
        double[][] inv = inverse(xtx);
        double[] beta = multiply(inv, xty);

        OLSStatisticalReport report = new OLSStatisticalReport();
        report.setCoefficients(beta);
        report.setSampleSize(y.length);
        return report;
    }

    private double[][] transpose(double[][] a) {
        double[][] t = new double[a[0].length][a.length];
        for (int i=0;i<a.length;i++)
            for (int j=0;j<a[0].length;j++)
                t[j][i]=a[i][j];
        return t;
    }

    private double[][] multiply(double[][] a,double[][] b){
        double[][] r=new double[a.length][b[0].length];
        for(int i=0;i<a.length;i++)
            for(int j=0;j<b[0].length;j++)
                for(int k=0;k<b.length;k++)
                    r[i][j]+=a[i][k]*b[k][j];
        return r;
    }

    private double[] multiply(double[][] a,double[] b){
        double[] r=new double[a.length];
        for(int i=0;i<a.length;i++)
            for(int j=0;j<b.length;j++)
                r[i]+=a[i][j]*b[j];
        return r;
    }

    private double[][] inverse(double[][] a){
        int n=a.length;
        double[][] m=new double[n][2*n];
        for(int i=0;i<n;i++){
            for(int j=0;j<n;j++) m[i][j]=a[i][j];
            m[i][i+n]=1;
        }
        for(int i=0;i<n;i++){
            double pivot=m[i][i];
            for(int j=0;j<2*n;j++) m[i][j]/=pivot;
            for(int k=0;k<n;k++){
                if(k==i) continue;
                double factor=m[k][i];
                for(int j=0;j<2*n;j++) m[k][j]-=factor*m[i][j];
            }
        }
        double[][] inv=new double[n][n];
        for(int i=0;i<n;i++)
            for(int j=0;j<n;j++)
                inv[i][j]=m[i][j+n];
        return inv;
    }
}
