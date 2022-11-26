package com.clinic.privateclinic;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PrivateClinicRepository extends JpaRepository<PrivateClinic, Long> {
    PrivateClinic findById(long id);
}
