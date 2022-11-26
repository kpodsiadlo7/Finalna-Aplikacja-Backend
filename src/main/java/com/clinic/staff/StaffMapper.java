package com.clinic.staff;

import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class StaffMapper {
    public Staff mapToStaff(final StaffDto staffDto){
        return new Staff(
                staffDto.getName(),
                staffDto.getSurname(),
                staffDto.getSex(),
                staffDto.getAge(),
                staffDto.getBaseProfession()
        );
    }
    public StaffDto mapToStaffDto(final Staff staff){
        return new StaffDto(
                staff.getId(),
                staff.getName(),
                staff.getSurname(),
                staff.getSex(),
                staff.getVocation(),
                staff.getAge(),
                staff.getBaseProfession(),
                staff.getQuantityPatientToHelp(),
                staff.getGrade(),
                staff.getPatientQuantity(),
                staff.getGradesList(),
                staff.getPatientList()
        );
    }
    public List<StaffDto> mapToStaffDtoList(final List<Staff> staffList){
        return staffList.stream()
                .map(this::mapToStaffDto)
                .collect(Collectors.toList());
    }
}
