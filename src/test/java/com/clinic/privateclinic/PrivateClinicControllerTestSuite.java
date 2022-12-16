package com.clinic.privateclinic;

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

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@SpringJUnitWebConfig
@WebMvcTest(PrivateClinicController.class)
public class PrivateClinicControllerTestSuite {

    @Autowired
    private MockMvc mockMvc;
    @MockBean
    private PrivateClinicMapper privateClinicMapper;
    @MockBean
    private PrivateClinicService privateClinicService;

    @Test
    void shouldFetchEmptyPrivateClinicList() throws Exception {
        // given
        when(privateClinicMapper.mapToPrivateClinicDtoList(any())).thenReturn(List.of());
        // when & then
        mockMvc.perform(MockMvcRequestBuilders
                        .get("/clinic")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().is2xxSuccessful())
                .andExpect(MockMvcResultMatchers.jsonPath("$", Matchers.hasSize(0)));
    }

    @Test
    void shouldFetchPrivateClinicList() throws Exception {
        // given
        List<PrivateClinicDto> privateClinicDtoList = new ArrayList<>();
        privateClinicDtoList.add(new PrivateClinicDto(7,
                "Klinika u zbycha",
                "Poznań",
                "Albańska",
                777,
                777,
                10,
                new ArrayList<>(),
                new ArrayList<>()));
        when(privateClinicService.getAllClinics()).thenReturn(privateClinicDtoList);
        //when & then
        mockMvc.perform(MockMvcRequestBuilders
                .get("/clinic")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().is2xxSuccessful())
                .andExpect(MockMvcResultMatchers.jsonPath("$[0].staffQuantity", Matchers.is(777)));
    }
}
