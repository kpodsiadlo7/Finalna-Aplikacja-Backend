package com.clinic.privateclinic.patient;


import javax.persistence.Entity;
import javax.persistence.Id;
import java.time.LocalDate;

@Entity
public class DiseasesStory {
    @Id
    private long id;
    private String description;
    private LocalDate createdHistory;
}
