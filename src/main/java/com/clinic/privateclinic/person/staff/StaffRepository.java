package com.clinic.privateclinic.person.staff;

import com.clinic.privateclinic.person.Person;
import com.clinic.privateclinic.person.enums.BaseProfession;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface StaffRepository extends JpaRepository<Staff, Long> {
    List<Staff> findByBaseProfession(BaseProfession baseProfession);
    void save(Person staff);

}
