package com.clinic.patient.disease;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DiseaseStoryRepository extends JpaRepository<DiseaseStory, Long> {
    DiseaseStory findById(long diseaseId);
}
