package com.tech.healthconnect.dto;

import com.tech.healthconnect.models.Appointment;
import jakarta.persistence.*;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDate;

public class CreateConsultationDTO {


    private LocalDate dateConsultation;
    private String rapport;
    private Long appointmentId;
}
