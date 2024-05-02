package com.tech.healthconnect.services;


import com.tech.healthconnect.models.Appointment;
import com.tech.healthconnect.models.Doctor;
import com.tech.healthconnect.repositories.AppointmentRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AppointmentService {

    @Autowired
    private AppointmentRepo appointmentRepo;




}
