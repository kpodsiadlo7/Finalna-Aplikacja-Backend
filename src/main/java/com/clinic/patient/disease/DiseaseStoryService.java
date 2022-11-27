package com.clinic.patient.disease;

import com.clinic.patient.Patient;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DiseaseStoryService {
    private final DiseaseStoryMapper diseaseStoryMapper;
    private final DiseaseStoryRepository diseaseStoryRepository;

    public DiseaseStoryService(final DiseaseStoryMapper diseaseStoryMapper, final DiseaseStoryRepository diseaseStoryRepository) {
        this.diseaseStoryMapper = diseaseStoryMapper;
        this.diseaseStoryRepository = diseaseStoryRepository;
    }
    public DiseaseStory createDiseaseStory(final DiseaseStoryDto diseaseStoryDto) {
        DiseaseStory diseaseStory = diseaseStoryMapper.mapToDiseaseStory(diseaseStoryDto);
        return diseaseStoryRepository.save(diseaseStory);
    }

    public List<DiseaseStoryDto> getDiseaseStoryDtoList(final List<DiseaseStory> diseaseStoryList) {
        return diseaseStoryMapper.mapToDiseaseStoryDtoList(diseaseStoryList);
    }

    public List<DiseaseStoryDto> getAllDiseasesStory() {
        return diseaseStoryMapper.mapToDiseaseStoryDtoList(diseaseStoryRepository.findAll());
    }

    public DiseaseStoryDto updateDiseaseStoryByPatientId(final DiseaseStoryDto diseaseStoryDto) throws DiseaseStoryNotFoundException {
        if (!diseaseStoryRepository.existsById(diseaseStoryDto.getId()))
            throw new DiseaseStoryNotFoundException();
        DiseaseStory diseaseStory = diseaseStoryRepository.findById(diseaseStoryDto.getId());
        diseaseStoryRepository.save(diseaseStoryMapper.diseaseStoryToUpdate(diseaseStoryDto));
        return diseaseStoryMapper.mapToDiseaseStoryDto(diseaseStory);
    }

    public DiseaseStoryDto getDiseaseStoryDto(final DiseaseStory diseaseStory) {
        return diseaseStoryMapper.mapToDiseaseStoryDto(diseaseStory);
    }
}
