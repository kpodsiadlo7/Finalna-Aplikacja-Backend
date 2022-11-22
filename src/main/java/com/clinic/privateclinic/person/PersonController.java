package com.clinic.privateclinic.person;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/person")
public class PersonController {

    private final PersonService service;

    PersonController(final PersonService service) {
        this.service = service;
    }

    @GetMapping
    ResponseEntity<List<PersonDto>> getAllPeople(){
        return ResponseEntity.ok(service.getAllPeople());
    }

    @GetMapping("/staff")
    ResponseEntity<List<PersonDto>> getAllStaff(){
        return ResponseEntity.ok(service.getAllStaff());
    }

    @GetMapping("/patient")
    ResponseEntity<List<PersonDto>> getAllPatient(){
        return ResponseEntity.ok(service.getAllPatient());
    }

    @PostMapping
    ResponseEntity<Boolean> createPerson(@RequestBody PersonDto personDto, @RequestParam long vocationId, @RequestParam long sexId){
        return ResponseEntity.ok(service.createPerson(personDto, vocationId,sexId));
    }

}
