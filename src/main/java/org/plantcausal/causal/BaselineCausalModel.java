package org.plantcausal.causal;

import org.plantcausal.data.PlantCausalDataset;
import org.plantcausal.parser.TsvTable;

import java.util.List;
import java.util.Map;

/**
 * Baseline causal estimator using difference in mean outcomes.
 *
 * Treatment coding convention:
 * treatment column: 0/1
 * outcome column: numeric
 */
public class BaselineCausalModel implements CausalModel {

    @Override
    public CausalResult estimate(PlantCausalDataset dataset){

        CausalResult result = new CausalResult();

        Object table = dataset.getTables().get("phenotype");
        if (!(table instanceof TsvTable)) {
            result.setAverageTreatmentEffect(0.0);
            result.setDiagnostics("Phenotype table not found. Expected TsvTable.");
            return result;
        }

        TsvTable phenotype = (TsvTable) table;
        List<String> headers = phenotype.getHeaders();

        int treatmentIndex = headers.indexOf("treatment");
        int outcomeIndex = headers.indexOf("outcome");

        if (treatmentIndex < 0 || outcomeIndex < 0) {
            result.setDiagnostics("Required columns missing: treatment and outcome.");
            return result;
        }

        double treatedSum = 0;
        double controlSum = 0;
        int treatedN = 0;
        int controlN = 0;

        for (List<String> row : phenotype.getRows()) {
            try {
                int treatment = Integer.parseInt(row.get(treatmentIndex));
                double outcome = Double.parseDouble(row.get(outcomeIndex));

                if (treatment == 1) {
                    treatedSum += outcome;
                    treatedN++;
                } else {
                    controlSum += outcome;
                    controlN++;
                }
            } catch (Exception ignored) {
            }
        }

        if (treatedN == 0 || controlN == 0) {
            result.setDiagnostics("Insufficient treatment groups for estimation.");
            return result;
        }

        double ate = treatedSum / treatedN - controlSum / controlN;

        result.setAverageTreatmentEffect(ate);
        result.setDiagnostics(
                "Difference-in-means estimator. Treated=" + treatedN + ", Control=" + controlN
        );

        return result;
    }
}
