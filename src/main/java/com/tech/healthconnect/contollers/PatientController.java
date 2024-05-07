package com.tech.healthconnect.contollers;

import com.tech.healthconnect.PdfGenerator.PDFGenerator;
import com.tech.healthconnect.dto.*;
import com.tech.healthconnect.services.ConsulationService;
import com.tech.healthconnect.services.PatientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.InputStreamResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;

@RestController
@RequestMapping("/patients")
public class PatientController {

    @Autowired
    PatientService patientService;
    @Autowired
    private ConsulationService consultationService;


    @PostMapping("/create")
    public ResponseEntity<PatientDTO> createPatient(@RequestBody CreatePatientDTO createPatientDTO) {

        PatientDTO patientDTO = patientService.createPatient(createPatientDTO);
        return ResponseEntity.ok(patientDTO);
    }
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<String> deletePatient(@PathVariable Long id) {
        patientService.deletePatient(id);
        return ResponseEntity.status(HttpStatus.OK).body("Patient with ID " + id + " deleted successfully");
    }
    @PutMapping("/{patientId}")
    public ResponseEntity<PatientDTO> updatePatient(@PathVariable Long patientId, @RequestBody PatientUpdateDTO patientUpdateDTO) {
        PatientDTO patientDTO = patientService.updatePatient(patientId, patientUpdateDTO);
        return ResponseEntity.status(HttpStatus.OK).body(patientDTO);
    }
    @GetMapping("/patients/consultation-report/{patientId}/download")
    public ResponseEntity<InputStreamResource> downloadPDF(@PathVariable Long patientId) throws IOException {
        String reportContent = "Sample report content for patient with ID " + patientId;
        String tempDirPath = System.getProperty("java.io.tmpdir"); // Use the system's temporary directory
        String pdfFilePath = PDFGenerator.generatePDF(reportContent, tempDirPath);

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

