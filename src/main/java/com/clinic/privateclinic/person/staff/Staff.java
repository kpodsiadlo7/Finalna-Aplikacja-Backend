package com.clinic.privateclinic.person.staff;

import com.clinic.privateclinic.clinics.grade.Grade;
import com.clinic.privateclinic.person.Person;
import com.clinic.privateclinic.person.enums.BaseProfession;
import com.clinic.privateclinic.person.enums.Sex;
import com.clinic.privateclinic.person.enums.Vocation;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "STAFF")
public class Staff extends Person {
    protected BaseProfession baseProfession;
    private double grade;
    protected Staff(){
    }

    @ManyToMany(cascade = CascadeType.ALL)
    @JoinTable(
            name = "StaffGrades",
            inverseJoinColumns = @JoinColumn(name = "PERSON_ID"),
            joinColumns = @JoinColumn(name = "GRADE_ID")
    )
    private List<Grade> grades;

    public Staff(final String name, final String surname, final Sex sex, final Vocation vocation, final int age, final BaseProfession baseProfession) {
        super(name, surname, sex, vocation, age);
        this.baseProfession = baseProfession;
    }
}
