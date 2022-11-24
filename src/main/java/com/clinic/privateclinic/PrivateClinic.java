package com.clinic.privateclinic;

import com.clinic.grade.Grade;
import com.clinic.staff.Staff;
import com.sun.istack.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Entity
@AllArgsConstructor
@Getter
@Table(name = "CLINICS")
@Inheritance(strategy = InheritanceType.TABLE_PER_CLASS)
public class PrivateClinic {
    @Id
    @NotNull
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;
    private String clinicName;
    private int staffQuantity;
    private int hospitalizedQuantity;
    private double grade;

    protected PrivateClinic(){
    }

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
            inverseJoinColumns = @JoinColumn(name = "STAFF_ID")
    )
    private List<Staff> staff;

    public PrivateClinic(String clinicName){
        this.clinicName = clinicName;
        this.gradesList = new ArrayList<>();
        this.staff = new ArrayList<>();
        this.staffQuantity = 0;
        this.hospitalizedQuantity = 0;
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

    public void addStaff(final Staff staffPerson) {
        this.staff.add(staffPerson);
        staffQuantity++;
        this.hospitalizedQuantity += staffPerson.getPatientList().size();
    }
}
