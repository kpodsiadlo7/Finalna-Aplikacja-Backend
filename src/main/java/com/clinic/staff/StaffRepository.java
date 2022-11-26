package com.clinic.staff;

import com.clinic.person.enums.BaseProfession;
import com.clinic.person.Person;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface StaffRepository extends JpaRepository<Staff, Long> {
    List<Staff> findByBaseProfession(BaseProfession baseProfession);
    Staff findById(long staffId);

}
