package com.clinic.privateclinic.clinics.base;

import com.clinic.privateclinic.clinics.Grade;
import com.clinic.privateclinic.person.Person;
import com.sun.istack.NotNull;
import lombok.Data;
import org.springframework.stereotype.Component;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "CLINICS")
@Inheritance(strategy = InheritanceType.TABLE_PER_CLASS)
public class Clinic {
    @Id
    @NotNull
    private long id;
    private int staffQty = 0;
    private int hospitalizedQty = 0;
    private double grade = 0;

    @ManyToMany(cascade = CascadeType.ALL)
    @JoinTable(
            name = "ClinicGrades",
            inverseJoinColumns = @JoinColumn(name = "GRADE_ID"),
            joinColumns = @JoinColumn(name = "CLINIC_ID")
    )
    private List<Grade> grades = new ArrayList<>();

    @ManyToMany(cascade = CascadeType.ALL)
    @JoinTable(
            name = "AllStaff",
            inverseJoinColumns = @JoinColumn(name = "PERSON_ID"),
            joinColumns = @JoinColumn(name = "CLINIC_ID")
    )
    private List<Person> staff = new ArrayList<>();

    protected Clinic(){
    }

    public long getId() {
        return id;
    }

    public int getStaffQty() {
        return staffQty;
    }

    public void setStaffQty(final int staffQty) {
        this.staffQty = staffQty;
    }

    public int getHospitalizedQty() {
        return hospitalizedQty;
    }

    public void setHospitalizedQty(final int hospitalizedQty) {
        this.hospitalizedQty = hospitalizedQty;
    }

    public double getGrade() {
        return grade;
    }

    public void setGrade(final double grade) {
        this.grade = grade;
    }

    public List<Grade> getGrades() {
        return grades;
    }

    public void setGrades(final List<Grade> grades) {
        this.grades = grades;
    }

    public List<Person> getStaff() {
        return staff;
    }

    public void setStaff(final List<Person> staff) {
        this.staff = staff;
    }
}
