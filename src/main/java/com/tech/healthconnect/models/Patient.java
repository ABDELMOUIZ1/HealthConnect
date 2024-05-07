package com.tech.healthconnect.models;


import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Patient {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long patientId;
    private String patientFirstName;
    private String patientLastName;
    private int age;
    @Enumerated(EnumType.STRING)
    private Title title;
    @Column(nullable = false)
    private String cityOfBirth;
    private String address;
    private String currentCity;
    private String patientEmail;
    @Column(nullable = false)
    private String patientPassword;
    private String patientContact;
    @OneToMany(mappedBy = "patient", fetch = FetchType.EAGER)
    private List<Appointment> appointments;
}
