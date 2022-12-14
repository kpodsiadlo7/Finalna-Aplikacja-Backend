package com.clinic.privateclinic;

import com.clinic.grade.Grade;
import com.clinic.staff.Staff;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class PrivateClinicMapper {
    public PrivateClinic mapToPrivateClinic(final PrivateClinicDto privateClinicDto){
        return new PrivateClinic(
                privateClinicDto.getClinicName(),
                privateClinicDto.getCity(),
                privateClinicDto.getStreet()
        );
    }
    public PrivateClinicDto mapToPrivateClinicDto(final PrivateClinic privateClinic){
        return new PrivateClinicDto(
                privateClinic.getId(),
                privateClinic.getClinicName(),
                privateClinic.getCity(),
                privateClinic.getStreet(),
                privateClinic.getStaffQuantity(),
                privateClinic.getHospitalizedQuantity(),
                privateClinic.getGrade(),
                privateClinic.getGradesList(),
                privateClinic.getStaffList()
        );
    }
    public List<PrivateClinicDto> mapToPrivateClinicDtoList(final List<PrivateClinic> privateClinicList){
        return privateClinicList.stream()
                .map(this::mapToPrivateClinicDto)
                .collect(Collectors.toList());
    }
}
