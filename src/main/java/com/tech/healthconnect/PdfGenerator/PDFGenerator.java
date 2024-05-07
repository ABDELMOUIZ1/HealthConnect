package com.tech.healthconnect.PdfGenerator;

import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.pdmodel.PDPage;
import org.apache.pdfbox.pdmodel.PDPageContentStream;
import org.apache.pdfbox.pdmodel.font.PDType1Font;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public class PDFGenerator {
        public static String generatePDF(String content, String tempDirPath) throws IOException {
        Path tempDir = Paths.get(tempDirPath);
        Files.createDirectories(tempDir); // Ensure the directory exists

        PDDocument document = new PDDocument();
        PDPage page = new PDPage();
        document.addPage(page);

        PDPageContentStream contentStream = new PDPageContentStream(document, page);
        contentStream.beginText();
        contentStream.setFont(PDType1Font.HELVETICA_BOLD, 12);
        contentStream.newLineAtOffset(50, 700);
        contentStream.showText(content);
        contentStream.endText();
        contentStream.close();

        String pdfFilePath = tempDir.resolve("report.pdf").toString();
        document.save(pdfFilePath);
        document.close();

        // Optionally, return the path to the generated PDF
        return pdfFilePath;
    }
}
