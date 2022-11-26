package com.clinic.grade;

import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class GradeMapper {
    public Grade mapToGrade(final GradeDto gradeDto){
        return new Grade(
                gradeDto.getNickname(),
                gradeDto.getDescription(),
                gradeDto.getGrade()
        );
    }

    public Grade updateGrade(final GradeDto gradeDto){
        return new Grade(
                gradeDto.getId(),
                gradeDto.getNickname(),
                gradeDto.getDescription(),
                gradeDto.getGrade()
        );
    }

    public GradeDto mapToGradeDto(final Grade grade){
        return new GradeDto(
                grade.getId(),
                grade.getNickname(),
                grade.getDescription(),
                grade.getGrade()
        );
    }

    public List<GradeDto> mapToGradeDtoList(final List<Grade> gradeList){
        return gradeList.stream()
                .map(this::mapToGradeDto)
                .collect(Collectors.toList());
    }
}
