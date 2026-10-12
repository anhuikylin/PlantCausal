package org.plantcausal.report;

import org.plantcausal.causal.CausalResult;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

/** Export causal analysis results. */
public class CausalReportExporter {

    public void exportJson(CausalResult result, Path output) throws IOException {
        String json = "{\n" +
                "  \"averageTreatmentEffect\": \"" + result + "\"\n" +
                "}";
        Files.writeString(output, json);
    }
}
