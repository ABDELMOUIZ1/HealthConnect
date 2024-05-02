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
public class DoctorUpdateDTO {
    private Long doctorId;
    private String doctorFirstName;
    private String doctorLastName;
    private int age;
    private Title title;
    private String cityOfBirth;
    private String address;
    private String currentCity;
    private String doctorEmail;
    private String doctorPassword;
    private String doctorContact;
    private String officeAddress;
    private Specialization specialization;
    private Date startTimeOfWork;
    private Date endTimeOfWork;
}
