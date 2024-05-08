package com.tech.healthconnect.services;


import com.tech.healthconnect.dto.ConsultationDTO;
import com.tech.healthconnect.models.Appointment;
import com.tech.healthconnect.models.Consultation;
import com.tech.healthconnect.repositories.AppointmentRepo;
import com.tech.healthconnect.repositories.ConsultationRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class ConsultationService {


    @Autowired
    AppointmentRepo appointmentRepo;
    @Autowired
    ConsultationRepo consultationRepo;

    @Transactional
    public void writeConsultation(Long appointmentId, ConsultationDTO consultationDTO) {
        Appointment appointment = appointmentRepo.findById(appointmentId)
                .orElseThrow(() -> new RuntimeException("Appointment not found"));

        Consultation consultation = new Consultation();
        consultation.setRapport(consultationDTO.getRapport());
        consultation.setDateConsultation(consultationDTO.getDateConsultation());


        appointment.setConsultation(consultation);
        consultation.setAppointment(appointment);

        consultationRepo.save(consultation);
    }

}
