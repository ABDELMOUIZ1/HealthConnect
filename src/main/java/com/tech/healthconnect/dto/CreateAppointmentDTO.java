package com.tech.healthconnect.dto;

import com.tech.healthconnect.models.Consultation;
import com.tech.healthconnect.models.Doctor;
import com.tech.healthconnect.models.Patient;
import com.tech.healthconnect.models.StatusAppointment;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


import java.time.LocalDate;
import java.time.LocalTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CreateAppointmentDTO {

    private LocalTime startTime;
    private AvailableSlotDTO availableSlotDTO;
    private StatusAppointment statusAppointment;
    private Long patientId;
    private Long doctorId;
    private LocalDate dateOnly;


}
