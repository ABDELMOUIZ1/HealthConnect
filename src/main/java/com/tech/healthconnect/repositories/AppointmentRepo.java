package com.tech.healthconnect.repositories;

import com.tech.healthconnect.models.Appointment;
import com.tech.healthconnect.models.Doctor;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface AppointmentRepo extends JpaRepository<Appointment, Long> {

}
