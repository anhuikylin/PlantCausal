package org.plantcausal.validator;

import org.plantcausal.schema.ValidationResult;
import java.nio.file.Path;

public class TimeConsistencyRule implements PackageRule {

    @Override
    public void validate(Path packageDirectory, ValidationResult result) {
        if (packageDirectory == null) {
            result.addError("Package path is missing");
        }
        // Time relationship validation hook.
        // Actual experiment event parsing is applied after event schema is enabled.
    }
}
