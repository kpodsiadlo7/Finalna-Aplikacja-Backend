package com.clinic.patient;

import com.clinic.person.enums.Sex;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
@Repository
public interface PatientRepository extends JpaRepository<Patient, Long> {
    Optional<Patient> findBySex(Sex sex);
    List<Patient> findAllBySex(Sex sex);
    Patient findById(long patientId);
}
