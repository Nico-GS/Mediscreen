package com.mediscreen.rapport.controller;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.anyInt;
import static org.mockito.Mockito.anyString;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import com.mediscreen.rapport.model.Rapport;
import com.mediscreen.rapport.model.Status;
import com.mediscreen.rapport.proxy.NoteProxy;
import com.mediscreen.rapport.service.RapportService;
import org.junit.jupiter.api.Test;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

public class RapportControllerTest {


    @Test
    public void testGetRapportByLastAndFirstName() {
        RapportService rapportService = mock(RapportService.class);
        when(rapportService.getRapportByLastAndFirstName(anyString(), anyString()))
                .thenReturn(new Rapport("Nicolas", "Gros", "M", 28, Status.None));
        ResponseEntity<Rapport> actualRapportByLastAndFirstName = (new RapportController(rapportService))
                .getRapportByLastAndFirstName("Nicolas", "Gros");
        assertTrue(actualRapportByLastAndFirstName.getHeaders().isEmpty());
        assertTrue(actualRapportByLastAndFirstName.hasBody());
        assertEquals(HttpStatus.OK, actualRapportByLastAndFirstName.getStatusCode());
        verify(rapportService).getRapportByLastAndFirstName(anyString(), anyString());
    }

    @Test
    public void testGetRapportById() {
        RapportService rapportService = mock(RapportService.class);
        when(rapportService.getRapportById(anyInt())).thenReturn(new Rapport("Nicolas", "Gros", "M", 28, Status.None));
        ResponseEntity<Rapport> actualRapportById = (new RapportController(rapportService)).getRapportById(1);
        assertTrue(actualRapportById.getHeaders().isEmpty());
        assertTrue(actualRapportById.hasBody());
        assertEquals(HttpStatus.OK, actualRapportById.getStatusCode());
        verify(rapportService).getRapportById(anyInt());
    }
}

