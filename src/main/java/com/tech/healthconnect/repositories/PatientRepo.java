package com.tech.healthconnect.repositories;

import com.tech.healthconnect.models.Patient;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PatientRepo extends JpaRepository<Patient, Long> {
}
