package com.tech.healthconnect.services;



import com.tech.healthconnect.dto.AppointmentDTO;
import org.springframework.stereotype.Service;



import com.tech.healthconnect.dto.AvailableSlotDTO;
import com.tech.healthconnect.dto.CreateAppointmentDTO;
import com.tech.healthconnect.models.Appointment;
import com.tech.healthconnect.models.Doctor;
import com.tech.healthconnect.models.Patient;
import com.tech.healthconnect.models.StatusAppointment;
import com.tech.healthconnect.repositories.AppointmentRepo;
import com.tech.healthconnect.repositories.DoctorRepo;
import com.tech.healthconnect.repositories.PatientRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;


@Service
public class AppointmentService {


    @Autowired
    private AppointmentRepo appointmentRepo;
    @Autowired
    private DoctorRepo doctorRepo;
    @Autowired
    private PatientRepo patientRepo;

    public void takeAppointment(CreateAppointmentDTO createAppointmentDTO) {
        Appointment appointment = new Appointment();

        // Set date, status, patient, and doctor
        appointment.setDateOnly(createAppointmentDTO.getDateOnly());
        appointment.setStatusAppointment(StatusAppointment.PENDING);

        Optional<Patient> optionalPatient = patientRepo.findById(createAppointmentDTO.getPatientId());
        optionalPatient.ifPresent(appointment::setPatient);

        Optional<Doctor> optionalDoctor = doctorRepo.findById(createAppointmentDTO.getDoctorId());
        optionalDoctor.ifPresent(appointment::setDoctor);

        // Set available slot
        AvailableSlotDTO availableSlotDTO = createAppointmentDTO.getAvailableSlotDTO();
        appointment.setStartTime(availableSlotDTO.getStartTime());
        appointment.setEndTime(availableSlotDTO.getEndTime());

        // Save the appointment
        appointmentRepo.save(appointment);
    }
    public List<AppointmentDTO> getAppointmentsByPatientId(Long patientId) {
        return appointmentRepo.findDTOByPatientId(patientId);
    }

}
