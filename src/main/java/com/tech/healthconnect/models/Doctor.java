package com.tech.healthconnect.models;


import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.Date;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Doctor {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long doctorId;
    private String doctorFirstName;
    private String doctorLastName;
    private int age;
    @Enumerated(EnumType.STRING)
    private Title title;
    @Column(nullable = false)
    private String cityOfBirth;
    private String address;
    private String currentCity;
    private String doctorEmail;
    @Column(nullable = false)
    private String doctorPassword;
    private String doctorContact;
    private String officeAddress;
    @Enumerated(EnumType.STRING)
    private Specialization specialization;
    @DateTimeFormat(pattern = "HH:mm:ss")
    private LocalTime startTimeOfWork;
    @DateTimeFormat(pattern = "HH:mm:ss")
    private LocalTime endTimeOfWork;

    @OneToMany(mappedBy = "doctor", fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    private List<Appointment> appointments;

}
