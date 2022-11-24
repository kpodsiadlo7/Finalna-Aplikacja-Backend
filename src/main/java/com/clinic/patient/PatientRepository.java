package com.clinic.patient;

import com.clinic.person.enums.Sex;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface PatientRepository extends JpaRepository<Patient, Long> {
    Optional<Patient> findBySex(Sex sex);
    List<Patient> findAllBySex(Sex sex);
}
