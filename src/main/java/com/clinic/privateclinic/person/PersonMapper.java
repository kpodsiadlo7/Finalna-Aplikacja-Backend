package com.clinic.privateclinic.person;

import com.clinic.privateclinic.person.enums.Sex;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class PersonMapper {

    public Person mapToPerson(final PersonDto personDto){
        return new Person(
                personDto.getName(),
                personDto.getSurname(),
                personDto.getSex(),
                personDto.getVocation(),
                personDto.getAge()
        );
    }

    public PersonDto mapToPersonDto(final Person person){
        return new PersonDto(
                person.getId(),
                person.getName(),
                person.getSurname(),
                person.getSex(),
                person.getVocation(),
                person.getAge()
        );
    }

    public List<PersonDto> mapToPersonDtoList(final List<Person> personList){
        return personList.stream()
                .map(this::mapToPersonDto)
                .collect(Collectors.toList());
    }
}
