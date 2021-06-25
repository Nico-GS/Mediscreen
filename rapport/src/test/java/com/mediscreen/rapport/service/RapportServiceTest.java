package com.mediscreen.rapport.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.anyInt;
import static org.mockito.Mockito.anyString;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import com.mediscreen.rapport.model.Note;
import com.mediscreen.rapport.model.Patient;
import com.mediscreen.rapport.model.Rapport;
import com.mediscreen.rapport.model.Status;
import com.mediscreen.rapport.proxy.NoteProxy;
import com.mediscreen.rapport.proxy.PatientProxy;

import java.time.LocalDate;

import java.util.ArrayList;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

@ContextConfiguration(classes = {RapportService.class})
@ExtendWith(SpringExtension.class)
public class RapportServiceTest {

    @MockBean
    private NoteProxy noteProxy;

    @MockBean
    private PatientProxy patientProxy;

    @Autowired
    private RapportService rapportService;


    @Test
    public void testGetPatientAge() {
        Patient patient = mock(Patient.class);
        when(patient.getDateOfBirth()).thenReturn(LocalDate.ofEpochDay(1L));
        assertEquals(51L, this.rapportService.getPatientAge(patient));
        verify(patient).getDateOfBirth();
    }

    @Test
    public void testCalculRisk() {
        Patient patient = mock(Patient.class);
        when(patient.getSex()).thenReturn("sex");
        when(patient.getDateOfBirth()).thenReturn(LocalDate.ofEpochDay(1L));
        assertEquals(Status.None, this.rapportService.calculRisk(patient, new ArrayList<Note>()));
        verify(patient).getDateOfBirth();
        verify(patient, times(4)).getSex();
    }

    @Test
    public void testCalculNbDeclencheurs() {
        Note note = mock(Note.class);
        when(note.getNote()).thenReturn("sex");

        ArrayList<Note> noteList = new ArrayList<Note>();
        noteList.add(note);
        noteList.add(new Note(1, "Note"));
        assertEquals(0L, this.rapportService.calculNbDeclencheurs(noteList));
        verify(note).getNote();
    }


    @Test
    public void testGetRapportByLastAndFirstName() {
        Patient patient = mock(Patient.class);
        when(patient.getFirstName()).thenReturn("Nicolas");
        when(patient.getLastName()).thenReturn("Gros");
        when(patient.getSex()).thenReturn("M");
        when(patient.getDateOfBirth()).thenReturn(LocalDate.ofEpochDay(1L));
        when(this.patientProxy.getPatientByLastAndFirstName(anyString(), anyString())).thenReturn(patient);
        Note note = mock(Note.class);
        when(note.getNote()).thenReturn("test");

        ArrayList<Note> noteList = new ArrayList<Note>();
        noteList.add(note);
        noteList.add(new Note(123, "Note"));
        when(this.noteProxy.getNotesPatientByLastAndFirstName(anyString(), anyString())).thenReturn(noteList);
        Rapport actualRapportByLastAndFirstName = this.rapportService.getRapportByLastAndFirstName("Doe", "Jane");
        assertEquals(51L, actualRapportByLastAndFirstName.getAge());
        assertEquals(Status.None, actualRapportByLastAndFirstName.getStatus());
        assertEquals("M", actualRapportByLastAndFirstName.getSex());
        assertEquals("Gros", actualRapportByLastAndFirstName.getLastName());
        assertEquals("Nicolas", actualRapportByLastAndFirstName.getFirstName());
        verify(note).getNote();
        verify(this.noteProxy).getNotesPatientByLastAndFirstName(anyString(), anyString());
        verify(patient, times(2)).getDateOfBirth();
        verify(patient, times(5)).getSex();
        verify(patient).getFirstName();
        verify(patient).getLastName();
        verify(this.patientProxy).getPatientByLastAndFirstName(anyString(), anyString());
    }

    @Test
    public void testGetRapportById() {
        Patient patient = new Patient(1, "Gros", "Nicolas", LocalDate.ofEpochDay(1L), "42 Main St", 1L, "M");
        patient.setFirstName("Nicolas");
        when(this.patientProxy.getPatientById(anyInt())).thenReturn(patient);
        when(this.noteProxy.getNotesPatientByLastAndFirstName(anyString(), anyString())).thenReturn(new ArrayList<Note>());
        Rapport actualRapportById = this.rapportService.getRapportById(1);
        assertEquals(51L, actualRapportById.getAge());
        assertEquals(Status.None, actualRapportById.getStatus());
        assertEquals("M", actualRapportById.getSex());
        assertEquals("Gros", actualRapportById.getLastName());
        assertEquals("Nicolas", actualRapportById.getFirstName());
        verify(this.noteProxy).getNotesPatientByLastAndFirstName(anyString(), anyString());
        verify(this.patientProxy).getPatientById(anyInt());
    }
}

