package com.clinic.grade;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/grade")
public class GradeController {

    private final GradeService gradeService;

    GradeController(final GradeService gradeService) {
        this.gradeService = gradeService;
    }

    @GetMapping
    public ResponseEntity<List<GradeDto>> getAllGrades(){
        return ResponseEntity.ok(gradeService.getAllGrades());
    }
    @GetMapping("/{id}")
    public ResponseEntity<GradeDto> getGradeById(@PathVariable final long id) throws GradeNotFoundException {
        return ResponseEntity.ok(gradeService.getGradeById(id));
    }

    @PostMapping
    public ResponseEntity<GradeDto> createNewGrade(@RequestBody GradeDto gradeDto){
        return ResponseEntity.ok(gradeService.createNewGrade(gradeDto));
    }
    @PutMapping
    private ResponseEntity<GradeDto> updateGrade(@RequestBody GradeDto gradeDto) throws GradeNotFoundException {
        return ResponseEntity.ok(gradeService.updateGrade(gradeDto));
    }
}
