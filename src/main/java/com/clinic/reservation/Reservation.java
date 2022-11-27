package com.clinic.reservation;

import com.clinic.reservation.enums.Currency;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Getter
@Setter
@NoArgsConstructor
@Table(name = "RESERVATIONS")
public class Reservation {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;
    private int daysToVisit;
    private Currency currencyPayment;
    private boolean afterVisit;
    private LocalDateTime reservationDate;

    public Reservation(final Currency currencyPayment, final LocalDateTime reservationDate) {
        this.currencyPayment = currencyPayment;
        this.reservationDate = LocalDateTime.now().plusDays(LocalDateTime.now().getDayOfMonth()-reservationDate.getDayOfMonth());
        this.afterVisit = false;
        this.daysToVisit = reservationDate.getDayOfMonth() - LocalDateTime.now().getDayOfMonth();
    }

    public void closeReservation(){
        this.afterVisit = true;
    }

    public boolean changeReservationDate(final LocalDateTime visitDate) {
        if (visitDate.getDayOfMonth() < LocalDateTime.now().getDayOfMonth())
            return false;
        this.reservationDate = visitDate;
        return true;
    }
    //SHOULDER POWINEN SPRAWDZAĆ co godzine KTÓRA REZERWACJA ZOSTAŁA PRZEDAWNIONA I ZMIENAĆ JEJ STATUS po 1 h od wizyty
    // shoulder powinen raz dziennie aktualizować dni do wizyty
    //IF DATE.NOW < DATE TO VISIT -> THROW EXCEPTION
    // nie można zarejestrować jeżeli liczba personelu nie wystarczy dla pacjentów
    // musi sprawdzać czy w danym dniu może kogoś obsłużyć
}
