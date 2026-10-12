package org.plantcausal.report;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

/**
 * PDF report export interface.
 * The rendering backend can be connected to a PDF library later.
 */
public class PdfCausalReportExporter {

    public void export(String reportText, Path output) throws IOException {
        Files.writeString(output, reportText);
    }
}
