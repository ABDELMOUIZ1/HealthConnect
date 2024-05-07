package com.tech.healthconnect.PdfGenerator;

import org.springframework.core.io.InputStreamResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;

@RestController
public class PDFController {
    private static final String TEMP_DIR_PATH = System.getProperty("java.io.tmpdir"); // Utilise le répertoire temporaire système

    @GetMapping("/patients/consultation-report/{patientId}/download")
    public ResponseEntity<InputStreamResource> downloadPDF(@PathVariable Long patientId) throws IOException {
        String reportContent = "Sample report content for patient with ID " + patientId;
        String pdfFilePath = PDFGenerator.generatePDF(reportContent, TEMP_DIR_PATH);

        File file = new File(pdfFilePath);
        InputStreamResource resource = new InputStreamResource(new FileInputStream(file));

        HttpHeaders headers = new HttpHeaders();
        headers.add(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=report.pdf");

        return ResponseEntity.ok()
                .headers(headers)
                .contentLength(file.length())
                .contentType(MediaType.APPLICATION_PDF)
                .body(resource);
    }

}
