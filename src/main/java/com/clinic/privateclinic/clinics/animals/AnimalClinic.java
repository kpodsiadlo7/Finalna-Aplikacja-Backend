package com.clinic.privateclinic.clinics.animals;

import com.clinic.privateclinic.clinics.animals.pet.Pet;
import com.clinic.privateclinic.clinics.base.Clinic;
import com.clinic.privateclinic.person.Person;
import com.clinic.privateclinic.staff.Staff;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "ANIMALS_CLINIC")
public final class AnimalClinic extends Clinic {
    private static final AnimalClinic SINGLETON = new AnimalClinic();
    private static final String name = "Animal's Clinic";

    public AnimalClinic(){
        if (SINGLETON != null)
            throw new IllegalStateException("Singleton already constructed");
    }
    @OneToMany
    List<Person> staff = new ArrayList<>();

    @OneToMany
    List<Pet> pet = new ArrayList<>();
}
