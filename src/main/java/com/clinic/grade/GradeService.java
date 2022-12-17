package com.clinic.grade;

import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class GradeService {
    private final GradeMapper gradeMapper;
    private final GradeRepository gradeRepository;

    GradeService(final GradeMapper gradeMapper, final GradeRepository gradeRepository) {
        this.gradeMapper = gradeMapper;
        this.gradeRepository = gradeRepository;
    }

    public List<GradeDto> getAllGrades() {
        return gradeMapper.mapToGradeDtoList(gradeRepository.findAll());
    }

    public GradeDto getGradeById(final long gradeId) throws GradeNotFoundException {
        if (!gradeRepository.existsById(gradeId))
            throw new GradeNotFoundException();
        return gradeMapper.mapToGradeDto(gradeRepository.findById(gradeId));
    }

    public GradeDto createNewGrade(final GradeDto gradeDto) {
        if (gradeDto.getGrade() > 10 && gradeDto.getGrade() < 1)
            throw new IllegalStateException("Grade 1-10");
        return gradeMapper.mapToGradeDto(gradeRepository.save(gradeMapper.mapToGrade(gradeDto)));
    }

    public GradeDto updateGrade(final GradeDto gradeDto) throws GradeNotFoundException {
        if (!gradeRepository.existsById(gradeDto.getId()))
            throw new GradeNotFoundException();
        if (gradeDto.getGrade() > 10 && gradeDto.getGrade() < 1)
            throw new IllegalStateException("Grade 1-10");
        return gradeMapper.mapToGradeDto(gradeMapper.updateGrade(gradeDto));
    }

    public Grade patientRate(final GradeDto gradeDto, final String patientName, final long patientId) {
        if (gradeDto.getGrade() > 10 && gradeDto.getGrade() < 1)
            throw new IllegalStateException("Grade 1-10");
        Grade grade = gradeMapper.mapToGrade(gradeDto);
        grade.setNickname(patientName);
        grade.setPatientId(patientId);
        return gradeRepository.save(grade);
    }
}
