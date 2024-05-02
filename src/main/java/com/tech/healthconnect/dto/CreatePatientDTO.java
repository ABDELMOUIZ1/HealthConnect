package com.tech.healthconnect.dto;

import com.tech.healthconnect.models.Specialization;
import com.tech.healthconnect.models.Title;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@NoArgsConstructor
@AllArgsConstructor
public class CreatePatientDTO {
    //creating doctor
    private String patientFirstName;
    private String patientLastName;
    private Title title;
    private String patientContact;
    private String patientEmail;
    private String currentCity;
    private String cityOfBirth;
    private int age;
    private String address;
    private String patientPassword;
}
