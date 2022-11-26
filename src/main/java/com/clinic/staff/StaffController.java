package com.clinic.staff;

import com.clinic.patient.PatientNotFoundException;
import com.clinic.patient.disease.DiseaseStoryDto;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.transaction.Transactional;
import java.util.List;

@RestController
@RequestMapping("/staff")
public class StaffController {
    private final StaffService staffService;

    StaffController(final StaffService staffService) {
        this.staffService = staffService;
    }

    @GetMapping
    public ResponseEntity<List<StaffDto>> getAllStaff(){
        return ResponseEntity.ok(staffService.getAllStaff());
    }
    @GetMapping("/{staffId}")
    public ResponseEntity<StaffDto> getStaffById(@PathVariable long staffId)
            throws StaffNotFoundException {
        return ResponseEntity.ok(staffService.getStaffById(staffId));
    }
    @PostMapping
    public ResponseEntity<StaffDto> createNewStaff(@RequestBody StaffDto staffDto, @RequestParam int profession, @RequestParam int sex){
        return ResponseEntity.ok(staffService.createNewStaff(staffDto,profession,sex));
    }
    @Transactional
    @PostMapping("/disease/{patientId}")
    public ResponseEntity<DiseaseStoryDto> addDescriptionDiseaseToDiseaseStoryByPatientId
            (@RequestBody DiseaseStoryDto diseaseStoryDto, @PathVariable long patientId) throws PatientNotFoundException {
        return ResponseEntity.ok(staffService.addDescriptionDiseaseToDiseaseStoryByPatientId(diseaseStoryDto,patientId));
    }
}
