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
        Grade grade = gradeMapper.mapToGrade(gradeDto);
        gradeRepository.save(grade);
        return gradeMapper.mapToGradeDto(gradeMapper.mapToGrade(gradeDto));
    }

    public GradeDto updateGrade(final GradeDto gradeDto) throws GradeNotFoundException {
        if (!gradeRepository.existsById(gradeDto.getId()))
            throw new GradeNotFoundException();
        return gradeMapper.mapToGradeDto(gradeMapper.updateGrade(gradeDto));
    }
    public Grade newRate(final String desc, final double rate, String patientName){
        GradeDto newGrade = new GradeDto(patientName,desc,rate);
        createNewGrade(newGrade);
        gradeRepository.save(gradeMapper.mapToGrade(newGrade));
        return gradeMapper.mapToGrade(newGrade);
    }
}
