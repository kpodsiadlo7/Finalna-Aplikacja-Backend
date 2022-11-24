package com.clinic.reservation;

import com.clinic.reservation.enums.Currency;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
@AllArgsConstructor
public class ReservationDto {
    private long id;
    private int daysToVisit;
    private Currency currencyPayment;
    private boolean afterVisit;
    private LocalDateTime reservationDate;
}
