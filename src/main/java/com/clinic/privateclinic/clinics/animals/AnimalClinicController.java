package com.clinic.privateclinic.clinics.animals;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/animal")
public class AnimalClinicController {
    private final AnimalClinicService service;

    AnimalClinicController(final AnimalClinicService service) {
        this.service = service;
    }

    @GetMapping
    public ResponseEntity<?> getAnimalClinicInfo(){
        return ResponseEntity.ok().build();
    }

    @PostMapping
    public ResponseEntity<?> appointment(){
        return ResponseEntity.ok().build();
    }
}
