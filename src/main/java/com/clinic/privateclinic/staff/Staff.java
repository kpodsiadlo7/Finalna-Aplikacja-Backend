package com.clinic.privateclinic.staff;

import com.clinic.privateclinic.person.Person;
import com.clinic.privateclinic.person.enums.Profession;
import org.springframework.stereotype.Component;

@Component
public class Staff extends Person{
    private Profession profession;

}
