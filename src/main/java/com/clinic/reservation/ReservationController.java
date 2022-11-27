package com.clinic.reservation;

import com.clinic.patient.PatientDto;
import com.clinic.patient.PatientNotFoundException;
import com.clinic.privateclinic.PrivateClinicNotFoundException;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.transaction.Transactional;
import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping("/reservation")
public class ReservationController {

    private final ReservationService reservationService;

    ReservationController(final ReservationService reservationService) {
        this.reservationService = reservationService;
    }

    @GetMapping
    public ResponseEntity<List<ReservationDto>> getAllReservations(){
        return ResponseEntity.ok(reservationService.getAllReservations());
    }
    @GetMapping("/closed")
    public ResponseEntity<List<ReservationDto>> getAllClosedReservation(){
        return ResponseEntity.ok(reservationService.getAllClosedReservation());
    }
    @GetMapping("/opened")
    public ResponseEntity<List<ReservationDto>> getAllOpenReservation(){
        return ResponseEntity.ok(reservationService.getAllOpenReservation());
    }
    @GetMapping("/patient/{patientId}")
    public ResponseEntity<List<ReservationDto>> getAllReservationsByPatientId(@PathVariable long patientId) throws PatientNotFoundException {
        return ResponseEntity.ok(reservationService.getAllReservationsByPatientId(patientId));
    }
    @Transactional
    @PostMapping
    public ResponseEntity<ReservationDto> createNewReservation(@RequestParam long clinicId, @RequestBody PatientDto patientDto,
                                                               @RequestParam int sex, @RequestParam String reasonComingToClinic,
                                                               @DateTimeFormat(pattern="yyyy-MM-dd") LocalDate visitDate,
                                                               int currency)
            throws PatientNotFoundException, PrivateClinicNotFoundException {
        return ResponseEntity.ok(reservationService.createNewReservation(clinicId,patientDto,sex,reasonComingToClinic,currency,visitDate));
    }

    @Transactional
    @PatchMapping("/close/{reservationId}")
    public ResponseEntity<ReservationDto> closeReservation(@PathVariable long reservationId) throws ReservationNotFoundException {
        return ResponseEntity.ok(reservationService.closeReservation(reservationId));
    }
    @Transactional
    @PatchMapping("/date/{reservationId}")
    public ResponseEntity<ReservationDto> changeReservationDate(@PathVariable long reservationId,
                                                                @DateTimeFormat(pattern = "yyyy-MM-dd")LocalDate newVisitDate)
            throws ReservationNotFoundException {
        return ResponseEntity.ok(reservationService.changeReservationDate(reservationId,newVisitDate));
    }
    @Transactional
    @PatchMapping("/currency/{reservationId}")
    public ResponseEntity<ReservationDto> changeCurrency(@PathVariable long reservationId,@RequestParam int newCurrency) throws ReservationNotFoundException {
        return ResponseEntity.ok(reservationService.changeCurrency(reservationId,newCurrency));
    }
}
