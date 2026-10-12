package org.plantcausal.dataset;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.*;

public class TSVPlantCausalDatasetLoader {

    public PlantCausalDataset load(Path packagePath) throws IOException {
        PlantCausalDataset dataset = new PlantCausalDataset();
        dataset.setPackagePath(packagePath.toString());
        dataset.setPhenotype(readTSV(packagePath.resolve("phenotype.tsv")));
        dataset.setGenotype(readTSV(packagePath.resolve("genotype.tsv")));
        dataset.setEnvironment(readTSV(packagePath.resolve("environment.tsv")));
        return dataset;
    }

    private List<Map<String,String>> readTSV(Path file) throws IOException {
        List<String> lines = Files.readAllLines(file);
        if (lines.isEmpty()) return new ArrayList<>();
        String[] header = lines.get(0).split("\\t");
        List<Map<String,String>> rows = new ArrayList<>();
        for (int i = 1; i < lines.size(); i++) {
            String[] values = lines.get(i).split("\\t");
            Map<String,String> row = new LinkedHashMap<>();
            for (int j = 0; j < header.length && j < values.length; j++) {
                row.put(header[j], values[j]);
            }
            rows.add(row);
        }
        return rows;
    }
}
