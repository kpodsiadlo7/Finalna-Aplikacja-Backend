package com.clinic.staff;

import com.clinic.grade.GradeService;
import com.clinic.patient.PatientNotFoundException;
import com.clinic.patient.PatientService;
import com.clinic.patient.disease.DiseaseStoryDto;
import com.clinic.person.enums.BaseProfession;
import com.clinic.person.enums.Sex;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class StaffService {
    private final StaffRepository staffRepository;
    private final StaffMapper staffMapper;
    private final PatientService patientService;
    private final GradeService gradeService;

    StaffService(final StaffRepository staffRepository, final StaffMapper staffMapper, final PatientService patientService, final GradeService gradeService) {
        this.staffRepository = staffRepository;
        this.staffMapper = staffMapper;
        this.patientService = patientService;
        this.gradeService = gradeService;
    }

    public DiseaseStoryDto addDescriptionDiseaseToDiseaseStoryByPatientId(final DiseaseStoryDto diseaseStoryDto, final long patientId)
            throws PatientNotFoundException {
        return patientService.addDescriptionDiseaseToDiseaseStory(diseaseStoryDto,patientId);
    }

    public List<StaffDto> getAllStaff() {
        return staffMapper.mapToStaffDtoList(staffRepository.findAll());
    }

    public StaffDto getStaffById(final long staffId) throws StaffNotFoundException {
        if (!staffRepository.existsById(staffId))
            throw new StaffNotFoundException();
        return staffMapper.mapToStaffDto(staffRepository.findById(staffId));
    }

    public StaffDto createNewStaff(final StaffDto staffDto, final int profession, final int sex) {
        if (profession >= 0 && profession <=3) {
            staffDto.setBaseProfession(BaseProfession.SURGEON);
            if (profession == 1)
                staffDto.setBaseProfession(BaseProfession.NURSE);
            else if (profession == 2)
                staffDto.setBaseProfession(BaseProfession.SECRETARY);
            else if (profession == 3)
                staffDto.setBaseProfession(BaseProfession.DOCTOR);
        }
        else throw new IllegalStateException("BASED PROFESSION NOT FOUND. STATE 0 TO 3");

        if (sex >= 0 && sex <= 1) {
            staffDto.setSex(Sex.MALE);
            if (sex == 1)
                staffDto.setSex(Sex.FEMALE);
        }
        else throw new IllegalStateException("SEX NOT FOUND. STATE 0 TO 1");

        Staff staff = staffMapper.mapToStaff(staffDto);
        return staffMapper.mapToStaffDto(staffRepository.save(staff));
    }

    public StaffDto rateStaff(final double rate, final String desc, final long staffId, String patientName) throws StaffNotFoundException {
        if (!staffRepository.existsById(staffId))
            throw new StaffNotFoundException();
        Staff staff = staffRepository.findById(staffId);
        staff.addGradeAndCalculateAverageGrade(gradeService.newRate(desc,rate,patientName));
        return staffMapper.mapToStaffDto(staffRepository.save(staff));
    }
}
