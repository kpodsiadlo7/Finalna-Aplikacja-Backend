package com.clinic.reservation;

import com.clinic.patient.PatientDto;
import com.clinic.person.enums.Sex;
import com.clinic.person.enums.Vocation;
import com.clinic.reservation.enums.Currency;
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

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.when;

@SpringJUnitWebConfig
@WebMvcTest(ReservationController.class)
public class ReservationControllerTestSuite {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private ReservationMapper reservationMapper;
    @MockBean
    private ReservationService reservationService;

    @Test
    void shouldFetchEmptyReservationList() throws Exception {
        // given
        when(reservationMapper.mapToReservationDtoList(any())).thenReturn(List.of());
        // when & then
        mockMvc.perform(MockMvcRequestBuilders
                .get("/reservation")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().is2xxSuccessful())
                .andExpect(MockMvcResultMatchers.jsonPath("$", Matchers.hasSize(0)));
    }

    @Test
    void shouldFetchReservationList() throws Exception {
        // given
        List<ReservationDto> reservations = List.of(new ReservationDto(7,
                14,
                Currency.USD,
                false,
                LocalDate.now().plusDays(15)));
        when(reservationService.getAllReservations()).thenReturn(reservations);
        // when & then
        mockMvc.perform(MockMvcRequestBuilders
                .get("/reservation")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.jsonPath("$[0].afterVisit", Matchers.is(false)));
    }
    @Test
    void shouldCreateNewReservation() throws Exception {
        // given
        ReservationDto reservationsDto = new ReservationDto(7,
                14,
                Currency.USD,
                false,
                LocalDate.now().plusDays(15));
        PatientDto patientDto = new PatientDto(7,"name","surname", Sex.MALE, Vocation.PATIENT,33,new ArrayList<>(),new ArrayList<>(),"Bo tak");
        when(reservationMapper.mapToReservationDto(new Reservation())).thenReturn(reservationsDto);

        Gson gson = new Gson();
        String jsonContent = gson.toJson(patientDto);
        // when & then
        mockMvc.perform(MockMvcRequestBuilders
                .post("/reservation")
                .content(jsonContent)
                .contentType(MediaType.APPLICATION_JSON)
                .param("clinicId","0")
                .param("sex","0")
                .param("reasonComingToClinic","reason")
                .param("visitDate","2022-12-25")
                .param("currency","0")
                .characterEncoding("UTF-8"))
                .andExpect(MockMvcResultMatchers.status().is2xxSuccessful());
    }
}
