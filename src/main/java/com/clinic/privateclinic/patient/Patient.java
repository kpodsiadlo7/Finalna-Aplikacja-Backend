package com.clinic.privateclinic.patient;

import com.clinic.privateclinic.person.Person;
import com.clinic.privateclinic.person.enums.Sex;
import com.clinic.privateclinic.person.enums.Vocation;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "PATIENTS")
public class Patient extends Person {

    public Patient() {
    }

    public Patient(final String name, final String surname, final Sex sex, final int age) {
        super(name, surname, sex, Vocation.PATIENT, age);
    }

    @OneToMany(cascade = CascadeType.ALL)
    List<DiseasesStory> diseasesStory = new ArrayList<>();

    void setDiseasesStory(final List<DiseasesStory> diseasesStory) {
        this.diseasesStory = diseasesStory;
    }
}
