package com.clinic.reservation;

import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ReservationMapper {
    public Reservation mapToReservation(final ReservationDto reservationDto){
        return new Reservation(
                reservationDto.getCurrencyPayment(),
                reservationDto.getReservationDate()
        );
    }
    public ReservationDto mapToReservationDto(final Reservation reservation){
        return new ReservationDto(
                reservation.getId(),
                reservation.getDaysToVisit(),
                reservation.getCurrencyPayment(),
                reservation.isAfterVisit(),
                reservation.getReservationDate()
        );
    }
    public List<ReservationDto> mapToReservationDtoList(final List<Reservation> reservationList){
        return reservationList.stream()
                .map(this::mapToReservationDto)
                .collect(Collectors.toList());
    }
}
