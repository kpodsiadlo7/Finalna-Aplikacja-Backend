package com.clinic.privateclinic.person.staff.vet;

import com.clinic.privateclinic.person.enums.BaseProfession;
import com.clinic.privateclinic.person.enums.Sex;
import com.clinic.privateclinic.person.enums.Vocation;
import com.clinic.privateclinic.person.staff.Staff;

import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "VETS")
public class Vet extends Staff {
    public Vet(){
    }

    Vet(final String name, final String surname, final Sex sex, final int age) {
        super(name, surname, sex, Vocation.STAFF, age, BaseProfession.VET);
    }


}

