package org.plantcausal.causal;

public class OLSRegressionSolver {

    public double[] solve(double[][] x, double[] y) {
        int rows = x.length;
        int cols = x[0].length;

        double[][] xtx = new double[cols][cols];
        double[] xty = new double[cols];

        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                xty[j] += x[i][j] * y[i];
                for (int k = 0; k < cols; k++) {
                    xtx[j][k] += x[i][j] * x[i][k];
                }
            }
        }

        return gaussianSolve(xtx, xty);
    }

    private double[] gaussianSolve(double[][] a, double[] b) {
        int n = b.length;
        for (int i = 0; i < n; i++) {
            double pivot = a[i][i];
            for (int j = i; j < n; j++) {
                a[i][j] /= pivot;
            }
            b[i] /= pivot;

            for (int r = 0; r < n; r++) {
                if (r == i) continue;
                double factor = a[r][i];
                for (int c = i; c < n; c++) {
                    a[r][c] -= factor * a[i][c];
                }
                b[r] -= factor * b[i];
            }
        }
        return b;
    }
}
