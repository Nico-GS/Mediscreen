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
        when(patient.getSex()).thenReturn("foo");
        when(patient.getDateOfBirth()).thenReturn(LocalDate.ofEpochDay(1L));
        assertEquals(Status.None, this.rapportService.calculRisk(patient, new ArrayList<Note>()));
        verify(patient).getDateOfBirth();
        verify(patient, times(4)).getSex();
    }

    @Test
    public void testCalculNbDeclencheurs() {
        Note note = mock(Note.class);
        when(note.getNote()).thenReturn("foo");

        ArrayList<Note> noteList = new ArrayList<Note>();
        noteList.add(note);
        noteList.add(new Note(123, "Note"));
        assertEquals(0L, this.rapportService.calculNbDeclencheurs(noteList));
        verify(note).getNote();
    }


    @Test
    public void testGetRapportByLastAndFirstName() {
        Patient patient = mock(Patient.class);
        when(patient.getFirstName()).thenReturn("foo");
        when(patient.getLastName()).thenReturn("foo");
        when(patient.getSex()).thenReturn("foo");
        when(patient.getDateOfBirth()).thenReturn(LocalDate.ofEpochDay(1L));
        when(this.patientProxy.getPatientByLastAndFirstName(anyString(), anyString())).thenReturn(patient);
        Note note = mock(Note.class);
        when(note.getNote()).thenReturn("foo");

        ArrayList<Note> noteList = new ArrayList<Note>();
        noteList.add(note);
        noteList.add(new Note(123, "Note"));
        when(this.noteProxy.getNotesPatientByLastAndFirstName(anyString(), anyString())).thenReturn(noteList);
        Rapport actualRapportByLastAndFirstName = this.rapportService.getRapportByLastAndFirstName("Doe", "Jane");
        assertEquals(51L, actualRapportByLastAndFirstName.getAge());
        assertEquals(Status.None, actualRapportByLastAndFirstName.getStatus());
        assertEquals("foo", actualRapportByLastAndFirstName.getSex());
        assertEquals("foo", actualRapportByLastAndFirstName.getLastName());
        assertEquals("foo", actualRapportByLastAndFirstName.getFirstName());
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
        Patient patient = new Patient(1, "Doe", "Jane", LocalDate.ofEpochDay(1L), "42 Main St", 1L, "Sex");
        patient.setFirstName("First Name");
        when(this.patientProxy.getPatientById(anyInt())).thenReturn(patient);
        when(this.noteProxy.getNotesPatientByLastAndFirstName(anyString(), anyString())).thenReturn(new ArrayList<Note>());
        Rapport actualRapportById = this.rapportService.getRapportById(1);
        assertEquals(51L, actualRapportById.getAge());
        assertEquals(Status.None, actualRapportById.getStatus());
        assertEquals("Sex", actualRapportById.getSex());
        assertEquals("Doe", actualRapportById.getLastName());
        assertEquals("First Name", actualRapportById.getFirstName());
        verify(this.noteProxy).getNotesPatientByLastAndFirstName(anyString(), anyString());
        verify(this.patientProxy).getPatientById(anyInt());
    }
}

