package com.tech.healthconnect.contollers;

import com.tech.healthconnect.dto.CreateAppointmentDTO;
import com.tech.healthconnect.dto.ListAppointmentDTO;
import com.tech.healthconnect.models.StatusAppointment;
import com.tech.healthconnect.services.AppointmentService;
import com.tech.healthconnect.services.DoctorService;
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
    @Autowired
    private DoctorService doctorService;
    @PostMapping("/take")
    public ResponseEntity<Void> takeAppointment(@RequestBody CreateAppointmentDTO createAppointmentDTO) {
        appointmentService.takeAppointment(createAppointmentDTO);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @GetMapping("listByStatus/{doctorId}")
    public ResponseEntity<List<ListAppointmentDTO>> listAppointmentsByStatus(
            @PathVariable Long doctorId,
            @RequestParam(required = false) StatusAppointment status
    ) {
        if (status == null) {
            // Default to PENDING status if not provided
            status = StatusAppointment.PENDING;
        }

        List<ListAppointmentDTO> appointments = appointmentService.listAppointmentsByStatus(doctorId, status);
        return ResponseEntity.ok(appointments);
    }

}

