package com.clinic.staff;

import com.clinic.patient.PatientDto;
import com.clinic.patient.PatientService;
import com.clinic.person.enums.BaseProfession;
import com.clinic.person.enums.Sex;
import com.clinic.person.enums.Vocation;
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

import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.when;

@SpringJUnitWebConfig
@WebMvcTest(StaffController.class)
public class StaffControllerTestSuite {

    @Autowired
    private MockMvc mockMvc;
    @MockBean
    private StaffService staffService;

    @Test
    void shouldGetStaffById() throws Exception {
        // given
        StaffDto staffDto = new StaffDto(777,
                "Karol",
                "Karol",
                Sex.MALE,
                Vocation.STAFF,
                27,
                BaseProfession.DOCTOR,
                7,
                10,
                1,
                new ArrayList<>(),
                new ArrayList<>());
        when(staffService.getStaffById(anyLong())).thenReturn(staffDto);
        // when & then
        mockMvc.perform(MockMvcRequestBuilders
                .get("/staff/0")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.jsonPath("$.id", Matchers.is(777)))
                .andExpect(MockMvcResultMatchers.status().is2xxSuccessful());
    }
}
