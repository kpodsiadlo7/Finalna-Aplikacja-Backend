package com.clinic.grade;

import com.sun.istack.NotNull;
import lombok.Getter;

import javax.persistence.*;

@Entity
@Getter
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

    protected Grade() {
    }

    Grade(final long id, final String nickname, final String description, final double grade) {
        this.id = id;
        this.nickname = nickname;
        this.description = description;
        this.grade = grade;
    }
}
