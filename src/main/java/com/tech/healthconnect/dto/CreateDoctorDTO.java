package com.tech.healthconnect.dto;

import com.tech.healthconnect.models.Specialization;
import com.tech.healthconnect.models.Title;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CreateDoctorDTO {
    //creating doctor
    private String doctorFirstName;
    private String doctorLastName;
    private Title title;
    private String doctorContact;
    private String doctorEmail;
    private String currentCity;
    private String officeAddress;
    private Specialization specialization;
    private String cityOfBirth;
    private int age;
    private String address;
    private String doctorPassword;
    private Date startTimeOfWork;
    private Date endTimeOfWork;


}