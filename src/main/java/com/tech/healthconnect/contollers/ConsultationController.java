package com.tech.healthconnect.contollers;

import com.tech.healthconnect.dto.ConsultationDTO;
import com.tech.healthconnect.services.ConsultationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/consultation")
public class ConsultationController {

    @Autowired
    ConsultationService consultationService;

    @PostMapping("/write/{appointmentId}")
    public ResponseEntity<String> writeConsultation(@PathVariable Long appointmentId, @RequestBody ConsultationDTO consultationDTO) {
        consultationService.writeConsultation(appointmentId, consultationDTO);
        return ResponseEntity.status(HttpStatus.CREATED).body("Consultation written successfully");
    }
}
