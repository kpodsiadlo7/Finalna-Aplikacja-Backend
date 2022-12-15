package com.clinic.patient;

import com.clinic.person.enums.Sex;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class PatientTestSuite {

    @Autowired
    private PatientRepository patientRepository;

    @Test
    @DisplayName("CreateSeveralPatients")
    void shouldCreatePatients(){
        // given
        Patient patient1 = new Patient("patient","surname", Sex.MALE,23,"Noga boli");
        Patient patient2 = new Patient("patient2","surname",Sex.FEMALE, 33,"biodro boli");
        Patient patient3 = new Patient("patient3","surname",Sex.MALE,23,"piszczel boli");
        Patient patient4 = new Patient("patient4","surname",Sex.FEMALE,33,"plec boli");
        Patient patient5 = new Patient("patient5","surname",Sex.FEMALE,33,"kręgosłup boli");
        patientRepository.save(patient1);
        patientRepository.save(patient2);
        patientRepository.save(patient3);
        patientRepository.save(patient4);
        patientRepository.save(patient5);
        // when&then
        assertEquals(5,patientRepository.findAll().size());
        assertEquals(3,patientRepository.findAllBySex(Sex.FEMALE).size());
        assertEquals("patient5", patient5.getName());

        //CleanUp
        patientRepository.deleteAll();
    }
}