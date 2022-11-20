package com.clinic.privateclinic.clinics.humans;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/medical")
public class MedicalCenterController {
    private final MedicalCenterService service;

    MedicalCenterController(final MedicalCenterService service) {
        this.service = service;
    }

    @GetMapping
    ResponseEntity<?> getMedicalCenterInfo(){
        return ResponseEntity.ok().build();
    }

    @PostMapping
    ResponseEntity<?> appointment(){
        return ResponseEntity.ok().build();
    }
}
