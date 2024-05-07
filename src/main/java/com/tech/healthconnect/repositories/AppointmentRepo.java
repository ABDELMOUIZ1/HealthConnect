package com.tech.healthconnect.repositories;

import com.tech.healthconnect.dto.AppointmentDTO;
import com.tech.healthconnect.models.Appointment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;

import java.util.Date;

import java.util.List;

@Repository

    public interface AppointmentRepo extends JpaRepository<Appointment, Long> {
    @Query("SELECT new com.tech.healthconnect.dto.AppointmentDTO(a.startTime, a.endTime, a.dateOnly, a.statusAppointment) " +
            "FROM Appointment a " +
            "WHERE a.patient.patientId = :patientId")
    List<AppointmentDTO> findDTOByPatientId(@Param("patientId") Long patientId);



    List<Appointment> findAppointmentByDateOnly(LocalDate date);




}



