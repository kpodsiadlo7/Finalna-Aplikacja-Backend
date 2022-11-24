package com.clinic.patient;

import com.clinic.person.Person;
import com.clinic.person.enums.Sex;
import com.clinic.person.enums.Vocation;
import com.clinic.reservation.Reservation;

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
    @OneToMany(cascade = CascadeType.ALL)
    private List<Reservation> reservations;

    public void setDiseasesStory(final DiseaseStory story) {
        this.diseaseStory.add(story);
    }



    public void setReservations(final Reservation reservations) {
        this.reservations.add(reservations);
    }
}
