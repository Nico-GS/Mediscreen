package com.mediscreen.rapport.controller;

import static org.mockito.Mockito.anyInt;
import static org.mockito.Mockito.anyString;
import static org.mockito.Mockito.when;

import com.mediscreen.rapport.model.Rapport;
import com.mediscreen.rapport.model.Status;
import com.mediscreen.rapport.service.RapportService;
import org.hamcrest.Matchers;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.PropertySource;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

@ContextConfiguration(classes = {RapportController.class})
@ExtendWith(SpringExtension.class)
public class RapportControllerTest {
    @Autowired
    private RapportController rapportController;

    @MockBean
    private RapportService rapportService;

    @Test
    public void testGetRapportByLastAndFirstName() throws Exception {
        when(this.rapportService.getRapportByLastAndFirstName(anyString(), anyString()))
                .thenReturn(new Rapport("Doe", "Jane", "Sex", 1L, Status.None));
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.get("/api/reports/lastAndFirstName")
                .param("firstName", "foo")
                .param("lastName", "foo");
        MockMvcBuilders.standaloneSetup(this.rapportController)
                .build()
                .perform(requestBuilder)
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType("application/json"))
                .andExpect(MockMvcResultMatchers.content()
                        .string(Matchers.containsString(
                                "{\"lastName\":\"Doe\",\"firstName\":\"Jane\",\"sex\":\"Sex\",\"age\":1,\"status\":\"None\"}")));
    }

    @Test
    public void testGetRapportById() throws Exception {
        when(this.rapportService.getRapportById(anyInt())).thenReturn(new Rapport("Doe", "Jane", "Sex", 1L, Status.None));
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.get("/api/reports/{id}", 1);
        MockMvcBuilders.standaloneSetup(this.rapportController)
                .build()
                .perform(requestBuilder)
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType("application/json"))
                .andExpect(MockMvcResultMatchers.content()
                        .string(Matchers.containsString(
                                "{\"lastName\":\"Doe\",\"firstName\":\"Jane\",\"sex\":\"Sex\",\"age\":1,\"status\":\"None\"}")));
    }
}

