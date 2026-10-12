package org.plantcausal.validator;

import org.plantcausal.schema.ValidationResult;
import java.nio.file.Path;

public class UnitConsistencyRule implements PackageRule {

    @Override
    public void validate(Path packageDirectory, ValidationResult result) {
        if (packageDirectory == null) {
            result.addError("Package path is missing");
        }
        // Unit ontology validation will be connected with Measurement schema.
    }
}
