package com.clinic.privateclinic;

import com.clinic.patient.PatientNotFoundException;
import com.clinic.staff.StaffDto;
import com.clinic.staff.StaffNotFoundException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.transaction.Transactional;
import java.util.List;

@RestController
@RequestMapping("/clinic")
public class PrivateClinicController {
    private final PrivateClinicService privateClinicService;
    PrivateClinicController(final PrivateClinicService privateClinicService) {
        this.privateClinicService = privateClinicService;
    }

    @GetMapping
    public ResponseEntity<List<PrivateClinicDto>> getAllClinics(){
        return ResponseEntity.ok(privateClinicService.getAllClinics());
    }
    @GetMapping("{clinicId}")
    public ResponseEntity<PrivateClinicDto> getClinicById(@PathVariable long clinicId)
            throws PrivateClinicNotFoundException {
        return ResponseEntity.ok(privateClinicService.getClinicById(clinicId));
    }
    @GetMapping("/staff/{clinicId}")
    public ResponseEntity<List<StaffDto>> getAllStaffFromClinicByClinicId(@PathVariable long clinicId)
            throws PrivateClinicNotFoundException {
        return ResponseEntity.ok(privateClinicService.getAllStaffByClinicId(clinicId));
    }
    @Transactional
    @PostMapping
    public ResponseEntity<PrivateClinicDto> createClinic(@RequestBody PrivateClinicDto privateClinicDto){
        return ResponseEntity.ok(privateClinicService.createNewClinic(privateClinicDto));
    }
    @Transactional
    @PatchMapping("{clinicId}")
    public ResponseEntity<PrivateClinicDto> changeClinicName(@RequestParam String clinicName, @PathVariable long clinicId)
            throws PrivateClinicNotFoundException {
        return ResponseEntity.ok(privateClinicService.changeClinicName(clinicName,clinicId));
    }
    @Transactional
    @PutMapping("/register/{patientId}/{clinicId}")
    public ResponseEntity<PrivateClinicDto> registerPatient(@PathVariable long clinicId, @PathVariable long patientId) throws PatientNotFoundException, PrivateClinicNotFoundException {
        return ResponseEntity.ok(privateClinicService.registerNewPatient(clinicId,patientId));
    }
    @Transactional
    @PutMapping("/addstaff/{staffId}/{clinicId}")
    public ResponseEntity<PrivateClinicDto> addStaffToClinic(@PathVariable long staffId, @PathVariable long clinicId)
            throws PrivateClinicNotFoundException, StaffNotFoundException {
        return ResponseEntity.ok(privateClinicService.addStaffToClinic(staffId,clinicId));
    }
    @Transactional
    @PatchMapping("/removestaff/{staffId}/{clinicId}")
    public ResponseEntity<PrivateClinicDto> removeStaffFromClinic(@PathVariable long staffId, @PathVariable long clinicId)
            throws StaffNotFoundException, PrivateClinicNotFoundException {
        return ResponseEntity.ok(privateClinicService.removeStaffFromClinic(staffId,clinicId));
    }
}
