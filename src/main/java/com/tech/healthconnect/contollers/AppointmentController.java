package com.tech.healthconnect.contollers;


import com.tech.healthconnect.dto.AppointmentDTO;
import com.tech.healthconnect.dto.CreateAppointmentDTO;
import com.tech.healthconnect.models.Appointment;
import com.tech.healthconnect.services.AppointmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/appointments")
public class AppointmentController {


    @Autowired
    private AppointmentService appointmentService;
    @PostMapping("/take")
    public ResponseEntity<Void> takeAppointment(@RequestBody CreateAppointmentDTO createAppointmentDTO) {
        appointmentService.takeAppointment(createAppointmentDTO);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }


    @GetMapping("/listAppointments/{patientId}")
    public List<AppointmentDTO> getAppointmentsByPatientId(@PathVariable Long patientId) {
        return appointmentService.getAppointmentsByPatientId(patientId);
    }

}

