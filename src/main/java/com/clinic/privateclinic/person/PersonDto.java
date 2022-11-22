package com.clinic.privateclinic.person;

import com.clinic.privateclinic.clinics.grade.Grade;
import com.clinic.privateclinic.clinics.base.Clinic;
import com.clinic.privateclinic.person.enums.Sex;
import com.clinic.privateclinic.person.enums.Vocation;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.List;

@Getter
@AllArgsConstructor
public class PersonDto {
    private long id;
    private String name;
    private String surname;
    private Sex sex;
    private Vocation vocation;
    private int age;

    void setVocation(final Vocation vocation) {
        this.vocation = vocation;
    }

    void setSex(final Sex sex) {
        this.sex = sex;
    }
}
