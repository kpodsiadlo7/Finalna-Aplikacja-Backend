package com.clinic.patient.disease;

import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Getter
@NoArgsConstructor
@Table(name = "DISEASES_STORY")
public class DiseaseStory {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;
    private String description;

    public DiseaseStory(final String description) {
        this.description = description;
    }

    @Override
    public String toString() {
        return description;
    }
}
