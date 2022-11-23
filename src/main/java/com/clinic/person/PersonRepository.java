package com.clinic.person;

import com.clinic.person.enums.Sex;
import com.clinic.person.enums.Vocation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PersonRepository extends JpaRepository<Person,Long> {
    List<Person> findByVocation(final Vocation vocation);
    List<Person> findBySex(final Sex sex);
}
