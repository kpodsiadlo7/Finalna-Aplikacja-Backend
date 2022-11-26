package com.clinic.person;

import com.clinic.person.enums.Sex;
import com.clinic.person.enums.Vocation;
import com.sun.istack.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Data
@Entity
@Table(name = "PERSONS")
public class Person {
    @Id
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

}
