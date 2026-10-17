package com.samosirverse.controller;

import com.samosirverse.entity.Destination;
import com.samosirverse.service.DestinationService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;

import java.math.BigDecimal;
import java.util.List;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
@ActiveProfiles("test")
class DestinationControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private DestinationService destinationService;

    @Test
    void shouldReturnPublicDestinationsList() throws Exception {
        Destination dest = new Destination();
        dest.setId(1L);
        dest.setName("Huta Siallagan");
        dest.setSlug("huta-siallagan");
        dest.setCategory("BUDAYA_SEJARAH");
        dest.setSubDistrict("Simanindo");
        dest.setTicketPrice(new BigDecimal("10000"));

        when(destinationService.getAll(any(), any(), any())).thenReturn(List.of(dest));

        mockMvc.perform(get("/api/destinations")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.success").value(true))
                .andExpect(jsonPath("$.data[0].name").value("Huta Siallagan"));
    }
}
