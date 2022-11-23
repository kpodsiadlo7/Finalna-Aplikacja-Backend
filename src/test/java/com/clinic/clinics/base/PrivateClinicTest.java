package com.clinic.clinics.base;

import com.clinic.privateclinic.PrivateClinic;
import com.clinic.privateclinic.PrivateClinicRepository;
import com.clinic.grade.Grade;
import com.clinic.grade.GradeRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
class PrivateClinicTest {

    @Autowired
    private PrivateClinicRepository privateClinicRepository;
    @Autowired
    private GradeRepository gradeRepository;

    @Test
    void shouldChangeAvgGradeInClinic(){
        //given
        PrivateClinic privateClinic = new PrivateClinic();
        privateClinicRepository.save(privateClinic);
        //when
        Grade grade1 = new Grade("Eloszka","Spoko było", 5.0);
        Grade grade2 = new Grade("Eloszka","Spoko było", 5.0);
        gradeRepository.save(grade1);
        gradeRepository.save(grade2);
        privateClinic.setGrades(grade1);
        privateClinic.setGrades(grade2);
        privateClinicRepository.save(privateClinic);
        //then
        assertEquals(5.0, privateClinic.getGrade());

        // CleanUp
        gradeRepository.deleteAll();
        privateClinicRepository.deleteAll();
    }

}