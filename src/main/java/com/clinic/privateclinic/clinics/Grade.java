package com.clinic.privateclinic.clinics;

import com.clinic.privateclinic.clinics.base.Clinic;
import com.clinic.privateclinic.person.Person;
import lombok.Data;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Data
@Table(name = "GRADES")
public class Grade {
    @Id
    private long id;
    private String nickname;
    private String description;
    private double grade;

    @ManyToMany
    @JoinTable(
            name = "ClinicGrades",
            joinColumns = @JoinColumn(name = "GRADE_ID"),
            inverseJoinColumns = @JoinColumn(name = "CLINIC_ID")
    )
    private List<Clinic> clinics = new ArrayList<>();

    @ManyToMany(cascade = CascadeType.ALL)
    @JoinTable(
            name = "StaffGrades",
            joinColumns = @JoinColumn(name = "PERSON_ID"),
            inverseJoinColumns = @JoinColumn(name = "GRADE_ID")
    )
    private List<Person> people = new ArrayList<>();
}
