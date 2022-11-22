package com.clinic.privateclinic.person;

import com.clinic.privateclinic.clinics.base.Clinic;
import com.clinic.privateclinic.clinics.grade.Grade;
import com.clinic.privateclinic.person.enums.Sex;
import com.clinic.privateclinic.person.enums.Vocation;
import com.sun.istack.NotNull;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "PERSONS")
@Inheritance(strategy = InheritanceType.TABLE_PER_CLASS)
public class Person {
    @Id
    @NotNull
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;
    private String name;
    private String surname;
    private Sex sex;
    private Vocation vocation;
    private int age;

    protected Person() {
    }

    protected Person(final String name, final String surname, final Sex sex, final Vocation vocation, final int age) {
        this.name = name;
        this.surname = surname;
        this.sex = sex;
        this.vocation = vocation;
        this.age = age;
    }

    long getId() {
        return id;
    }

    String getName() {
        return name;
    }

    void setName(final String name) {
        this.name = name;
    }

    String getSurname() {
        return surname;
    }

    void setSurname(final String surname) {
        this.surname = surname;
    }

    Sex getSex() {
        return sex;
    }

    void setSex(final Sex sex) {
        this.sex = sex;
    }

    Vocation getVocation() {
        return vocation;
    }

    void setVocation(final Vocation vocation) {
        this.vocation = vocation;
    }

    int getAge() {
        return age;
    }

    void setAge(final int age) {
        this.age = age;
    }

}
