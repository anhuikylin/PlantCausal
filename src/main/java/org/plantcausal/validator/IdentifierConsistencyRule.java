package org.plantcausal.validator;

import org.plantcausal.schema.ValidationResult;

import java.nio.file.Files;
import java.nio.file.Path;

public class IdentifierConsistencyRule implements PackageRule {

    @Override
    public void validate(Path packageDirectory, ValidationResult result) {
        Path phenotype = packageDirectory.resolve("phenotype.tsv");

        if (Files.exists(phenotype)) {
            result.addWarning("Identifier validation enabled after TSV parser integration");
        }
    }
}
