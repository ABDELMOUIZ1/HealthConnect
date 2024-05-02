package com.tech.healthconnect.contollers;

import com.tech.healthconnect.dto.*;
import com.tech.healthconnect.services.PatientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/patients")
public class PatientController {

    @Autowired
    PatientService patientService;

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
}
