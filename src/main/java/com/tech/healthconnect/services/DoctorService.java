package com.tech.healthconnect.services;

import com.tech.healthconnect.dto.*;
import com.tech.healthconnect.models.Appointment;
import com.tech.healthconnect.dto.AvailableSlotDTO;
import com.tech.healthconnect.models.Doctor;
import com.tech.healthconnect.repositories.AppointmentRepo;
import com.tech.healthconnect.repositories.DoctorRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.server.ResponseStatusException;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.*;
import java.util.concurrent.TimeUnit;
import java.util.stream.Collectors;

@Service
public class DoctorService {

    @Autowired
    private DoctorRepo doctorRepo;
    @Autowired
    private AppointmentRepo appointmentRepo;

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
        doctorDTO.setEndTimeOfWork(doctor.getEndTimeOfWork());
        doctorDTO.setStartTimeOfWork(doctor.getStartTimeOfWork());

        doctorDTO.setAppointments(convertAppointmentsToDTOs(doctor.getAppointments()));
        return doctorDTO;
    }

    private List<AvailableSlotDTO> convertAppointmentsToDTOs(List<Appointment> appointments) {
        if (appointments != null) { // Add null check here
            return appointments.stream()
                    .map(this::convertToAppointmentDTO)
                    .collect(Collectors.toList());
        } else {
            return Collections.emptyList(); // Return an empty list if appointments is null
        }
    }


    private AvailableSlotDTO convertToAppointmentDTO(Appointment appointment) {
        AvailableSlotDTO appointmentDTO = new AvailableSlotDTO();
        appointmentDTO.setStartTime(appointment.getStartTime());
        appointmentDTO.setEndTime(appointment.getEndTime());
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
    public List<AvailableSlotDTO> checkDoctorAvailability(Long doctorId, LocalDate date) {
        Doctor doctor = doctorRepo.findById(doctorId)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Doctor not found"));
        LocalTime startTimeOfWork = doctor.getStartTimeOfWork();
        LocalTime endTimeOfWork = doctor.getEndTimeOfWork();
        List<Appointment> appointments = appointmentRepo.findAppointmentByDateOnly(date);

        List<AvailableSlotDTO> availabilitySlots = new ArrayList<>();
        LocalTime currentTime = startTimeOfWork;

        while (currentTime.isBefore(endTimeOfWork)) {
            LocalTime nextHour = currentTime.plusHours(1);
            availabilitySlots.add(new AvailableSlotDTO(currentTime, nextHour));
            currentTime = nextHour;
        }

        // Supprimer les intervalles déjà occupés par des rendez-vous
        for (Appointment appointment : appointments) {
            Iterator<AvailableSlotDTO> iterator = availabilitySlots.iterator();
            while (iterator.hasNext()) {
                AvailableSlotDTO slot = iterator.next();

                LocalTime startTime = appointment.getStartTime();
                if (slot.getStartTime().compareTo(startTime) == 0) {
                    iterator.remove();
                }
            }
        }

        System.out.println(availabilitySlots);
        return availabilitySlots;
    }
}
