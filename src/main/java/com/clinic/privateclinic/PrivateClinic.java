package com.clinic.privateclinic;

import com.clinic.grade.Grade;
import com.clinic.patient.Patient;
import com.clinic.staff.Staff;
import com.clinic.staff.StaffDto;
import com.sun.istack.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Entity
@Getter
@Setter
@AllArgsConstructor
@Table(name = "CLINICS")
@Inheritance(strategy = InheritanceType.TABLE_PER_CLASS)
public class PrivateClinic {
    @Id
    @NotNull
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;
    private String clinicName;
    private String city;
    private String street;
    private int staffQuantity;
    private int hospitalizedQuantity;
    private double grade;

    protected PrivateClinic(){
    }

    @ManyToMany(cascade = CascadeType.ALL)
    @JoinTable(
            name = "ClinicGrades",
            joinColumns = @JoinColumn(name = "CLINIC_ID"),
            inverseJoinColumns = @JoinColumn(name = "GRADE_ID")
    )
    private List<Grade> gradesList;

    @ManyToMany
    @JoinTable(
            name = "AllStaff",
            joinColumns = @JoinColumn(name = "CLINIC_ID"),
            inverseJoinColumns = @JoinColumn(name = "STAFF_ID")
    )
    private List<Staff> staffList;

    @ManyToMany
    @JoinTable(
            name = "AllPatients",
            joinColumns = @JoinColumn(name = "CLINIC_ID"),
            inverseJoinColumns = @JoinColumn(name = "PATIENT_ID")
    )
    private List<Patient> patientList;

    public PrivateClinic(final String clinicName, final String city, final String street){
        this.clinicName = clinicName;
        this.city = city;
        this.street = street;
        this.gradesList = new ArrayList<>();
        this.staffList = new ArrayList<>();
        this.patientList = new ArrayList<>();
        this.staffQuantity = 0;
        this.hospitalizedQuantity = 0;
    }

    public void setGrades(final Grade grade) {
        this.gradesList.add(grade);
        this.grade = avgGrade();
    }

    double avgGrade(){
        List<Double> doubleList = getGradesList().stream().map(Grade::getGrade).collect(Collectors.toList());
        return doubleList.stream().mapToDouble(d -> d).average().orElse(0.0);
    }

    public void addStaff(final Staff staffPerson) {
        this.staffList.add(staffPerson);
        staffQuantity++;
    }
    public void registerPatient(final Patient patient){
        this.patientList.add(patient);
        hospitalizedQuantity++;
    }
    public boolean removeStaff(Staff staffToRemove){
        for (Staff s: staffList){
            if (s == staffToRemove)
                staffList.remove(staffToRemove);
            else return false;
        }
        staffQuantity--;
        return true;
    }
}
