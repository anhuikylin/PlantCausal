package org.plantcausal.loader;

import org.plantcausal.benchmark.PlantCausalPackage;
import org.plantcausal.data.PlantCausalDataset;
import org.plantcausal.data.PlantCausalDatasetBuilder;

import java.nio.file.Files;
import java.nio.file.Path;

/**
 * Default loader for PlantCausal standardized packages.
 */
public class DefaultPackageLoader {

    public PlantCausalPackage inspect(Path directory){
        PlantCausalPackage pkg = new PlantCausalPackage();
        pkg.setRoot(directory);

        if(Files.exists(directory.resolve("protocol.json")))
            pkg.setProtocol(directory.resolve("protocol.json"));

        if(Files.exists(directory.resolve("phenotype.tsv")))
            pkg.setPhenotype(directory.resolve("phenotype.tsv"));

        if(Files.exists(directory.resolve("genotype.tsv")))
            pkg.setGenotype(directory.resolve("genotype.tsv"));

        if(Files.exists(directory.resolve("environment.tsv")))
            pkg.setEnvironment(directory.resolve("environment.tsv"));

        if(Files.exists(directory.resolve("omics.tsv")))
            pkg.setOmics(directory.resolve("omics.tsv"));

        return pkg;
    }

    public PlantCausalDataset load(Path directory){
        PlantCausalPackage pkg = inspect(directory);

        return new PlantCausalDatasetBuilder()
                .addTable("package", pkg)
                .build(directory.getFileName().toString());
    }
}
