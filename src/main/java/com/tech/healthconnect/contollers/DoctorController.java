package com.tech.healthconnect.contollers;

import com.tech.healthconnect.dto.CreateDoctorDTO;
import com.tech.healthconnect.dto.DoctorDTO;
import com.tech.healthconnect.dto.DoctorSearchDTO;
import com.tech.healthconnect.dto.DoctorUpdateDTO;
import com.tech.healthconnect.models.Doctor;
import com.tech.healthconnect.models.Specialization;
import com.tech.healthconnect.services.DoctorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/doctors")
public class DoctorController {

    @Autowired
    private DoctorService doctorService;

    @PostMapping("/search")
    public ResponseEntity<List<DoctorDTO>> searchDoctors(@RequestBody DoctorSearchDTO searchDTO) {
        List<DoctorDTO> doctors = doctorService.searchDoctors(searchDTO);
        return ResponseEntity.ok(doctors);
    }

    @PostMapping("/create")
    public ResponseEntity<DoctorDTO> createDoctor(@RequestBody CreateDoctorDTO createDoctorDTO) {
        DoctorDTO doctorDTO = doctorService.createDoctor(createDoctorDTO);
        return ResponseEntity.ok(doctorDTO);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<String> deleteDoctor(@PathVariable Long id) {
        doctorService.deleteDoctor(id);
        return ResponseEntity.status(HttpStatus.OK).body("Doctor with ID " + id + " deleted successfully");
    }
    @PutMapping("/update/{doctorId}")
    public ResponseEntity<DoctorDTO> updateDoctor(@PathVariable Long doctorId, @RequestBody DoctorUpdateDTO doctorUpdateDTO) {
        DoctorDTO doctorDTO = doctorService.updateDoctor(doctorId, doctorUpdateDTO);
        return ResponseEntity.status(HttpStatus.OK).body(doctorDTO);
    }
}
