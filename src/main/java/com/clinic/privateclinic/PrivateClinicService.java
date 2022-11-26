package com.clinic.privateclinic;

import com.clinic.grade.GradeService;
import com.clinic.patient.PatientNotFoundException;
import com.clinic.patient.PatientRepository;
import com.clinic.staff.StaffDto;
import com.clinic.staff.StaffMapper;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PrivateClinicService {
    private final PrivateClinicRepository privateClinicRepository;
    private final PrivateClinicMapper privateClinicMapper;
    private final GradeService gradeService;
    private final StaffMapper staffMapper;
    private final PatientRepository patientRepository;

    PrivateClinicService(final PrivateClinicRepository privateClinicRepository, final PrivateClinicMapper privateClinicMapper, final GradeService gradeService, final StaffMapper staffMapper, final PatientRepository patientRepository) {
        this.privateClinicRepository = privateClinicRepository;
        this.privateClinicMapper = privateClinicMapper;
        this.gradeService = gradeService;
        this.staffMapper = staffMapper;
        this.patientRepository = patientRepository;
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

}
