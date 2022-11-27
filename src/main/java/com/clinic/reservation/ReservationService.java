package com.clinic.reservation;

import com.clinic.patient.PatientDto;
import com.clinic.patient.PatientNotFoundException;
import com.clinic.patient.PatientService;
import com.clinic.privateclinic.PrivateClinicNotFoundException;
import com.clinic.privateclinic.PrivateClinicService;
import com.clinic.reservation.enums.Currency;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
public class ReservationService {
    private final ReservationRepository reservationRepository;
    private final ReservationMapper reservationMapper;
    private final PrivateClinicService privateClinicService;
    private final PatientService patientService;

    ReservationService(final ReservationRepository reservationRepository, final ReservationMapper reservationMapper, final PrivateClinicService privateClinicService, final PatientService patientService) {
        this.reservationRepository = reservationRepository;
        this.reservationMapper = reservationMapper;
        this.privateClinicService = privateClinicService;
        this.patientService = patientService;
    }

    public List<ReservationDto> getAllReservations() {
        return reservationMapper.mapToReservationDtoList(reservationRepository.findAll());
    }

    public List<ReservationDto> getAllClosedReservation() {
        return reservationMapper.mapToReservationDtoList(reservationRepository.findByAfterVisitIsTrue());
    }
    public List<ReservationDto> getAllOpenReservation() {
        return reservationMapper.mapToReservationDtoList(reservationRepository.findByAfterVisitIsFalse());
    }

    public ReservationDto createNewReservation(final long clinicId, final PatientDto patientDto, int sex, String reasonComingToClinic, int currency, LocalDate reservationDate)
            throws PatientNotFoundException, PrivateClinicNotFoundException {
        if (reservationDate.isBefore(LocalDate.now()))
            throw new IllegalArgumentException("Date should not be past");
        Reservation reservation = new Reservation(changeCurrency(currency),reservationDate);
        reservationRepository.save(reservation);
        privateClinicService.registerNewPatient(clinicId,patientService.createPatient(patientDto,sex,reasonComingToClinic,reservation).getId());
        return reservationMapper.mapToReservationDto(reservation);
    }

    public ReservationDto closeReservation(final long reservationId) throws ReservationNotFoundException {
        if (!reservationRepository.existsById(reservationId))
            throw new ReservationNotFoundException();
        if (reservationRepository.findById(reservationId).isAfterVisit())
            throw new IllegalArgumentException("This reservation is already closed!");
        Reservation reservation = reservationRepository.findById(reservationId);
        reservation.closeReservation();
        reservationRepository.save(reservation);
        return reservationMapper.mapToReservationDto(reservation);
    }

    public List<ReservationDto> getAllReservationsByPatientId(final long patientId) throws PatientNotFoundException {
        return reservationMapper.mapToReservationDtoList(patientService.getReservationByPatientId(patientId));
    }

    public ReservationDto changeReservationDate(final long reservationId, final LocalDate visitDate) throws ReservationNotFoundException {
        if (!reservationRepository.existsById(reservationId))
            throw new ReservationNotFoundException();
        Reservation reservation = reservationRepository.findById(reservationId);
        if (!reservation.changeReservationDate(visitDate))
            throw new IllegalArgumentException("Date should not be past");
        return reservationMapper.mapToReservationDto(reservationRepository.save(reservation));
    }

    public ReservationDto changeCurrency(final long reservationId, final int newCurrency) throws ReservationNotFoundException {
        if (!reservationRepository.existsById(reservationId))
            throw new ReservationNotFoundException();
        Reservation reservation = reservationRepository.findById(reservationId);
        reservation.setCurrencyPayment(changeCurrency(newCurrency));
        return reservationMapper.mapToReservationDto(reservationRepository.save(reservation));
    }
    private Currency changeCurrency(int currency){
        Currency currencyPayment;
        switch (currency) {
            case 0:
                currencyPayment = Currency.PLN;
                break;
            case 1:
                currencyPayment = Currency.EUR;
                break;
            case 2:
                currencyPayment = Currency.USD;
                break;
            case 3:
                currencyPayment = Currency.CASH;
                break;
            default:
                throw new IllegalArgumentException("0-PLN,1-EUR,2-USD,3-CASH");
        }
        return currencyPayment;
    }
}
