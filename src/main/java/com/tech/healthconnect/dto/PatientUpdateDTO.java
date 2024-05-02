package com.tech.healthconnect.dto;

import com.tech.healthconnect.models.Specialization;
import com.tech.healthconnect.models.Title;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@NoArgsConstructor
@AllArgsConstructor
public class PatientUpdateDTO {

    private Long patientId;
    private String patientFirstName;
    private String patientLastName;
    private int age;
    private Title title;
    private String cityOfBirth;
    private String address;
    private String currentCity;
    private String patientEmail;
    private String patientPassword;
    private String patientContact;
}
