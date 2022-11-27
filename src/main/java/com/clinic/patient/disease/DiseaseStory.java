package com.clinic.patient.disease;

import com.clinic.patient.Patient;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Getter
@Setter
@NoArgsConstructor
@Table(name = "DISEASES_STORY")
public class DiseaseStory {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;
    private String description;
    private LocalDateTime date;

    public DiseaseStory(final String description) {
        this.description = description;
        this.date = LocalDateTime.now();
    }

    public DiseaseStory(final long id, final String description) {
        this.id = id;
        this.description = description;
    }

    @Override
    public String toString() {
        return description;
    }
}
