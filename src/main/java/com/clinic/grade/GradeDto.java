package com.clinic.grade;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class GradeDto {
    private long id;
    private String nickname;
    private String description;
    private double grade;
}
