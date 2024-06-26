package com.tech.healthconnect.dto;

import com.tech.healthconnect.models.Specialization;
import com.tech.healthconnect.models.Title;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.Date;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class DoctorDTO {
    // for searching, return
    private Long doctorId;
    private String doctorFirstName;
    private String doctorLastName;
    private Title title;
    private String doctorContact;
    private String doctorEmail;
    private String currentCity;
    private String officeAddress;
    private Specialization specialization;



    private List<AppointmentDTO> appointments;

    private LocalTime startTimeOfWork;
    private LocalTime endTimeOfWork;

}