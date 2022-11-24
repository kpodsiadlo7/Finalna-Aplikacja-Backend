package com.clinic.privateclinic;

import com.clinic.grade.GradeDto;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.List;

@Getter
@AllArgsConstructor
public class PrivateClinicDto {
    private long id;
    private String clinicName;
    private int staffQuantity;
    private int hospitalizedQuantity;
    private double grade;
    private List<Long> gradeIdList;
    private List<Long> staffIdList;
}
