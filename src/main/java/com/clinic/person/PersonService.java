package com.clinic.person;

import com.clinic.person.enums.Sex;
import com.clinic.person.enums.Vocation;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PersonService {

    private final PersonRepository repository;
    private final PersonMapper mapper;

    PersonService(final PersonRepository repository, final PersonMapper mapper) {
        this.repository = repository;
        this.mapper = mapper;
    }

    public List<PersonDto> getAllPeople(){
        return mapper.mapToPersonDtoList(repository.findAll());
    }

    public List<PersonDto> getAllStaff(){
            return mapper.mapToPersonDtoList(repository.findByVocation(Vocation.STAFF));
    }
    public List<PersonDto> getAllPatient(){
            return mapper.mapToPersonDtoList(repository.findByVocation(Vocation.PATIENT));
    }

    public boolean createPerson(PersonDto personDto, long vocation, long sex){
        if (personDto.getAge() < 18 && vocation == 1)
            throw new IllegalStateException("You are too younger to be staff.");
        if (vocation <= 1) {
            if (vocation == 0 )
                personDto.setVocation(Vocation.PATIENT);
            else
                personDto.setVocation(Vocation.STAFF);
        } else
            throw new IllegalStateException("0 for patient - 1 for staff");
        if (sex <= 1){
            if (sex == 0)
                personDto.setSex(Sex.MALE);
            else
                personDto.setSex(Sex.FEMALE);
        } else
            throw new IllegalStateException("0 for Male - 1 for Female ");
        Person person = mapper.mapToPerson(personDto);
        repository.save(person);
        return true;
    }
}
