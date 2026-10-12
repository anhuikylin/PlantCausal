package org.plantcausal.causal;

public class OLSStatisticalReport {
    private double coefficient;
    private double standardError;
    private double tStatistic;
    private double pValue;
    private double rSquared;

    public double getCoefficient(){ return coefficient; }
    public void setCoefficient(double coefficient){ this.coefficient = coefficient; }

    public double getStandardError(){ return standardError; }
    public void setStandardError(double standardError){ this.standardError = standardError; }

    public double getTStatistic(){ return tStatistic; }
    public void setTStatistic(double tStatistic){ this.tStatistic = tStatistic; }

    public double getPValue(){ return pValue; }
    public void setPValue(double pValue){ this.pValue = pValue; }

    public double getRSquared(){ return rSquared; }
    public void setRSquared(double rSquared){ this.rSquared = rSquared; }
}
