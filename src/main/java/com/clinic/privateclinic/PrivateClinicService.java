package com.clinic.privateclinic;

import com.clinic.grade.GradeService;
import com.clinic.patient.PatientNotFoundException;
import com.clinic.patient.PatientRepository;
import com.clinic.staff.*;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PrivateClinicService {
    private final PrivateClinicRepository privateClinicRepository;
    private final PrivateClinicMapper privateClinicMapper;
    private final GradeService gradeService;
    private final StaffMapper staffMapper;
    private final PatientRepository patientRepository;
    private final StaffRepository staffRepository;

    PrivateClinicService(final PrivateClinicRepository privateClinicRepository, final PrivateClinicMapper privateClinicMapper, final GradeService gradeService, final StaffMapper staffMapper, final PatientRepository patientRepository, final StaffRepository staffRepository) {
        this.privateClinicRepository = privateClinicRepository;
        this.privateClinicMapper = privateClinicMapper;
        this.gradeService = gradeService;
        this.staffMapper = staffMapper;
        this.patientRepository = patientRepository;
        this.staffRepository = staffRepository;
    }

    public List<PrivateClinicDto> getAllClinics() {
        return privateClinicMapper.mapToPrivateClinicDtoList(privateClinicRepository.findAll());
    }

    public PrivateClinicDto getClinicById(final long clinicId) throws PrivateClinicNotFoundException {
        if (!privateClinicRepository.existsById(clinicId))
            throw new PrivateClinicNotFoundException();
        return privateClinicMapper.mapToPrivateClinicDto(privateClinicRepository.findById(clinicId));
    }

    public List<StaffDto> getAllStaffByClinicId(final long clinicId) throws PrivateClinicNotFoundException {
        if (!privateClinicRepository.existsById(clinicId))
            throw new PrivateClinicNotFoundException();
        PrivateClinic clinicStaff = privateClinicRepository.findById(clinicId);
        return staffMapper.mapToStaffDtoList(clinicStaff.getStaffList());
    }

    public PrivateClinicDto createNewClinic(final PrivateClinicDto privateClinicDto) {
        PrivateClinic newClinic = privateClinicMapper.mapToPrivateClinic(privateClinicDto);
        return privateClinicMapper.mapToPrivateClinicDto(privateClinicRepository.save(newClinic));
    }

    public PrivateClinicDto changeClinicName(final String clinicName, final long clinicId) throws PrivateClinicNotFoundException {
        if (!privateClinicRepository.existsById(clinicId))
            throw new PrivateClinicNotFoundException();
        PrivateClinic privateClinicToUpdateName = privateClinicRepository.findById(clinicId);
        privateClinicToUpdateName.setClinicName(clinicName);
        return privateClinicMapper.mapToPrivateClinicDto(privateClinicRepository.save(privateClinicToUpdateName));
    }

    public PrivateClinicDto rateClinic(final String desc, double rate, long clinicId, String patientName) throws PrivateClinicNotFoundException {
        if (!privateClinicRepository.existsById(clinicId))
            throw new PrivateClinicNotFoundException();
        PrivateClinic privateClinic = privateClinicRepository.findById(clinicId);
        privateClinic.setGrades(gradeService.newRate(desc,rate,patientName));
        return privateClinicMapper.mapToPrivateClinicDto(privateClinicRepository.save(privateClinic));
    }

    public PrivateClinicDto registerNewPatient(final long clinicId, final long patientId) throws PatientNotFoundException, PrivateClinicNotFoundException {
        if (!privateClinicRepository.existsById(clinicId))
            throw new PrivateClinicNotFoundException();
        if (!patientRepository.existsById(patientId))
            throw new PatientNotFoundException();
        PrivateClinic privateClinic = privateClinicRepository.findById(clinicId);
        privateClinic.registerPatient(patientRepository.findById(patientId));
        return privateClinicMapper.mapToPrivateClinicDto(privateClinicRepository.save(privateClinic));
    }

    public void addStaffToClinic(final long staffId, final long clinicId) throws PrivateClinicNotFoundException, StaffNotFoundException {
        if (!privateClinicRepository.existsById(clinicId))
            throw new PrivateClinicNotFoundException();
        if (!staffRepository.existsById(staffId))
            throw new StaffNotFoundException();
        PrivateClinic privateClinic = privateClinicRepository.findById(clinicId);
        Staff staff = staffRepository.findById(staffId);
        privateClinic.addStaff(staff);
        privateClinicRepository.save(privateClinic);
    }

    public boolean removeStaffFromClinic(final long staffId, final long clinicId) throws StaffNotFoundException, PrivateClinicNotFoundException {
        if (!privateClinicRepository.existsById(clinicId))
            throw new PrivateClinicNotFoundException();
        if (!staffRepository.existsById(staffId))
            throw new StaffNotFoundException();
        Staff staffToRemove = staffRepository.findById(staffId);
        PrivateClinic privateClinic = privateClinicRepository.findById(clinicId);
        if (!privateClinic.removeStaff(staffToRemove))
            throw new IllegalStateException("This " +staffToRemove.getBaseProfession() +" named "
                    + staffToRemove.getName()
                    +" is not working for this clinic!");
        privateClinicRepository.save(privateClinic);
        return true;
    }
}
