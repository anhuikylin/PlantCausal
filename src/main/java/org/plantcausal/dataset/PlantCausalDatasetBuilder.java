package org.plantcausal.dataset;

import java.util.*;

/**
 * Builds a PlantCausalDataset from parsed benchmark tables.
 */
public class PlantCausalDatasetBuilder {

    public PlantCausalDataset build(List<Map<String, String>> phenotype,
                                    List<Map<String, String>> genotype,
                                    List<Map<String, String>> environment) {
        PlantCausalDataset dataset = new PlantCausalDataset();
        dataset.setPhenotype(phenotype);
        dataset.setGenotype(genotype);
        dataset.setEnvironment(environment);
        return dataset;
    }
}
