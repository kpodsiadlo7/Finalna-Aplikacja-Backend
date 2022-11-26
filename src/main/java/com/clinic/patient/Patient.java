package com.clinic.patient;

import com.clinic.patient.disease.DiseaseStory;
import com.clinic.person.Person;
import com.clinic.person.enums.Sex;
import com.clinic.person.enums.Vocation;
import com.clinic.reservation.Reservation;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Setter
public class Patient extends Person {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;
    private String reasonComingToClinic = "";

    protected Patient() {
    }

    public Patient(final String name, final String surname, final Sex sex, final int age, final String reasonComingToClinic) {
        super(name, surname,sex, Vocation.PATIENT, age);
        this.reasonComingToClinic = reasonComingToClinic;
        this.diseaseStory = new ArrayList<>();
        this.reservations = new ArrayList<>();
    }

    @OneToMany(cascade = CascadeType.ALL)
    @JoinTable(name = "PATIENT_DISEASES_STORY")
    private List<DiseaseStory> diseaseStory;
    @OneToMany(cascade = CascadeType.ALL)
    @JoinTable(name = "PATIENT_RESERVATIONS")
    private List<Reservation> reservations;

    public Patient(final String name, final String surname, final Sex sex, final int age, final long id, final String reasonComingToClinic) {
        super(name, surname,sex, Vocation.PATIENT, age);
        this.reasonComingToClinic = reasonComingToClinic;
        this.id = id;
    }

    public void setDiseasesStory(final DiseaseStory story) {
        this.diseaseStory.add(story);
    }

    public void setReservations(final Reservation reservations) {
        this.reservations.add(reservations);
    }
}
