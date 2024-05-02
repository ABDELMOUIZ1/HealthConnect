package com.tech.healthconnect.services;


import com.tech.healthconnect.models.Consultation;
import com.tech.healthconnect.models.Doctor;
import com.tech.healthconnect.repositories.ConsultationRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ConsulationService {
    @Autowired
    private ConsultationRepo consultationRepo;




}
