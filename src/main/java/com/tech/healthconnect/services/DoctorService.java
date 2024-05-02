package com.tech.healthconnect.services;

import com.tech.healthconnect.dto.*;
import com.tech.healthconnect.models.Appointment;
import com.tech.healthconnect.models.Doctor;
import com.tech.healthconnect.repositories.DoctorRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class DoctorService {

    @Autowired
    private DoctorRepo doctorRepo;


    //for searching
    @Transactional
    public List<DoctorDTO> searchDoctors(DoctorSearchDTO searchDTO) {
        List<Doctor> doctors = doctorRepo.findDoctorsByCriteria(
                searchDTO.getDoctorFirstName(),
                searchDTO.getDoctorLastName(),
                searchDTO.getCurrentCity(),
                searchDTO.getOfficeAddress(),
                searchDTO.getSpecialization().toString()
        );

        return doctors.stream()
                .map(this::convertToDoctorDTO)
                .collect(Collectors.toList());
    }

    private DoctorDTO convertToDoctorDTO(Doctor doctor) {
        DoctorDTO doctorDTO = new DoctorDTO();
        doctorDTO.setDoctorFirstName(doctor.getDoctorFirstName());
        doctorDTO.setDoctorLastName(doctor.getDoctorLastName());
        doctorDTO.setTitle(doctor.getTitle());
        doctorDTO.setDoctorContact(doctor.getDoctorContact());
        doctorDTO.setDoctorEmail(doctor.getDoctorEmail());
        doctorDTO.setCurrentCity(doctor.getCurrentCity());
        doctorDTO.setOfficeAddress(doctor.getOfficeAddress());
        doctorDTO.setSpecialization(doctor.getSpecialization());
        doctorDTO.setEndTimeOfWork(doctor.getEndTimeOfWork());
        doctorDTO.setStartTimeOfWork(doctor.getStartTimeOfWork());

        doctorDTO.setAppointments(convertAppointmentsToDTOs(doctor.getAppointments()));
        return doctorDTO;
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

    //creating doctor
    public DoctorDTO createDoctor(CreateDoctorDTO createDoctorDTO) {
        Doctor doctor = convertToDoctorEntity(createDoctorDTO);
        Doctor savedDoctor = doctorRepo.save(doctor);
        return convertToDoctorDTO(savedDoctor);
    }

    private Doctor convertToDoctorEntity(CreateDoctorDTO createDoctorDTO) {
        Doctor doctor = new Doctor();
        doctor.setDoctorFirstName(createDoctorDTO.getDoctorFirstName());
        doctor.setDoctorLastName(createDoctorDTO.getDoctorLastName());
        doctor.setTitle(createDoctorDTO.getTitle());
        doctor.setDoctorContact(createDoctorDTO.getDoctorContact());
        doctor.setDoctorEmail(createDoctorDTO.getDoctorEmail());
        doctor.setCurrentCity(createDoctorDTO.getCurrentCity());
        doctor.setOfficeAddress(createDoctorDTO.getOfficeAddress());
        doctor.setSpecialization(createDoctorDTO.getSpecialization());
        doctor.setCityOfBirth(createDoctorDTO.getCityOfBirth());
        doctor.setAge(createDoctorDTO.getAge());
        doctor.setAddress(createDoctorDTO.getAddress());
        doctor.setDoctorPassword(createDoctorDTO.getDoctorPassword());
        doctor.setEndTimeOfWork(createDoctorDTO.getEndTimeOfWork());
        doctor.setStartTimeOfWork(createDoctorDTO.getStartTimeOfWork());
        return doctor;
    }

    // delete doctor

    @Transactional
    public void deleteDoctor(Long id) {
        doctorRepo.deleteById(id);
    }

    // update doctor info

    public DoctorDTO updateDoctor(Long doctorId, DoctorUpdateDTO doctorUpdateDTO) {
        Doctor doctor = doctorRepo.findById(doctorId).orElseThrow(() -> new RuntimeException("Doctor not found"));
        // Update doctor fields based on DTO
        doctor.setDoctorId(doctorUpdateDTO.getDoctorId());
        doctor.setDoctorFirstName(doctorUpdateDTO.getDoctorFirstName());
        doctor.setDoctorLastName(doctorUpdateDTO.getDoctorLastName());
        doctor.setAge(doctorUpdateDTO.getAge());
        doctor.setTitle(doctorUpdateDTO.getTitle());
        doctor.setCityOfBirth(doctorUpdateDTO.getCityOfBirth());
        doctor.setAddress(doctorUpdateDTO.getAddress());
        doctor.setCurrentCity(doctorUpdateDTO.getCurrentCity());
        doctor.setDoctorEmail(doctorUpdateDTO.getDoctorEmail());
        doctor.setDoctorPassword(doctorUpdateDTO.getDoctorPassword());
        doctor.setDoctorContact(doctorUpdateDTO.getDoctorContact());
        doctor.setOfficeAddress(doctorUpdateDTO.getOfficeAddress());
        doctor.setSpecialization(doctorUpdateDTO.getSpecialization());
        doctor.setStartTimeOfWork(doctorUpdateDTO.getStartTimeOfWork());
        doctor.setEndTimeOfWork(doctor.getEndTimeOfWork());
        Doctor savedDoctor = doctorRepo.save(doctor);
        return convertToDoctorDTO(savedDoctor);
    }
}
