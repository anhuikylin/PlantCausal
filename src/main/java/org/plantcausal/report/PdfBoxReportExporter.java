package org.plantcausal.report;

import java.io.File;
import java.io.IOException;

/**
 * PDF export integration layer.
 * The implementation keeps report generation independent from analysis logic.
 */
public class PdfBoxReportExporter {
    public File export(CausalReport report, File output) throws IOException {
        if (report == null || output == null) {
            throw new IllegalArgumentException("Report and output are required");
        }
        // PDFBox backend is connected here in the next integration step.
        return output;
    }
}
