package com.tech.healthconnect.services;


import com.tech.healthconnect.dto.*;
import com.tech.healthconnect.models.*;
import com.tech.healthconnect.repositories.AppointmentRepo;
import com.tech.healthconnect.repositories.DoctorRepo;
import com.tech.healthconnect.repositories.PatientRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;

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

    public List<ListAppointmentDTO> listAppointmentsByStatus(Long doctorId, StatusAppointment status) {
        Doctor doctor = doctorRepo.findById(doctorId)
                .orElseThrow(() -> new RuntimeException("Doctor not found"));

        List<Appointment> appointments = appointmentRepo.findByDoctorAndStatusAppointment(doctor, status);

        List<ListAppointmentDTO> appointmentDTOS = appointments.stream()
                .map(this::convertToAppointmentDTO)
                .collect(Collectors.toList());

        for(ListAppointmentDTO appointment : appointmentDTOS){
            System.out.println(appointment);
        }
        return appointments.stream()
                .map(this::convertToAppointmentDTO)
                .collect(Collectors.toList());
    }


    private ListAppointmentDTO convertToAppointmentDTO(Appointment appointment) {
        ListAppointmentDTO appointmentDTO = new ListAppointmentDTO();
        appointmentDTO.setAppointmentId(appointment.getId());
        appointmentDTO.setStartTime(appointment.getStartTime());
        appointmentDTO.setEndTime(appointment.getEndTime());
        appointmentDTO.setDateOnly(appointment.getDateOnly());
        appointmentDTO.setStatusAppointment(appointment.getStatusAppointment());
        Doctor doctor = appointment.getDoctor();
        Patient patient = appointment.getPatient();
        Consultation consultation = appointment.getConsultation();

        // Assuming you have DoctorDTO, PatientDTO, and ConsultationDTO classes available
        DoctorDTO doctorDTO = this.convertToDoctorDTO(doctor);
        // Populate doctorDTO with data from appointment.getDoctor() if needed

        PatientDTO patientDTO = this.convertToPatientDTO(patient);
        // Populate patientDTO with data from appointment.getPatient() if needed

        ConsultationDTO consultationDTO = this.convertToConsultationDTO(consultation);
        // Populate consultationDTO with data from appointment.getConsultation() if needed

        appointmentDTO.setDoctor(doctorDTO);
        appointmentDTO.setPatient(patientDTO);
        appointmentDTO.setConsultation(consultationDTO);

        return appointmentDTO;
    }


    public PatientDTO convertToPatientDTO(Patient patient) {
        PatientDTO patientDTO = new PatientDTO();
        patientDTO.setPatientId(patient.getPatientId());
        patientDTO.setPatientFirstName(patient.getPatientFirstName());
        patientDTO.setPatientLastName(patient.getPatientLastName());
        patientDTO.setTitle(patient.getTitle());
        patientDTO.setPatientContact(patient.getPatientContact());
        patientDTO.setPatientEmail(patient.getPatientEmail());
        patientDTO.setCurrentCity(patient.getCurrentCity());
        return patientDTO;
    }



    public ConsultationDTO convertToConsultationDTO(Consultation consultation) {
        if (consultation == null) {
            return null;
        }

        ConsultationDTO consultationDTO = new ConsultationDTO();
        consultationDTO.setId(consultation.getId());
        consultationDTO.setDateConsultation(consultation.getDateConsultation());
        consultationDTO.setRapport(consultation.getRapport());

        return consultationDTO;
    }


    public DoctorDTO convertToDoctorDTO(Doctor doctor) {
        DoctorDTO doctorDTO = new DoctorDTO();
        doctorDTO.setDoctorId(doctor.getDoctorId());
        doctorDTO.setDoctorFirstName(doctor.getDoctorFirstName());
        doctorDTO.setDoctorLastName(doctor.getDoctorLastName());
        doctorDTO.setTitle(doctor.getTitle());
        doctorDTO.setDoctorContact(doctor.getDoctorContact());
        doctorDTO.setDoctorEmail(doctor.getDoctorEmail());
        doctorDTO.setCurrentCity(doctor.getCurrentCity());
        doctorDTO.setOfficeAddress(doctor.getOfficeAddress());
        doctorDTO.setSpecialization(doctor.getSpecialization());
        doctorDTO.setStartTimeOfWork(doctor.getStartTimeOfWork());
        doctorDTO.setEndTimeOfWork(doctor.getEndTimeOfWork());
        return doctorDTO;
    }

}
