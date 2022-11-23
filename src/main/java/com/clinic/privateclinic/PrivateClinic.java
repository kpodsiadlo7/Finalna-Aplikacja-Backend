package com.clinic.privateclinic;

import com.clinic.grade.Grade;
import com.clinic.staff.Staff;
import com.sun.istack.NotNull;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Entity
@Table(name = "CLINICS")
@Inheritance(strategy = InheritanceType.TABLE_PER_CLASS)
public class PrivateClinic {
    @Id
    @NotNull
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;
    private String clinicName;
    private int staffQty = 0;
    private int hospitalizedQty = 0;
    private double grade;

    @ManyToMany
    @JoinTable(
            name = "ClinicGrades",
            joinColumns = @JoinColumn(name = "CLINIC_ID"),
            inverseJoinColumns = @JoinColumn(name = "GRADE_ID")
    )
    private List<Grade> gradesList;

    @ManyToMany(cascade = CascadeType.ALL)
    @JoinTable(
            name = "AllStaff",
            joinColumns = @JoinColumn(name = "CLINIC_ID"),
            inverseJoinColumns = @JoinColumn(name = "PERSON_ID")
    )
    private List<Staff> staff;

    public PrivateClinic(){
        gradesList = new ArrayList<>();
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

    private void setGrade(final double grade) {
        this.grade = grade;
    }

    public List<Grade> getGrades() {
        return gradesList;
    }

    public void setGrades(final Grade grade) {
        this.gradesList.add(grade);
        setGrade(avgGrade());
    }


    double avgGrade(){
        List<Double> doubleList = getGrades().stream().map(Grade::getGrade).collect(Collectors.toList());
        return doubleList.stream().mapToDouble(d -> d).average().orElse(0.0);
    }

    List<Staff> getStaff() {
        return staff;
    }

    public void setStaff(final Staff staffs) {
        this.staff.add(staffs);
        staffQty++;
    }
}
