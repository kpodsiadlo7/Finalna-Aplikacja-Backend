package com.clinic.patient;

import com.clinic.person.enums.Sex;
import com.clinic.person.enums.Vocation;
import com.clinic.staff.StaffService;
import com.google.gson.Gson;
import org.hamcrest.Matchers;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit.jupiter.web.SpringJUnitWebConfig;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import java.util.ArrayList;
import java.util.List;

import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.when;

@SpringJUnitWebConfig
@WebMvcTest(PatientController.class)
public class PatientControllerTestSuite {

    @Autowired
    private MockMvc mockMvc;
    @MockBean
    private PatientService patientService;
    @MockBean
    private StaffService staffService;
    @MockBean
    private PatientMapper patientMapper;

    @Test
    void shouldFetchEmptyPatientList() throws Exception {
        // given
        when(patientMapper.mapToPatientDtoList(any())).thenReturn(List.of());
        // when & then
        mockMvc.perform(MockMvcRequestBuilders
                .get("/patient")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().is2xxSuccessful())
                .andExpect(MockMvcResultMatchers.jsonPath("$", Matchers.hasSize(0)));
    }

    @Test
    void shouldFetchPatientList() throws Exception {
        // given
        List<PatientDto> patientDtos = new ArrayList<>();
        patientDtos.add(new PatientDto(7,
                "imie",
                "nazwisko",
                Sex.MALE,
                Vocation.PATIENT,
                24,
                new ArrayList<>(),
                new ArrayList<>(),
                "Bo nogi mnie bolą"));
        when(patientService.getAllPatients()).thenReturn(patientDtos);
        // when & then
        mockMvc.perform(MockMvcRequestBuilders
                .get("/patient")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().is2xxSuccessful())
                .andExpect(MockMvcResultMatchers.jsonPath("$[0].name", Matchers.is("imie")))
                .andExpect(MockMvcResultMatchers.jsonPath("$", Matchers.hasSize(1)));
    }

    @Test
    void shouldGetPatientById() throws Exception {
        // given
        PatientDto patient = new PatientDto(7,
                "imie",
                "nazwisko",
                Sex.MALE,
                Vocation.PATIENT,
                24,
                new ArrayList<>(),
                new ArrayList<>(),
                "Bo nogi mnie bolą");
        when(patientService.getPatientDtoById(anyLong())).thenReturn(patient);
        // when & then
        mockMvc.perform(MockMvcRequestBuilders
                .get("/patient/7")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().is2xxSuccessful())
                .andExpect(MockMvcResultMatchers.jsonPath("$.id",Matchers.is(7)));
    }

    @Test
    void shouldCreateNewPatient() throws Exception {
        // given
        PatientDto patientDto = new PatientDto(7,"name","surname",Sex.MALE,Vocation.PATIENT,33,new ArrayList<>(),new ArrayList<>(),"Bo tak");
        when(patientService.createNewPatient(patientDto,0,"s")).thenReturn(patientDto);

        Gson gson = new Gson();
        String jsonContent = gson.toJson(patientDto);
        //when & then
        mockMvc.perform(MockMvcRequestBuilders
                .post("/patient")
                .contentType(MediaType.APPLICATION_JSON)
                .content(jsonContent)
                        .param("sex","0")
                        .param("reasonComingToClinic", "s")
                .characterEncoding("UTF-8"))
                .andExpect(MockMvcResultMatchers.status().is2xxSuccessful());
    }
}
