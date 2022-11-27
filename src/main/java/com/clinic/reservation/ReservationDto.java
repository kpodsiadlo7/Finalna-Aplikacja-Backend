package com.clinic.reservation;

import com.clinic.reservation.enums.Currency;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.time.LocalDate;

@Getter
@AllArgsConstructor
public class ReservationDto {
    private long id;
    private long daysToVisit;
    private Currency currencyPayment;
    private boolean afterVisit;
    private LocalDate reservationDate;
}
