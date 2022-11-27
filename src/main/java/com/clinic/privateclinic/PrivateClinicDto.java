package com.clinic.privateclinic;

import com.clinic.grade.Grade;
import com.clinic.grade.GradeDto;
import com.clinic.staff.Staff;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.List;

@Getter
@AllArgsConstructor
public class PrivateClinicDto {
    private long id;
    private String clinicName;
    private String city;
    private String street;
    private int staffQuantity;
    private int hospitalizedQuantity;
    private double grade;
    private List<Grade> gradeList;
    private List<Staff> staffList;
}
