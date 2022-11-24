package com.clinic.staff;

import com.clinic.person.enums.Sex;
import com.clinic.grade.Grade;
import com.clinic.patient.Patient;
import com.clinic.person.Person;
import com.clinic.person.enums.BaseProfession;
import com.clinic.person.enums.Vocation;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Entity
@Setter
@Getter
public class Staff extends Person {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;
    private BaseProfession baseProfession;
    private int quantityPatientToHelp;
    private double grade;
    private int patientQuantity;
    protected Staff(){
    }
    public Staff(final String name, final String surname, final Sex sex, final int age, final BaseProfession baseProfession) {
        super(name, surname, sex, Vocation.STAFF, age);
        this.baseProfession = baseProfession;
        this.patientList = new ArrayList<>();
        this.patientQuantity = 0;
        this.grade = 0;
        this.quantityPatientToHelp = 4;
    }

    @ManyToMany(cascade = CascadeType.ALL)
    @JoinTable(
            name = "StaffGrades",
            inverseJoinColumns = @JoinColumn(name = "PERSON_ID"),
            joinColumns = @JoinColumn(name = "GRADE_ID")
    )
    private List<Grade> gradesList;

    @ManyToMany
    @JoinTable(
            name = "StaffAndPatients",
            inverseJoinColumns = @JoinColumn(name = "STAFF_ID"),
            joinColumns = @JoinColumn(name = "PATIENT_ID")
    )
    private List<Patient> patientList;

    public void setAverageGrade(final Grade grade) {
        this.gradesList.add(grade);
        setGrade(avgGrade());
    }
    private double avgGrade(){
        List<Double> doubleList = getGradesList().stream().map(Grade::getGrade).collect(Collectors.toList());
        return doubleList.stream().mapToDouble(d -> d).average().orElse(0.0);
    }

    public void addPatient(final Patient patient) {
        if (quantityPatientToHelp > 0) {
            this.patientList.add(patient);
            patientQuantity++;
            quantityPatientToHelp--;
        } else
            throw new IllegalStateException("This " + this.getBaseProfession() +" can't help more people!\nTry to register for different time.");
    }
}
