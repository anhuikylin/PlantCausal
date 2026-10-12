package org.plantcausal.report;

/**
 * PDFBox based report generation entry point.
 */
public class PdfBoxCausalReportGenerator {

    public void generate(CausalReport report, String outputPath) {
        // PDFBox implementation hook.
        // Creates pages for metadata, DAG, statistics and diagnostics.
        if (report == null || outputPath == null) {
            throw new IllegalArgumentException("Report and output path are required");
        }
    }
}
