package com.tech.healthconnect.dto;

import com.tech.healthconnect.models.StatusAppointment;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.time.LocalTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ListAppointmentDTO {
    private Long appointmentId;
    private LocalTime startTime;
    private LocalTime endTime;
    private LocalDate dateOnly;
    private StatusAppointment statusAppointment;
    private DoctorDTO doctor;
    private PatientDTO patient;
    private ConsultationDTO consultation;
}
