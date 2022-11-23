package com.clinic.grade;

import com.clinic.person.Person;
import com.clinic.staff.Staff;
import com.sun.istack.NotNull;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "GRADES")
public class Grade {
    @Id
    @NotNull
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;
    private String nickname;
    private String description;
    private double grade;

    public Grade(final String nickname, final String description, final double grade) {
        this.nickname = nickname;
        this.description = description;
        this.grade = grade;
    }

    public Grade() {
    }

    public String getNickname() {
        return nickname;
    }

    public void setNickname(final String nickname) {
        this.nickname = nickname;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(final String description) {
        this.description = description;
    }

    public double getGrade() {
        return grade;
    }

    void setGrade(final double grade) {
        this.grade = grade;
    }

}
