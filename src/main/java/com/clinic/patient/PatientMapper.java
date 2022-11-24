package com.clinic.patient;

import com.clinic.patient.disease.DiseaseStory;
import com.clinic.reservation.Reservation;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class PatientMapper {
    public Patient mapToPatient(final PatientDto patientDto){
        return new Patient(
                patientDto.getName(),
                patientDto.getSurname(),
                patientDto.getSex(),
                patientDto.getAge()
        );
    }
    public PatientDto mapToPatientDto(final Patient patient){
        return new PatientDto(
                patient.getId(),
                patient.getName(),
                patient.getSurname(),
                patient.getSex(),
                patient.getVocation(),
                patient.getAge(),
                patient.getDiseaseStory().stream().map(DiseaseStory::getId).collect(Collectors.toList()),
                patient.getReservations().stream().map(Reservation::getId).collect(Collectors.toList())
        );
    }
    public List<PatientDto> mapToPatientDtoList(final List<Patient> patientList){
        return patientList.stream()
                .map(this::mapToPatientDto)
                .collect(Collectors.toList());
    }
}
