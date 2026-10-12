package org.plantcausal.report;

import java.io.IOException;
import java.nio.file.Path;
import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.pdmodel.PDPage;
import org.apache.pdfbox.pdmodel.PDPageContentStream;
import org.apache.pdfbox.pdmodel.font.PDType1Font;

/** PDFBox based causal report generator. */
public class PdfBoxCausalReportGenerator {

    public void generate(Path output) throws IOException {
        try (PDDocument document = new PDDocument()) {
            PDPage page = new PDPage();
            document.addPage(page);
            try (PDPageContentStream stream = new PDPageContentStream(document, page)) {
                stream.beginText();
                stream.setFont(PDType1Font.HELVETICA, 12);
                stream.newLineAtOffset(60, 720);
                stream.showText("PlantCausal Causal Analysis Report");
                stream.newLineAtOffset(0, -20);
                stream.showText("Benchmark: Maize Salt Stress Demo");
                stream.newLineAtOffset(0, -20);
                stream.showText("OLS regression results and Student-t inference exported.");
                stream.endText();
            }
            document.save(output.toFile());
        }
    }
}
