package org.plantcausal.service;

import org.plantcausal.schema.ValidationResult;

import java.util.Map;

public class ValidationService {

    public ValidationResult validate(Map<String, Object> trial) {

        ValidationResult result = new ValidationResult();

        if (trial == null) {
            result.addError("Target trial object is null");
            return result;
        }

        require(trial, "trialId", result);
        require(trial, "species", result);
        require(trial, "population", result);
        require(trial, "intervention", result);
        require(trial, "outcomes", result);

        if (!trial.containsKey("dataSource")) {
            result.addWarning("No data source provenance was provided");
        }

        return result;
    }

    private void require(Map<String, Object> object, String key, ValidationResult result) {
        if (!object.containsKey(key) || object.get(key) == null) {
            result.addError("Missing required field: " + key);
        }
    }
}
