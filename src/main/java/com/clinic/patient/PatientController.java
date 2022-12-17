package com.clinic.patient;

import com.clinic.grade.GradeDto;
import com.clinic.patient.disease.DiseaseStoryDto;
import com.clinic.patient.disease.DiseaseStoryNotFoundException;
import com.clinic.privateclinic.PrivateClinicDto;
import com.clinic.privateclinic.PrivateClinicNotFoundException;
import com.clinic.staff.StaffDto;
import com.clinic.staff.StaffNotFoundException;
import com.clinic.staff.StaffService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.transaction.Transactional;
import java.util.List;

@RestController
@RequestMapping("/patient")
public class PatientController {

    private final PatientService patientService;
    private final StaffService staffService;

    PatientController(final PatientService patientService, final StaffService staffService) {
        this.patientService = patientService;
        this.staffService = staffService;
    }

    @GetMapping
    public ResponseEntity<List<PatientDto>> getAllPatients(){
        return ResponseEntity.ok(patientService.getAllPatients());
    }
    @GetMapping("{patientId}")
    public ResponseEntity<PatientDto> getPatientById(@PathVariable long patientId)
            throws PatientNotFoundException {
        return ResponseEntity.ok(patientService.getPatientDtoById(patientId));
    }
    @GetMapping("/disease/{patientId}")
    public ResponseEntity<List<DiseaseStoryDto>> getDiseasesStoryByPatientId(@PathVariable long patientId)
            throws PatientNotFoundException {
        return ResponseEntity.ok(patientService.getDiseasesStoryByPatientId(patientId));
    }

    @Transactional
    @PostMapping
    public ResponseEntity<PatientDto> createPatient(@RequestBody PatientDto patientDto, @RequestParam int sex, @RequestParam String reasonComingToClinic){
        return ResponseEntity.ok(patientService.createNewPatient(patientDto,sex,reasonComingToClinic));
    }

    @Transactional
    @PutMapping
    public ResponseEntity<PatientDto> updatePatient(@RequestBody PatientDto patientDto) throws PatientNotFoundException {
        return ResponseEntity.ok(patientService.updatePatient(patientDto));
    }

    @Transactional
    @PutMapping("/rateclinic")
    public ResponseEntity<PrivateClinicDto> rateClinic(@RequestBody GradeDto gradeDto, @RequestParam long clinicId, @RequestParam long patientId)
            throws PrivateClinicNotFoundException, PatientNotFoundException {
        return ResponseEntity.ok(patientService.rateClinic(gradeDto,clinicId,patientId));
    }
    @Transactional
    @PutMapping("/ratestaff/{staffId}/{patientId}")
    public ResponseEntity<StaffDto> rateStaff(@RequestBody GradeDto gradeDto, @PathVariable long staffId, @PathVariable long patientId) throws PatientNotFoundException, StaffNotFoundException {
        if (!patientService.patientExist(patientId))
            throw new PatientNotFoundException();
        return ResponseEntity.ok(staffService.rateStaff(gradeDto,staffId,patientService.getPatientById(patientId).getName(),patientId));
    }
}
