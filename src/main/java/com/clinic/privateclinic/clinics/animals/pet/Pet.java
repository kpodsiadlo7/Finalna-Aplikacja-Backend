package com.clinic.privateclinic.clinics.animals.pet;

import com.clinic.privateclinic.clinics.animals.enums.Animal;
import com.clinic.privateclinic.clinics.animals.enums.Homeless;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "PETS")
public class Pet {
    @Id
    private long id;
    private Animal animal;
    private Homeless homeless;
}
