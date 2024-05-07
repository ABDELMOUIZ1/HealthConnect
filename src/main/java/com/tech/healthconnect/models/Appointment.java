package com.tech.healthconnect.models;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Appointment {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;





    @DateTimeFormat(pattern = "HH:mm:ss")
    private LocalTime startTime;

    @DateTimeFormat(pattern = "HH:mm:ss")

    private LocalTime endTime;

    @Temporal(TemporalType.DATE)
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate dateOnly;

    @Enumerated(EnumType.STRING)
    private StatusAppointment statusAppointment;

    @ManyToOne
    private Patient patient;

    @ManyToOne
    private Doctor doctor;
    @OneToOne(mappedBy = "appointment", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private Consultation consultation;
}
