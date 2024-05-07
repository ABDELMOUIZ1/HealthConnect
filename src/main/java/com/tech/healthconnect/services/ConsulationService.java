package com.tech.healthconnect.services;


import com.tech.healthconnect.models.Appointment;
import com.tech.healthconnect.models.Consultation;
import com.tech.healthconnect.models.Doctor;
import com.tech.healthconnect.models.StatusAppointment;
import com.tech.healthconnect.repositories.AppointmentRepo;
import com.tech.healthconnect.repositories.ConsultationRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ConsulationService {

    @Autowired
    private AppointmentRepo appointmentRepository;

    @Autowired
    private ConsultationRepo consultationRepository;

    public String getConsultationReport(Long appointmentId) {
        Appointment appointment = appointmentRepository.findById(appointmentId).orElse(null);
        if (appointment != null && appointment.getStatusAppointment() == StatusAppointment.DONE) {
            Consultation consultation = appointment.getConsultation();
            if (consultation != null) {
                return consultation.getRapport();
            }
        }
        return null; // Report not available or appointment status is not DONE
    }
}

