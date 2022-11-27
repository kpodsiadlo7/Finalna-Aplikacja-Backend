package com.clinic.patient;

import com.clinic.patient.disease.DiseaseStory;
import com.clinic.person.enums.Sex;
import com.clinic.person.enums.Vocation;
import com.clinic.reservation.Reservation;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
public class PatientDto {
    private long id;
    private String name;
    private String surname;
    private Sex sex;
    private Vocation vocation;
    private int age;
    private List<DiseaseStory> diseaseStory;
    private List<Reservation> reservation;
    private String reasonComingToClinic;
}
