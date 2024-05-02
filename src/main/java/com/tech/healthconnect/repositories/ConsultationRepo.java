package com.tech.healthconnect.repositories;

import com.tech.healthconnect.models.Consultation;
import com.tech.healthconnect.models.Doctor;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ConsultationRepo extends JpaRepository<Consultation, Long> {

}
