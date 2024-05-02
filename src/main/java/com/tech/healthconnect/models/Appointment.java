package com.tech.healthconnect.models;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Appointment {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Temporal(TemporalType.TIMESTAMP) // Changed to TIMESTAMP to include time
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss") // Pattern for date and time
    private Date startTime;

    @Temporal(TemporalType.TIMESTAMP) // Changed to TIMESTAMP to include time
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss") // Pattern for date and time
    private Date endTime;

    @Enumerated(EnumType.STRING)
    private StatusAppointment statusAppointment;

    @ManyToOne
    private Patient patient;

    @ManyToOne
    private Doctor doctor;
    @OneToOne(mappedBy = "appointment", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private Consultation consultation;
}
