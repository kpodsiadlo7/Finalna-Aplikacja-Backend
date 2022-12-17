package com.clinic.patient.disease;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
@AllArgsConstructor
public class DiseaseStoryDto {
    private long id;
    private long patientId;
    private String description;
    private LocalDateTime date;
}
