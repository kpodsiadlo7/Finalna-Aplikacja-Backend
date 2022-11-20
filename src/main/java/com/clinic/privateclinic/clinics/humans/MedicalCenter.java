package com.clinic.privateclinic.clinics.humans;

import com.clinic.privateclinic.clinics.base.Clinic;
import com.clinic.privateclinic.patient.Patient;

import javax.persistence.Entity;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "MEDICALS_CENTER")
public final class MedicalCenter extends Clinic {
    private static final MedicalCenter SINGLETON = new MedicalCenter();
    private static final String name = "Medical Center";

    public MedicalCenter(){
        if (SINGLETON != null)
            throw new IllegalStateException("Singleton already constructed");
    }

    @OneToMany
    private List<Patient> patients = new ArrayList<>();

    void setPatients(final List<Patient> patients) {
        this.patients = patients;
    }

    List<Patient> getPatients() {
        return patients;
    }

    static String getName() {
        return name;
    }
}
