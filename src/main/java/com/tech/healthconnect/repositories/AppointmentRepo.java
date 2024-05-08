package com.tech.healthconnect.repositories;

import com.tech.healthconnect.models.Appointment;
import com.tech.healthconnect.models.Doctor;
import com.tech.healthconnect.models.StatusAppointment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.time.LocalDate;
import java.util.List;

public interface AppointmentRepo extends JpaRepository<Appointment, Long> {

    List<Appointment> findAppointmentByDateOnly(LocalDate date);

    List<Appointment> findByDoctorAndStatusAppointment(Doctor doctor, StatusAppointment status);
}