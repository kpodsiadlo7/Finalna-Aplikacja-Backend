package com.clinic.person;

import com.clinic.person.enums.BaseProfession;
import com.clinic.person.enums.Sex;
import com.clinic.person.enums.Vocation;
import com.clinic.patient.Patient;
import com.clinic.staff.Staff;
import com.clinic.staff.StaffRepository;
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
        Person person1 = new Person("name","surname",Sex.MALE,null,23);
        Person person2 = new Person("name2","surname",Sex.FEMALE,null, 33);
        Person person3 = new Person("name","surname",Sex.MALE,null, 23);
        Person person4 = new Person("name2","surname",Sex.FEMALE,null,33);
        Person person5 = new Person("name5","surname",Sex.FEMALE,null,33);
        // when
        personRepository.save(person1);
        personRepository.save(person2);
        personRepository.save(person3);
        personRepository.save(person4);
        personRepository.save(person5);
        Vocation vocation = null;
        // then
        assertEquals(Sex.MALE, person1.getSex());
        assertEquals(vocation,person2.getVocation());
        assertEquals(5,personRepository.findByVocation(Vocation.PATIENT).size());
        assertEquals(3,personRepository.findBySex(Sex.FEMALE).size());
        assertEquals(0,staffRepository.findByBaseProfession(null).size());

        // CleanUp
        personRepository.deleteAll();
    }
}