package com.clinic.staff;

import com.clinic.patient.Patient;
import com.clinic.patient.PatientRepository;
import com.clinic.person.enums.BaseProfession;
import com.clinic.person.enums.Sex;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class StaffTest {

    @Autowired
    private StaffRepository staffRepository;
    @Autowired
    private PatientRepository patientRepository;

    @Test
    @DisplayName("CreateProfessionDoctorNurseVet")
    void shouldCreateStaffProfession_Doctor_Nurse_Surgeon(){
        // given
        Staff doctor = new Staff("doktorek","surname", Sex.MALE,33, BaseProfession.DOCTOR);
        Staff nurse = new Staff("nurse","surname",Sex.FEMALE,23,BaseProfession.NURSE);
        Staff surgeon = new Staff("surgeon","surname",Sex.MALE,43,BaseProfession.SURGEON);
        // then & when
        staffRepository.save(doctor);
        staffRepository.save(nurse);
        staffRepository.save(surgeon);
        assertEquals(1, staffRepository.findByBaseProfession(BaseProfession.DOCTOR).size());
        assertEquals(1, staffRepository.findByBaseProfession(BaseProfession.NURSE).size());
        assertEquals(1, staffRepository.findByBaseProfession(BaseProfession.SURGEON).size());

        // CleanUp
        staffRepository.deleteAll();
    }

    @Test
    @DisplayName("CheckEveryVariableInStaff")
    void checkingEveryVariableInStaff(){
        // given
        Staff doctor = new Staff("doktorek","surname", Sex.MALE,33, BaseProfession.DOCTOR);
        Staff nurse = new Staff("nurse","surname",Sex.FEMALE,23,BaseProfession.NURSE);
        Staff surgeon = new Staff("surgeon","surname",Sex.MALE,43,BaseProfession.SURGEON);
        Staff secretary = new Staff("secretary","surname",Sex.FEMALE,23,BaseProfession.SECRETARY);
        staffRepository.save(doctor);
        staffRepository.save(nurse);
        staffRepository.save(surgeon);
        staffRepository.save(secretary);
        // when & then
        assertEquals("doktorek", doctor.getName());
        assertEquals("surname", nurse.getSurname());
        assertEquals(23, secretary.getAge());
        assertEquals(Sex.MALE, surgeon.getSex());
        assertEquals(4, staffRepository.findAll().size());

        //CleanUp
        staffRepository.deleteAll();
    }

    @Test
    @DisplayName("FillPatientListInStaffAndUpdateHospitalizedQuantity")
    void shouldFillPatientAndUpdateHospitalizedQuantity(){
        // given
        Staff doctor = new Staff("doktorek","surname", Sex.MALE,33, BaseProfession.DOCTOR);
        Staff nurse = new Staff("nurse","surname",Sex.FEMALE,23,BaseProfession.NURSE);
        Staff surgeon = new Staff("surgeon","surname",Sex.MALE,43,BaseProfession.SURGEON);
        staffRepository.save(doctor);
        staffRepository.save(nurse);
        staffRepository.save(surgeon);
        // and
        Patient patient1 = new Patient("patient","surname", Sex.MALE,23);
        Patient patient2 = new Patient("patient2","surname",Sex.FEMALE, 33);
        Patient patient3 = new Patient("patient3","surname",Sex.MALE,23);
        Patient patient4 = new Patient("patient4","surname",Sex.FEMALE,33);
        Patient patient5 = new Patient("patient5","surname",Sex.FEMALE,33);
        patientRepository.save(patient1);
        patientRepository.save(patient2);
        patientRepository.save(patient3);
        patientRepository.save(patient4);
        patientRepository.save(patient5);
        // when
        doctor.addPatient(patient1);
        surgeon.addPatient(patient1);
        doctor.addPatient(patient2);
        nurse.addPatient(patient3);
        surgeon.addPatient(patient4);
        nurse.addPatient(patient4);
        nurse.addPatient(patient5);
        staffRepository.save(doctor);
        staffRepository.save(surgeon);
        staffRepository.save(nurse);
        // then
        assertEquals(2,doctor.getPatientList().size());
        assertEquals(3,nurse.getPatientList().size());
        assertEquals(2,surgeon.getPatientList().size());

        //CleanUp
        staffRepository.deleteAll();
        patientRepository.deleteAll();
    }
}