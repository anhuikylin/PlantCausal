package org.plantcausal.causal;

public class CausalResult {

    private double averageTreatmentEffect;
    private String diagnostics;

    public double getAverageTreatmentEffect(){
        return averageTreatmentEffect;
    }

    public void setAverageTreatmentEffect(double value){
        this.averageTreatmentEffect = value;
    }

    public String getDiagnostics(){
        return diagnostics;
    }

    public void setDiagnostics(String diagnostics){
        this.diagnostics = diagnostics;
    }
}
