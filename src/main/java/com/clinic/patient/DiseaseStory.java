package com.clinic.patient;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.time.LocalDate;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "DISEASES_STORY")
public class DiseaseStory {
    @Id
    private long id;
    private String description;
    private LocalDate reservationDate;

    public DiseaseStory(final String description, final LocalDate date) {
        this.description = description;
        this.reservationDate = date;
    }
}
