package com.clinic.grade;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@AllArgsConstructor
@NoArgsConstructor
public class GradeDto {
    private long id;
    private String nickname;
    private String description;
    private double grade;
}
