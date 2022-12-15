package com.clinic.grade;

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
@WebMvcTest(GradeController.class)
public class GradeControllerTestSuite {

    @Autowired
    private MockMvc mockMvc;
    @MockBean
    private GradeMapper gradeMapper;
    @MockBean
    private GradeService gradeService;

    @Test
    void shouldFetchEmptyGradeList() throws Exception {
        // given
        when(gradeMapper.mapToGradeDtoList(any())).thenReturn(List.of());
        // when & then
        mockMvc.perform(MockMvcRequestBuilders
                .get("/grade")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().is2xxSuccessful())
                .andExpect(MockMvcResultMatchers.jsonPath("$", Matchers.hasSize(0)));
    }

    @Test
    void shouldFetchGradesList() throws Exception {
        // given
        List<GradeDto> grades = new ArrayList<>();
        grades.add(new GradeDto(7, "nick","desc",9));

        when(gradeService.getAllGrades()).thenReturn(grades);
        // when & then
        mockMvc.perform(MockMvcRequestBuilders
                .get("/grade")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.jsonPath("$[0].id",Matchers.is(7)));
    }

    @Test
    void shouldGetGradeById() throws Exception {
        // given
        GradeDto gradeDto = new GradeDto(777,
                "lucky",
                "number",
                7);
        when(gradeService.getGradeById(anyLong())).thenReturn(gradeDto);
        // when & then
        mockMvc.perform(MockMvcRequestBuilders
                .get("/grade/7")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().is2xxSuccessful())
                .andExpect(MockMvcResultMatchers.jsonPath("$.nickname",Matchers.is("lucky")));
    }

    @Test
    void shouldCreateNewGrade() throws Exception {
        // given
        GradeDto gradeDto = new GradeDto(777,
                "lucky",
                "number",
                7);
        when(gradeService.createNewGrade(gradeDto)).thenReturn(gradeDto);

        Gson gson = new Gson();
        String jsonContent = gson.toJson(gradeDto);
        // when & then
        mockMvc.perform(MockMvcRequestBuilders
                .post("/grade")
                .contentType(MediaType.APPLICATION_JSON)
                .content(jsonContent)
                .characterEncoding("UTF-8"))
                .andExpect(MockMvcResultMatchers.status().is2xxSuccessful());
    }
}
