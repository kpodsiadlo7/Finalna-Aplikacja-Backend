package com.clinic.patient;

import com.clinic.patient.disease.DiseaseStory;
import com.clinic.patient.disease.DiseaseStoryDto;
import com.clinic.patient.disease.DiseaseStoryNotFoundException;
import com.clinic.patient.disease.DiseaseStoryService;
import com.clinic.person.enums.Sex;
import com.clinic.privateclinic.PrivateClinicDto;
import com.clinic.privateclinic.PrivateClinicNotFoundException;
import com.clinic.privateclinic.PrivateClinicService;
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


    public PatientDto createNewPatient(final PatientDto patientDto, int sex, String reasonComingToClinic) {
        if (sex >= 0 && sex <= 1){
            patientDto.setSex(Sex.MALE);
            if (sex == 1)
                patientDto.setSex(Sex.FEMALE);
        }
        Patient patient = patientMapper.mapToPatient(patientDto, reasonComingToClinic);
        patientRepository.save(patient);
        return patientMapper.mapToPatientDto(patient);
    }

    public PatientDto updatePatient(final PatientDto patientDto) throws PatientNotFoundException {
        if (!patientRepository.existsById(patientDto.getId()))
            throw new PatientNotFoundException();
        Patient patient = patientMapper.updatePatient(patientDto);
        return patientMapper.mapToPatientDto(patientRepository.save(patient));
    }

    public List<DiseaseStoryDto> getDiseasesStoryByPatientId(final long patientId) throws PatientNotFoundException {
        if (!patientRepository.existsById(patientId))
            throw new PatientNotFoundException();
        Patient patient = patientRepository.findById(patientId);
        return diseaseStoryService.getDiseaseStoryDtoList(patient.getDiseaseStory());
    }

    public List<DiseaseStoryDto> getAllDiseasesStory() {
        return diseaseStoryService.getAllDiseasesStory();
    }

    public DiseaseStoryDto updateDiseaseStoryByPatientId(final DiseaseStoryDto diseaseStoryDto, final long patientId)
            throws PatientNotFoundException, DiseaseStoryNotFoundException {
        if (!patientRepository.existsById(patientId))
            throw new PatientNotFoundException();
        return diseaseStoryService.updateDiseaseStoryByPatientId(diseaseStoryDto);
    }

    public PrivateClinicDto rateClinic(final String desc, double rate, final long clinicId, final long patientId)
            throws PrivateClinicNotFoundException, PatientNotFoundException {
        if (!patientRepository.existsById(patientId))
            throw new PatientNotFoundException();
        String patientName = patientRepository.findById(patientId).getName();
        return privateClinicService.rateClinic(desc,rate,clinicId,patientName);
    }

    public DiseaseStoryDto addDescriptionDiseaseToDiseaseStory(final DiseaseStoryDto diseaseStoryDto, long patientId) throws PatientNotFoundException {
        if (!patientRepository.existsById(patientId))
            throw new PatientNotFoundException();
        Patient patient = patientRepository.findById(patientId);
        DiseaseStory diseaseStory = diseaseStoryService.createDiseaseStory(diseaseStoryDto);
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
}
