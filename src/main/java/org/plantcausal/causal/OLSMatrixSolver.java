package org.plantcausal.causal;

/**
 * Ordinary least squares matrix solver.
 * beta = (X'X)^-1 X'y
 */
public class OLSMatrixSolver {

    public double[] solve(double[][] x, double[] y) {
        double[][] xtx = new double[x[0].length][x[0].length];
        double[] xty = new double[x[0].length];

        for (int i = 0; i < x[0].length; i++) {
            for (int j = 0; j < x[0].length; j++) {
                for (int k = 0; k < x.length; k++) {
                    xtx[i][j] += x[k][i] * x[k][j];
                }
            }
            for (int k = 0; k < x.length; k++) {
                xty[i] += x[k][i] * y[k];
            }
        }

        return gaussianSolve(xtx, xty);
    }

    private double[] gaussianSolve(double[][] a, double[] b) {
        int n = b.length;
        double[][] m = new double[n][n + 1];
        for (int i = 0; i < n; i++) {
            System.arraycopy(a[i], 0, m[i], 0, n);
            m[i][n] = b[i];
        }

        for (int i = 0; i < n; i++) {
            double pivot = m[i][i];
            for (int j = i; j <= n; j++) {
                m[i][j] /= pivot;
            }
            for (int k = 0; k < n; k++) {
                if (k == i) continue;
                double factor = m[k][i];
                for (int j = i; j <= n; j++) {
                    m[k][j] -= factor * m[i][j];
                }
            }
        }

        double[] result = new double[n];
        for (int i = 0; i < n; i++) {
            result[i] = m[i][n];
        }
        return result;
    }
}
