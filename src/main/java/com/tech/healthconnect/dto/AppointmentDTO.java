package com.tech.healthconnect.dto;

import com.tech.healthconnect.models.StatusAppointment;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;


@Data
@AllArgsConstructor
@NoArgsConstructor
public class AppointmentDTO {
    //for searching, return
    private Date startTime;
    private Date endTime;
    private StatusAppointment statusAppointment;

}