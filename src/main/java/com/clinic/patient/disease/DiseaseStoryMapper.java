package com.clinic.patient.disease;

import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class DiseaseStoryMapper {
    public DiseaseStory mapToDiseaseStory(final DiseaseStoryDto diseaseStoryDto){
        return new DiseaseStory(
                diseaseStoryDto.getDescription()
        );
    }
    public DiseaseStory diseaseStoryToUpdate(final DiseaseStoryDto diseaseStoryDto){
        return new DiseaseStory(
                diseaseStoryDto.getId(),
                diseaseStoryDto.getDescription()
        );
    }
    public DiseaseStoryDto mapToDiseaseStoryDto(final DiseaseStory diseaseStory){
        return new DiseaseStoryDto(
                diseaseStory.getId(),
                diseaseStory.getDescription()
        );
    }
    public List<DiseaseStoryDto> mapToDiseaseStoryDtoList(final List<DiseaseStory> diseaseStoryList){
        return diseaseStoryList.stream()
                .map(this::mapToDiseaseStoryDto)
                .collect(Collectors.toList());
    }
}
