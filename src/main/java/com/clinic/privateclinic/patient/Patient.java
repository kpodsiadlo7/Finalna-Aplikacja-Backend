package com.clinic.privateclinic.patient;

import com.clinic.privateclinic.person.Person;
import com.clinic.privateclinic.person.enums.Sex;
import com.clinic.privateclinic.person.enums.Vocation;
import com.clinic.privateclinic.person.staff.doctor.Doctor;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "PATIENTS")
public class Patient extends Person {

    protected Patient() {
    }

    public Patient(final String name, final String surname, final Sex sex, final int age) {
        super(name, surname, sex, Vocation.PATIENT, age);
    }

    @OneToMany(cascade = CascadeType.ALL)
    List<DiseasesStory> diseasesStory;

    @ManyToMany
    @JoinTable(
            name = "DoctorsAndPatients",
            joinColumns = @JoinColumn(name = "DOCTOR_ID"),
            inverseJoinColumns = @JoinColumn(name = "PATIENT_ID")
    )
    private List<Doctor> doctors;

    public void setDiseasesStory(final List<DiseasesStory> diseasesStory) {
        this.diseasesStory = diseasesStory;
    }
}
