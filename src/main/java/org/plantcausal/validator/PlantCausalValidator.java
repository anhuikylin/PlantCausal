package org.plantcausal.validator;

import org.plantcausal.benchmark.BenchmarkPackageLoader.BenchmarkPackage;

/**
 * Validates benchmark package structure before causal analysis.
 */
public class PlantCausalValidator {

    public ValidationResult validate(BenchmarkPackage pkg) {
        if (pkg == null || pkg.location() == null || pkg.location().isBlank()) {
            return new ValidationResult(false, "Missing benchmark package");
        }
        return new ValidationResult(true, "Package validation passed");
    }

    public record ValidationResult(boolean valid, String message) {}
}
