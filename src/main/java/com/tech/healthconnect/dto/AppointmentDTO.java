package com.tech.healthconnect.dto;

import com.tech.healthconnect.models.StatusAppointment;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.Date;


@Data
@AllArgsConstructor
@NoArgsConstructor
public class AppointmentDTO {
    //for searching, return
    private LocalTime startTime;
    private LocalTime endTime;
    private LocalDate dateOnly;
    private StatusAppointment statusAppointment;


}
