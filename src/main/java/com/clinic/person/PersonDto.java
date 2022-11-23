package com.clinic.person;

import com.clinic.person.enums.Sex;
import com.clinic.person.enums.Vocation;
import lombok.AllArgsConstructor;
import lombok.Getter;

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
