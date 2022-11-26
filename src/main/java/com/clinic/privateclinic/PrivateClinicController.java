package com.clinic.privateclinic;

import com.clinic.patient.PatientNotFoundException;
import com.clinic.staff.StaffDto;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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
    @PostMapping
    public ResponseEntity<PrivateClinicDto> createClinic(@RequestBody PrivateClinicDto privateClinicDto){
        return ResponseEntity.ok(privateClinicService.createNewClinic(privateClinicDto));
    }
    @PatchMapping("{clinicId}")
    public ResponseEntity<PrivateClinicDto> changeClinicName(@RequestParam String clinicName, @PathVariable long clinicId)
            throws PrivateClinicNotFoundException {
        return ResponseEntity.ok(privateClinicService.changeClinicName(clinicName,clinicId));
    }
    @PutMapping("/register/{clinicId}/{patientId}")
    public ResponseEntity<PrivateClinicDto> registerPatient(@PathVariable long clinicId, @PathVariable long patientId) throws PatientNotFoundException, PrivateClinicNotFoundException {
        return ResponseEntity.ok(privateClinicService.registerNewPatient(clinicId,patientId));
    }
}
