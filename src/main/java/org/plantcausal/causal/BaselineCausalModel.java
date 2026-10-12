package org.plantcausal.causal;

import org.plantcausal.data.PlantCausalDataset;

/**
 * Initial causal engine implementation placeholder.
 * Provides a stable API for future causal estimators.
 */
public class BaselineCausalModel implements CausalModel {

    @Override
    public CausalResult estimate(PlantCausalDataset dataset){

        CausalResult result = new CausalResult();
        result.setAverageTreatmentEffect(0.0);
        result.setDiagnostics(
            "Baseline estimator initialized for dataset: " + dataset.getDatasetId()
        );

        return result;
    }
}
