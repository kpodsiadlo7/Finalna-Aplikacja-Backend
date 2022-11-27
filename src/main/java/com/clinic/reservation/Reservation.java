package com.clinic.reservation;

import com.clinic.reservation.enums.Currency;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.time.Duration;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;

@Entity
@Getter
@Setter
@NoArgsConstructor
@Table(name = "RESERVATIONS")
public class Reservation {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;
    private long daysToVisit;
    private Currency currencyPayment;
    private boolean afterVisit;
    private LocalDate reservationDate;

    public Reservation(final Currency currencyPayment, final LocalDate reservationDate) {
        this.currencyPayment = currencyPayment;
        this.reservationDate = reservationDate;
        this.afterVisit = false;
        this.daysToVisit = ChronoUnit.DAYS.between(LocalDate.now(),reservationDate);
    }

    public void closeReservation(){
        this.afterVisit = true;
    }

    public boolean changeReservationDate(final LocalDate visitDate) {
        if (visitDate.isBefore(LocalDate.now()))
            return false;
        this.reservationDate = visitDate;
        this.daysToVisit = ChronoUnit.DAYS.between(LocalDate.now(),reservationDate);
        return true;
    }
    //SHOULDER POWINEN SPRAWDZAĆ co godzine KTÓRA REZERWACJA ZOSTAŁA PRZEDAWNIONA I ZMIENAĆ JEJ STATUS po 1 h od wizyty
    // shoulder powinen raz dziennie aktualizować dni do wizyty
    //IF DATE.NOW < DATE TO VISIT -> THROW EXCEPTION
    // nie można zarejestrować jeżeli liczba personelu nie wystarczy dla pacjentów
    // musi sprawdzać czy w danym dniu może kogoś obsłużyć
}
