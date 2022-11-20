package com.clinic.privateclinic.bank;

import com.clinic.privateclinic.person.Person;

import java.util.List;

public class Bank {
    private final String name;
    private final String nip;
    private final String krs;
    private final String gus;
    private final String medicalEntitiesNumber;
    private String street;
    private String city;
    private String postalCode;
    List<Person> persons;

    Bank(final String name, final String nip, final String krs, final String gus, final String medicalEntitiesNumber, final String street, final String city, final String postalCode, final List<Person> persons) {
        this.name = name;
        this.nip = nip;
        this.krs = krs;
        this.gus = gus;
        this.medicalEntitiesNumber = medicalEntitiesNumber;
        this.street = street;
        this.city = city;
        this.postalCode = postalCode;
        this.persons = persons;
    }
}
