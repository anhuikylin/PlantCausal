package org.plantcausal.validator;

import org.plantcausal.schema.ValidationResult;

import java.nio.file.Path;

/**
 * JSON schema validation entry point.
 * Full JSON Schema draft support can be plugged in later.
 */
public class JsonSchemaValidator {

    public ValidationResult validate(Path jsonFile) {
        ValidationResult result = new ValidationResult();

        if (jsonFile == null) {
            result.addError("JSON schema input file is missing");
        }

        return result;
    }
}
