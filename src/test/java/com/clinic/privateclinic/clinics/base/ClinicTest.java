package com.clinic.privateclinic.clinics.base;

import com.clinic.privateclinic.clinics.grade.Grade;
import com.clinic.privateclinic.clinics.grade.GradeRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
class ClinicTest {

    @Autowired
    private ClinicRepository clinicRepository;
    @Autowired
    private GradeRepository gradeRepository;

    @Test
    void shouldChangeAvgGradeInClinic(){
        //given
        Clinic clinic = new Clinic();
        clinicRepository.save(clinic);
        //when
        Grade grade1 = new Grade("Eloszka","Spoko było", 5.0);
        Grade grade2 = new Grade("Eloszka","Spoko było", 5.0);
        gradeRepository.save(grade1);
        gradeRepository.save(grade2);
        clinic.setGrades(grade1);
        clinic.setGrades(grade2);
        clinicRepository.save(clinic);
        //then
        assertEquals(5.0,clinic.getGrade());

        // CleanUp
        gradeRepository.deleteAll();
        clinicRepository.deleteAll();
    }

}