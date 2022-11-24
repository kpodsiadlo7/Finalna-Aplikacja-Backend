package com.clinic.reservation;

import com.clinic.patient.Patient;

import javax.persistence.*;
import java.time.LocalDate;

@Entity
@Table(name = "RESERVATIONS")
public class Reservation {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    private LocalDate reservationCreateTime;
}
