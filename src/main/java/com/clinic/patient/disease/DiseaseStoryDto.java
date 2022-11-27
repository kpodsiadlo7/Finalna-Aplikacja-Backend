package com.clinic.patient.disease;

import com.clinic.patient.Patient;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class DiseaseStoryDto {
    private long id;
    private String description;
}
