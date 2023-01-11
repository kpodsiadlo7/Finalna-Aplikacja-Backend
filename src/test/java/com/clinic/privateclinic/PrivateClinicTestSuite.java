package com.clinic.privateclinic;

import com.clinic.patient.Patient;
import com.clinic.patient.PatientRepository;
import com.clinic.person.Person;
import com.clinic.person.enums.BaseProfession;
import com.clinic.person.enums.Sex;
import com.clinic.grade.Grade;
import com.clinic.grade.GradeRepository;
import com.clinic.staff.Staff;
import com.clinic.staff.StaffRepository;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
class PrivateClinicTestSuite {

    @Autowired
    private PrivateClinicRepository privateClinicRepository;
    @Autowired
    private GradeRepository gradeRepository;
    @Autowired
    private StaffRepository staffRepository;
    @Autowired
    private PatientRepository patientRepository;

    @Test
    void shouldChangeAverageGradeClinic(){
        // given
        PrivateClinic privateClinic = new PrivateClinic("Klinika u zbycha","Gorzów","gorzowska");
        privateClinicRepository.save(privateClinic);
        // when
        Grade grade1 = new Grade("Eloszka","Spoko było", 5.0);
        Grade grade2 = new Grade("Eloszka","słabo było", 1.0);
        gradeRepository.save(grade1);
        gradeRepository.save(grade2);
        privateClinic.setGrades(grade1);
        privateClinic.setGrades(grade2);
        privateClinicRepository.save(privateClinic);
        // then
        assertEquals(3.0, privateClinic.getGrade());
        assertEquals(2,privateClinic.getGradesList().size());

        // CleanUp
        //gradeRepository.deleteAll();
        privateClinicRepository.deleteAll();
    }

    @Test
    void shouldFillStaffList(){
        // given
        PrivateClinic privateClinic = new PrivateClinic("Klinika u zbycha","Gorzów", "hawelańska");
        privateClinicRepository.save(privateClinic);
        // and
        Staff doctor = new Staff("doktorek","surname",Sex.MALE,33, BaseProfession.DOCTOR);
        Staff nurse = new Staff("nurse","surname",Sex.FEMALE,23,BaseProfession.NURSE);
        Staff surgeon = new Staff("surgeon","surname",Sex.MALE,43,BaseProfession.SURGEON);
        staffRepository.save(doctor);
        staffRepository.save(nurse);
        staffRepository.save(surgeon);
        // and
        privateClinic.addStaff(doctor);
        privateClinic.addStaff(nurse);
        privateClinic.addStaff(surgeon);
        privateClinicRepository.save(privateClinic);
        // when
        int staffQuantity = privateClinic.getStaffQuantity();
        int doctorQuantityInStaffListFromClinic = staffRepository.findByBaseProfession(BaseProfession.DOCTOR).size();
        String shouldNurse = privateClinic.getStaffList().stream()
                .filter(staff -> staff.getBaseProfession().equals(BaseProfession.NURSE)).map(Person::getName).findAny().orElse("list not contains nurse with name nurse");
        // then
        assertEquals(3, staffQuantity);
        assertEquals(1, doctorQuantityInStaffListFromClinic);
        assertEquals("nurse", shouldNurse);

        //CleanUp
        privateClinicRepository.deleteAll();
        staffRepository.deleteAll();
    }

    @Test
    @DisplayName("ShouldUpdateHospitalizedQuantityInClinicAfterAddingStaffWithPatients")
    void shouldUpdateHospitalizedQuantity(){
        // given
        PrivateClinic privateClinic = new PrivateClinic("Klinika nabyta","Poznań","poznańska");
        privateClinicRepository.save(privateClinic);
        // and
        Patient patient1 = new Patient("patient","surname", Sex.MALE,23,"brain damage");
        Patient patient2 = new Patient("patient2","surname",Sex.FEMALE, 33,"brain damage");
        Patient patient3 = new Patient("patient3","surname",Sex.MALE,23,"brain damage");
        Patient patient4 = new Patient("patient4","surname",Sex.FEMALE,33,"brain damage");
        Patient patient5 = new Patient("patient5","surname",Sex.FEMALE,33,"brain damage");
        patientRepository.save(patient1);
        patientRepository.save(patient2);
        patientRepository.save(patient3);
        patientRepository.save(patient4);
        patientRepository.save(patient5);
        // and
        Staff doctor = new Staff("doktorek","surname", Sex.MALE,33, BaseProfession.DOCTOR);
        Staff nurse = new Staff("nurse","surname",Sex.FEMALE,23,BaseProfession.NURSE);
        staffRepository.save(doctor);
        staffRepository.save(nurse);
    }
}