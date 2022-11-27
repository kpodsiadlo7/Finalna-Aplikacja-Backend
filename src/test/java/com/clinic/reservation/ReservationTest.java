package com.clinic.reservation;

import com.clinic.patient.disease.DiseaseStory;
import com.clinic.patient.Patient;
import com.clinic.patient.PatientRepository;
import com.clinic.patient.disease.DiseaseStoryRepository;
import com.clinic.person.enums.Sex;
import com.clinic.reservation.enums.Currency;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDate;
import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class ReservationTest {

    @Autowired
    private ReservationRepository reservationRepository;
    @Autowired
    private PatientRepository patientRepository;
    @Autowired
    private DiseaseStoryRepository diseaseStoryRepository;

    @Test
    @DisplayName("CreateReservationAndAddToPatientWithDiseaseStory")
    void shouldCreateEmptyReservationWithDiseaseStory(){
        // given
        Reservation reservation = new Reservation(Currency.USD, LocalDate.now().plusDays(6));
        reservationRepository.save(reservation);
        // and
        DiseaseStory diseaseStory = new DiseaseStory("Połamana noga");
        diseaseStoryRepository.save(diseaseStory);
        // and
        Patient patient1 = new Patient("patient","surname", Sex.MALE,23,"arm hurts");
        patientRepository.save(patient1);
        patient1.setReservations(reservation);
        patient1.setDiseasesStory(diseaseStory);
        patientRepository.save(patient1);
        // when
        String diseaseStoryActual = patient1.getDiseaseStory().stream().map(DiseaseStory::getDescription).findAny().get();
        // then
        assertEquals("Połamana noga",diseaseStoryActual);
        assertFalse(reservation.isAfterVisit());

        //CleanUp
        diseaseStoryRepository.deleteAll();
        reservationRepository.deleteAll();
        patientRepository.deleteAll();
    }
}