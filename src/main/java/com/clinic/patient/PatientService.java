package com.clinic.patient;

import com.clinic.grade.GradeDto;
import com.clinic.patient.disease.DiseaseStory;
import com.clinic.patient.disease.DiseaseStoryDto;
import com.clinic.patient.disease.DiseaseStoryService;
import com.clinic.person.enums.Sex;
import com.clinic.privateclinic.PrivateClinicDto;
import com.clinic.privateclinic.PrivateClinicNotFoundException;
import com.clinic.privateclinic.PrivateClinicService;
import com.clinic.reservation.Reservation;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PatientService {
    private final PatientRepository patientRepository;
    private final PatientMapper patientMapper;
    private final DiseaseStoryService diseaseStoryService;
    private final PrivateClinicService privateClinicService;

    PatientService(final PatientRepository patientRepository, final PatientMapper patientMapper, final DiseaseStoryService diseaseStoryService, final PrivateClinicService privateClinicService) {
        this.patientRepository = patientRepository;
        this.patientMapper = patientMapper;
        this.diseaseStoryService = diseaseStoryService;
        this.privateClinicService = privateClinicService;
    }

    public List<PatientDto> getAllPatients() {
        return patientMapper.mapToPatientDtoList(patientRepository.findAll());
    }

    public PatientDto getPatientDtoById(final long patientId) throws PatientNotFoundException {
        if (!patientRepository.existsById(patientId))
            throw new PatientNotFoundException();
        return patientMapper.mapToPatientDto(patientRepository.findById(patientId));
    }

    public PatientDto createNewPatient(PatientDto patientDto, int sex, String reasonComingToClinic){
        return patientMapper.mapToPatientDto(createPatient(patientDto,sex,reasonComingToClinic,new Reservation()));
    }

    public Patient createPatient(final PatientDto patientDto, int sex, String reasonComingToClinic, Reservation reservation) {
        switch (sex){
            case 0:
                patientDto.setSex(Sex.MALE);
                break;
            case 1:
                patientDto.setSex(Sex.FEMALE);
                break;
            default:
                throw new IllegalStateException("0-MALE,1-FEMALE");
        }
        Patient patient = patientMapper.mapToPatient(patientDto, reasonComingToClinic);
        patient.setReservations(reservation);
        return patientRepository.save(patient);
    }

    public PatientDto updatePatient(final PatientDto patientDto) throws PatientNotFoundException {
        if (!patientRepository.existsById(patientDto.getId()))
            throw new PatientNotFoundException();
        if (patientRepository.findById(patientDto.getId()).getAge() > patientDto.getAge())
            throw new IllegalArgumentException("Sorry bro, you can't be younger");
        return changePatient(patientRepository.findById(patientDto.getId()),patientDto);
    }

    public List<DiseaseStoryDto> getDiseasesStoryByPatientId(final long patientId) throws PatientNotFoundException {
        if (!patientRepository.existsById(patientId))
            throw new PatientNotFoundException();
        Patient patient = patientRepository.findById(patientId);
        return diseaseStoryService.getDiseaseStoryDtoList(patient.getDiseaseStory());
    }

    public PrivateClinicDto rateClinic(final GradeDto gradeDto,final long clinicId, final long patientId)
            throws PrivateClinicNotFoundException, PatientNotFoundException {
        if (!patientRepository.existsById(patientId))
            throw new PatientNotFoundException();
        String patientName = patientRepository.findById(patientId).getName();
        return privateClinicService.rateClinic(gradeDto,clinicId,patientName,patientId);
    }

    public DiseaseStoryDto addDescriptionDiseaseToDiseaseStory(final DiseaseStoryDto diseaseStoryDto, long patientId) throws PatientNotFoundException {
        if (!patientRepository.existsById(patientId))
            throw new PatientNotFoundException();
        Patient patient = patientRepository.findById(patientId);
        DiseaseStory diseaseStory = diseaseStoryService.createDiseaseStory(diseaseStoryDto,patientId);
        patient.setDiseasesStory(diseaseStory);
        patientRepository.save(patient);
        return diseaseStoryService.getDiseaseStoryDto(diseaseStory);
    }


    public boolean patientExist(final long patientId) {
        return patientRepository.existsById(patientId);
    }

    public Patient getPatientById(final long patientId) {
        return patientRepository.findById(patientId);
    }

    public List<Reservation> getReservationByPatientId(final long patientId) throws PatientNotFoundException {
        if (!patientRepository.existsById(patientId))
            throw new PatientNotFoundException();
        return patientRepository.findById(patientId).getReservations();
    }
    private PatientDto changePatient(Patient patient, PatientDto patientDto){
        Patient patientToUpdate = patientRepository.findById(patientDto.getId());
        patientDto.setDiseaseStory(patientToUpdate.getDiseaseStory());
        patientDto.setReservation(patient.getReservations());
        patientDto.setSex(patientToUpdate.getSex());
        return patientMapper.mapToPatientDto(patientRepository.save(patientMapper.mapToPatient(patientDto,patient.getReasonComingToClinic())));
    }
}
