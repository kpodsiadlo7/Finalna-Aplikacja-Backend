package com.clinic.reservation;

import com.clinic.reservation.enums.Currency;
import lombok.Getter;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Getter
@Table(name = "RESERVATIONS")
public class Reservation {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;
    private int daysToVisit;
    private Currency currencyPayment;
    private boolean afterVisit;
    private LocalDateTime reservationDate;

    Reservation(final Currency currencyPayment, final LocalDateTime reservationDate) {
        this.currencyPayment = currencyPayment;
        this.reservationDate = reservationDate;
        this.afterVisit = false;
        this.daysToVisit = reservationDate.getDayOfMonth() - LocalDateTime.now().getDayOfMonth();
    }
    public Reservation(){
        this.reservationDate = LocalDateTime.now().plusDays(5);
        this.afterVisit = false;
        this.daysToVisit = reservationDate.getDayOfMonth() - LocalDateTime.now().getDayOfMonth();
    }


    //IF DATE.NOW() > DATE.NOW()+5DAYS -> THROW EXCEPTION
    //SHOULDER POWINEN SPRAWDZAĆ co godzine KTÓRA REZERWACJA ZOSTAŁA PRZEDAWNIONA I ZMIENAĆ JEJ STATUS po 1 h od wizyty
    // shoulder powinen raz dziennie aktualizować dni do wizyty
    //IF DATE.NOW < DATE TO VISIT -> THROW EXCEPTION
    // nie można zarejestrować jeżeli liczba personelu nie wystarczy dla pacjentów
    // musi sprawdzać czy w danym dniu może kogoś obsłużyć
}
