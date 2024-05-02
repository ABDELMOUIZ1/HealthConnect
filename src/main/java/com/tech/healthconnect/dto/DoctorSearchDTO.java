package com.tech.healthconnect.dto;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class DoctorSearchDTO {
    //for searching , fields
    private String doctorFirstName;
    private String doctorLastName;
    private String currentCity;
    private String officeAddress;
    private String specialization;

}
