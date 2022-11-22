package com.clinic.privateclinic.person.staff.doctor;

import com.clinic.privateclinic.patient.Patient;
import com.clinic.privateclinic.person.enums.BaseProfession;
import com.clinic.privateclinic.person.enums.Sex;
import com.clinic.privateclinic.person.enums.Vocation;
import com.clinic.privateclinic.person.staff.Staff;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "DOCTORS")
public class Doctor extends Staff {

    public Doctor(){
    }

    public Doctor(final String name, final String surname, final Sex sex,final int age) {
        super(name, surname, sex, Vocation.STAFF, age, BaseProfession.DOCTOR);
    }

    @ManyToMany
    @JoinTable(
            name = "DoctorsAndPatients",
            inverseJoinColumns = @JoinColumn(name = "DOCTOR_ID"),
            joinColumns = @JoinColumn(name = "PATIENT_ID")
    )
    private List<Patient> patients;
}
