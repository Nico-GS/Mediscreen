package com.mediscreen.notes.service;

import static org.junit.jupiter.api.Assertions.assertSame;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.any;
import static org.mockito.Mockito.anyString;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import com.mediscreen.notes.model.Notes;
import com.mediscreen.notes.repository.NotesRepository;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

@ContextConfiguration(classes = {NotesServices.class})
@ExtendWith(SpringExtension.class)
public class NotesServicesTest {
    @MockBean
    private NotesRepository notesRepository;

    @Autowired
    private NotesServices notesServices;

    @Test
    public void testGetNotesById() {
        Notes notes = new Notes();
        notes.setDateNote(LocalDateTime.of(1, 1, 1, 1, 1));
        notes.setId("42");
        notes.setPatientLastName("Doe");
        notes.setPatientId(123);
        notes.setPatientFirstName("Jane");
        notes.setNote("Note");
        Optional<Notes> ofResult = Optional.<Notes>of(notes);
        when(this.notesRepository.findById(anyString())).thenReturn(ofResult);
        Optional<Notes> actualNotesById = this.notesServices.getNotesById("42");
        assertSame(ofResult, actualNotesById);
        assertTrue(actualNotesById.isPresent());
        verify(this.notesRepository).findById(anyString());
    }

    @Test
    public void testCreateNotes() {
        Notes notes = new Notes();
        notes.setDateNote(LocalDateTime.of(1, 1, 1, 1, 1));
        notes.setId("42");
        notes.setPatientLastName("Doe");
        notes.setPatientId(123);
        notes.setPatientFirstName("Jane");
        notes.setNote("Note");
        when(this.notesRepository.save((Notes) any())).thenReturn(notes);
        this.notesServices.createNotes(new Notes());
        verify(this.notesRepository).save((Notes) any());
    }

    @Test
    public void testFindNotesByLastAndFirstName() {
        ArrayList<Notes> notesList = new ArrayList<Notes>();
        when(this.notesRepository.findByPatientLastNameAndPatientFirstName(anyString(), anyString())).thenReturn(notesList);
        List<Notes> actualFindNotesByLastAndFirstNameResult = this.notesServices.findNotesByLastAndFirstName("Doe", "Jane");
        assertSame(notesList, actualFindNotesByLastAndFirstNameResult);
        assertTrue(actualFindNotesByLastAndFirstNameResult.isEmpty());
        verify(this.notesRepository).findByPatientLastNameAndPatientFirstName(anyString(), anyString());
    }
}

