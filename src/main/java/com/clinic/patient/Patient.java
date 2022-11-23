package com.clinic.patient;

import com.clinic.person.Person;
import com.clinic.person.enums.Sex;
import com.clinic.person.enums.Vocation;

import javax.persistence.*;
import java.util.List;

@Entity
public class Patient extends Person {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    protected Patient() {
    }

    public Patient(final String name, final String surname, final Sex sex, final int age) {
        super(name, surname, sex, Vocation.PATIENT, age);
    }

    @OneToMany(cascade = CascadeType.ALL)
    List<DiseaseStory> diseaseStory;

    public void setDiseasesStory(final List<DiseaseStory> diseaseStory) {
        this.diseaseStory = diseaseStory;
    }
}
