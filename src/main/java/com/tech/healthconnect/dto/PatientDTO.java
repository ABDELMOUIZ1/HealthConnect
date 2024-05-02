package com.tech.healthconnect.dto;

import com.tech.healthconnect.models.Specialization;
import com.tech.healthconnect.models.Title;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PatientDTO {
    // for searching, return
    private String patientFirstName;
    private String patientLastName;
    private Title title;
    private String patientContact;
    private String patientEmail;
    private String currentCity;

    private List<AppointmentDTO> appointments;
}
