package com.clinic.grade;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class GradeDto {
    private long id;
    private String nickname;
    private String description;
    private double grade;

    GradeDto(final String nickname, final String description, final double grade) {
        this.nickname = nickname;
        this.description = description;
        this.grade = grade;
    }
}
