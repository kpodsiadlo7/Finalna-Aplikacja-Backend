package com.clinic.person;

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
    @PostMapping
    ResponseEntity<Boolean> createPerson(@RequestBody PersonDto personDto, @RequestParam long vocationId, @RequestParam long sexId){
        return ResponseEntity.ok(service.createPerson(personDto, vocationId,sexId));
    }

}
