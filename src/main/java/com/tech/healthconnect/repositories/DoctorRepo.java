package com.tech.healthconnect.repositories;

import com.tech.healthconnect.models.Appointment;
import com.tech.healthconnect.models.Doctor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface DoctorRepo extends JpaRepository<Doctor, Long> {

    //for searching
    @Query("SELECT d FROM Doctor d " +
            "WHERE (COALESCE(:doctorFirstName, '') = '' OR LOWER(d.doctorFirstName) LIKE LOWER(CONCAT('%', :doctorFirstName, '%'))) " +
            "AND (COALESCE(:doctorLastName, '') = '' OR LOWER(d.doctorLastName) LIKE LOWER(CONCAT('%', :doctorLastName, '%'))) " +
            "AND (COALESCE(:currentCity, '') = '' OR LOWER(d.currentCity) LIKE LOWER(CONCAT('%', :currentCity, '%'))) " +
            "AND (COALESCE(:officeAddress, '') = '' OR LOWER(d.officeAddress) LIKE LOWER(CONCAT('%', :officeAddress, '%'))) " +
            "AND (COALESCE(:specialization, '') = '' OR LOWER(d.specialization) LIKE LOWER(CONCAT('%', :specialization, '%'))) ")
    List<Doctor> findDoctorsByCriteria(@Param("doctorFirstName") String doctorFirstName,
                                       @Param("doctorLastName") String doctorLastName,
                                       @Param("currentCity") String currentCity,
                                       @Param("officeAddress") String officeAddress,
                                       @Param("specialization") String specialization );



}
