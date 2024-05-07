package com.tech.healthconnect.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.time.LocalTime;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class AvailableSlotDTO {
    private LocalTime startTime;
    private LocalTime endTime;


}
