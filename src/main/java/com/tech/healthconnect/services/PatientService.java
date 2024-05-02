package com.tech.healthconnect.services;


import com.tech.healthconnect.dto.*;
import com.tech.healthconnect.models.Appointment;
import com.tech.healthconnect.models.Doctor;
import com.tech.healthconnect.models.Patient;
import com.tech.healthconnect.repositories.DoctorRepo;
import com.tech.healthconnect.repositories.PatientRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class PatientService {
    @Autowired
    private PatientRepo patientRepo;


    private PatientDTO convertToPatientDTO(Patient patient) {
        PatientDTO patientDTO = new PatientDTO();
        patientDTO.setPatientFirstName(patient.getPatientFirstName());
        patientDTO.setPatientLastName(patient.getPatientLastName());
        patientDTO.setTitle(patient.getTitle());
        patientDTO.setPatientContact(patient.getPatientContact());
        patientDTO.setPatientEmail(patient.getPatientEmail());
        patientDTO.setCurrentCity(patient.getCurrentCity());
        patientDTO.setAppointments(convertAppointmentsToDTOs(patient.getAppointments()));
        return patientDTO;
    }

    private List<AppointmentDTO> convertAppointmentsToDTOs(List<Appointment> appointments) {
        if (appointments != null) { // Add null check here
            return appointments.stream()
                    .map(this::convertToAppointmentDTO)
                    .collect(Collectors.toList());
        } else {
            return Collections.emptyList(); // Return an empty list if appointments is null
        }
    }


    private AppointmentDTO convertToAppointmentDTO(Appointment appointment) {
        AppointmentDTO appointmentDTO = new AppointmentDTO();
        appointmentDTO.setStartTime(appointment.getStartTime());
        appointmentDTO.setEndTime(appointment.getEndTime());
        appointmentDTO.setStatusAppointment(appointment.getStatusAppointment());
        return appointmentDTO;
    }

    // Creating patient
    public PatientDTO createPatient(CreatePatientDTO createPatientDTO) {
        Patient patient = convertToPatientEntity(createPatientDTO);
        Patient savedPatient = patientRepo.save(patient);
        return convertToPatientDTO(savedPatient);
    }

    private Patient convertToPatientEntity(CreatePatientDTO createPatientDTO) {
        Patient patient = new Patient();
        patient.setPatientFirstName(createPatientDTO.getPatientFirstName());
        patient.setPatientLastName(createPatientDTO.getPatientLastName());
        patient.setTitle(createPatientDTO.getTitle());
        patient.setPatientContact(createPatientDTO.getPatientContact());
        patient.setPatientEmail(createPatientDTO.getPatientEmail());
        patient.setCurrentCity(createPatientDTO.getCurrentCity());
        patient.setCityOfBirth(createPatientDTO.getCityOfBirth());
        patient.setAddress(createPatientDTO.getAddress());
        patient.setPatientPassword(createPatientDTO.getPatientPassword());
        patient.setAge(createPatientDTO.getAge());
        return patient;
    }

    // Deleting patient
    @Transactional
    public void deletePatient(Long id) {
        patientRepo.deleteById(id);
    }

    // Updating patient info
    public PatientDTO updatePatient(Long patientId, PatientUpdateDTO patientUpdateDTO) {
        Patient patient = patientRepo.findById(patientId).orElseThrow(() -> new RuntimeException("Patient not found"));
        // Update patient fields based on DTO
        patient.setPatientId(patientUpdateDTO.getPatientId());
        patient.setPatientFirstName(patientUpdateDTO.getPatientFirstName());
        patient.setPatientLastName(patientUpdateDTO.getPatientLastName());
        patient.setAge(patientUpdateDTO.getAge());
        patient.setTitle(patientUpdateDTO.getTitle());
        patient.setCityOfBirth(patientUpdateDTO.getCityOfBirth());
        patient.setAddress(patientUpdateDTO.getAddress());
        patient.setCurrentCity(patientUpdateDTO.getCurrentCity());
        patient.setPatientEmail(patientUpdateDTO.getPatientEmail());
        patient.setPatientPassword(patientUpdateDTO.getPatientPassword());
        patient.setPatientContact(patientUpdateDTO.getPatientContact());
        Patient savedPatient = patientRepo.save(patient);
        return convertToPatientDTO(savedPatient);
    }
}

