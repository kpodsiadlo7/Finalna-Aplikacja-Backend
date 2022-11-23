package com.clinic.person;

import com.clinic.person.enums.BaseProfession;
import com.clinic.person.enums.Sex;
import com.clinic.person.enums.Vocation;
import com.clinic.patient.Patient;
import com.clinic.staff.StaffRepository;
import com.clinic.privateclinic.person.staff.doctor.Doctor;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
class PersonTest {
    @Autowired
    private StaffRepository staffRepository;
    @Autowired
    private PersonRepository personRepository;

    @Test
    @DisplayName("CreatePersonWithoutProfession")
    void shouldCreatePatientPerson_Male_Female_ProfessionNull(){
        // given
        Person person1 = new Patient("name","surname", Sex.MALE,23);
        Person person2 = new Patient("name2","surname",Sex.FEMALE, 33);
        Person person3 = new Patient("name","surname",Sex.MALE,23);
        Person person4 = new Patient("name2","surname",Sex.FEMALE,33);
        Person person5 = new Patient("name5","surname",Sex.FEMALE,33);
        // when
        personRepository.save(person1);
        personRepository.save(person2);
        personRepository.save(person3);
        personRepository.save(person4);
        personRepository.save(person5);
        Vocation vocation = Vocation.PATIENT;
        //then
        assertEquals(Sex.MALE, person1.getSex());
        assertEquals(vocation,person2.getVocation());
        assertEquals(5,personRepository.findByVocation(Vocation.PATIENT).size());
        assertEquals(3,personRepository.findBySex(Sex.FEMALE).size());
        assertEquals(0,staffRepository.findByBaseProfession(null).size());

        // CleanUp
        //personRepository.deleteAll();
    }

    @Test
    @DisplayName("CreateProfessionDoctorNurseVet")
    void shouldCreateStaffProfession_Doctor_Nurse_Vet(){
        // given
        Person doctor = new Doctor("doktorek","surname",Sex.MALE,33);
        Person nurse = new Doctor("nurse","surname",Sex.FEMALE,23);
        Person vet = new Doctor("vet","surname",Sex.MALE,43);
        // then & when
        staffRepository.save(doctor);
        staffRepository.save(nurse);
        staffRepository.save(vet);
        assertEquals(4, staffRepository.findByBaseProfession(BaseProfession.DOCTOR).size());
        assertEquals(4, staffRepository.findByBaseProfession(BaseProfession.NURSE).size());
        assertEquals(4, staffRepository.findByBaseProfession(BaseProfession.VET).size());

        // CleanUp
        staffRepository.deleteAll();
    }
}