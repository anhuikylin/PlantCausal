package org.plantcausal.validator;

import org.plantcausal.schema.ValidationResult;

import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;

/**
 * Validator for complete PlantCausal data packages.
 *
 * A package may contain protocol.json, phenotype.tsv,
 * genotype.tsv and omics measurement tables.
 */
public class DataPackageValidator {

    private final List<PackageRule> rules = new ArrayList<>();

    public DataPackageValidator() {
        rules.add(new RequiredFileRule());
        rules.add(new IdentifierConsistencyRule());
    }

    public ValidationResult validate(Path packageDirectory) {
        ValidationResult result = new ValidationResult();

        if (packageDirectory == null || !Files.exists(packageDirectory)) {
            result.addError("Data package directory does not exist");
            return result;
        }

        for (PackageRule rule : rules) {
            rule.validate(packageDirectory, result);
        }

        return result;
    }
}
