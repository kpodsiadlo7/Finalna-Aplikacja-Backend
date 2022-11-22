package com.clinic.privateclinic.clinics.animals;

import com.clinic.privateclinic.clinics.animals.pet.Pet;
import com.clinic.privateclinic.clinics.base.Clinic;
import com.clinic.privateclinic.person.Person;
import com.clinic.privateclinic.person.staff.Staff;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "ANIMALS_CLINIC")
public class AnimalClinic extends Clinic {

    protected AnimalClinic(){
    }



    @OneToMany()
    private List<Staff> staff;

    @OneToMany()
    private List<Pet> pet;
}
