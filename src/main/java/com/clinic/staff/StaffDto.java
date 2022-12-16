package com.clinic.staff;

import com.clinic.grade.Grade;
import com.clinic.patient.Patient;
import com.clinic.person.enums.BaseProfession;
import com.clinic.person.enums.Sex;
import com.clinic.person.enums.Vocation;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
public class StaffDto {
    private long id;
    private String name;
    private String surname;
    private Sex sex;
    private Vocation vocation;
    private int age;
    protected BaseProfession baseProfession;
    private int quantityPatientToHelp;
    private double grade;
    private int patientQuantity;
    private List<Grade> gradeList;
    private List<Patient> patientList;
}
