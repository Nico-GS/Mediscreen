package com.mediscreen.notes.controller;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertSame;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.any;
import static org.mockito.Mockito.anyString;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import com.mediscreen.notes.exception.ResourceNotFoundException;
import com.mediscreen.notes.model.Notes;
import com.mediscreen.notes.repository.NotesRepository;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

public class NotesControllerTest {

    @Test
    public void testGetNotes() {
        NotesRepository notesRepository = mock(NotesRepository.class);
        ArrayList<Notes> notesList = new ArrayList<Notes>();
        when(notesRepository.findAll()).thenReturn(notesList);
        NotesController notesController = new NotesController();
        notesController.notesRepository = notesRepository;
        List<Notes> actualNotes = notesController.getNotes();
        assertSame(notesList, actualNotes);
        assertTrue(actualNotes.isEmpty());
        verify(notesRepository).findAll();
    }

    @Test
    public void testGetNotesById() {
        Notes notes = new Notes();
        notes.setDateNote(LocalDateTime.of(1, 1, 1, 1, 1));
        notes.setId("42");
        notes.setPatientLastName("Doe");
        notes.setPatientId(123);
        notes.setPatientFirstName("Jane");
        notes.setNote("Note");
        NotesRepository notesRepository = mock(NotesRepository.class);
        when(notesRepository.findById(anyString())).thenReturn(Optional.<Notes>of(notes));
        NotesController notesController = new NotesController();
        notesController.notesRepository = notesRepository;
        ResponseEntity<Notes> actualNotesById = notesController.getNotesById("42");
        assertTrue(actualNotesById.getHeaders().isEmpty());
        assertTrue(actualNotesById.hasBody());
        assertEquals(HttpStatus.OK, actualNotesById.getStatusCode());
        verify(notesRepository).findById(anyString());
        assertTrue(notesController.getNotes().isEmpty());
    }

    @Test
    public void testGetNotesById2() {
        NotesRepository notesRepository = mock(NotesRepository.class);
        when(notesRepository.findById(anyString())).thenReturn(Optional.<Notes>empty());
        NotesController notesController = new NotesController();
        notesController.notesRepository = notesRepository;
        assertThrows(ResourceNotFoundException.class, () -> notesController.getNotesById("42"));
        verify(notesRepository).findById(anyString());
    }

    @Test
    public void testAddNotes() {
        NotesController notesController = new NotesController();
        Notes notes = mock(Notes.class);
        when(notes.getId()).thenThrow(new ResourceNotFoundException("An error occurred"));
        assertThrows(ResourceNotFoundException.class, () -> notesController.addNotes(notes));
        verify(notes).getId();
    }

    @Test
    public void testAddNotes2() {
        NotesController notesController = new NotesController();
        Notes notes = mock(Notes.class);
        when(notes.getId()).thenThrow(new ResourceNotFoundException("An error occurred"));
        assertThrows(ResourceNotFoundException.class, () -> notesController.addNotes(notes));
        verify(notes).getId();
    }

    @Test
    public void testUpdateNotes() {
        Notes notes = new Notes();
        notes.setDateNote(LocalDateTime.of(1, 1, 1, 1, 1));
        notes.setId("42");
        notes.setPatientLastName("Doe");
        notes.setPatientId(123);
        notes.setPatientFirstName("Jane");
        notes.setNote("Note");
        Optional<Notes> ofResult = Optional.<Notes>of(notes);

        Notes notes1 = new Notes();
        notes1.setDateNote(LocalDateTime.of(1, 1, 1, 1, 1));
        notes1.setId("42");
        notes1.setPatientLastName("Doe");
        notes1.setPatientId(123);
        notes1.setPatientFirstName("Jane");
        notes1.setNote("Note");
        NotesRepository notesRepository = mock(NotesRepository.class);
        when(notesRepository.save((Notes) any())).thenReturn(notes1);
        when(notesRepository.findById(anyString())).thenReturn(ofResult);
        NotesController notesController = new NotesController();
        notesController.notesRepository = notesRepository;
        ResponseEntity<Notes> actualUpdateNotesResult = notesController.updateNotes("42", new Notes());
        assertTrue(actualUpdateNotesResult.getHeaders().isEmpty());
        assertTrue(actualUpdateNotesResult.hasBody());
        assertEquals(HttpStatus.OK, actualUpdateNotesResult.getStatusCode());
        verify(notesRepository).findById(anyString());
        verify(notesRepository).save((Notes) any());
    }

    @Test
    public void testUpdateNotes2() {
        Notes notes = new Notes();
        notes.setDateNote(LocalDateTime.of(1, 1, 1, 1, 1));
        notes.setId("42");
        notes.setPatientLastName("Doe");
        notes.setPatientId(123);
        notes.setPatientFirstName("Jane");
        notes.setNote("Note");
        NotesRepository notesRepository = mock(NotesRepository.class);
        when(notesRepository.save((Notes) any())).thenReturn(notes);
        when(notesRepository.findById(anyString())).thenReturn(Optional.<Notes>empty());
        NotesController notesController = new NotesController();
        notesController.notesRepository = notesRepository;
        assertThrows(ResourceNotFoundException.class, () -> notesController.updateNotes("42", new Notes()));
        verify(notesRepository).findById(anyString());
    }

    @Test
    public void testDeleteNotes() {
        Notes notes = new Notes();
        notes.setDateNote(LocalDateTime.of(1, 1, 1, 1, 1));
        notes.setId("42");
        notes.setPatientLastName("Doe");
        notes.setPatientId(123);
        notes.setPatientFirstName("Jane");
        notes.setNote("Note");
        Optional<Notes> ofResult = Optional.<Notes>of(notes);
        NotesRepository notesRepository = mock(NotesRepository.class);
        doNothing().when(notesRepository).delete((Notes) any());
        when(notesRepository.findById(anyString())).thenReturn(ofResult);
        NotesController notesController = new NotesController();
        notesController.notesRepository = notesRepository;
        ResponseEntity<Map<String, Boolean>> actualDeleteNotesResult = notesController.deleteNotes("42");
        assertEquals(1, actualDeleteNotesResult.getBody().size());
        assertEquals("<200 OK OK,{Deleted=true},[]>", actualDeleteNotesResult.toString());
        assertTrue(actualDeleteNotesResult.hasBody());
        assertEquals(HttpStatus.OK, actualDeleteNotesResult.getStatusCode());
        assertTrue(actualDeleteNotesResult.getHeaders().isEmpty());
        verify(notesRepository).delete((Notes) any());
        verify(notesRepository).findById(anyString());
        assertTrue(notesController.getNotes().isEmpty());
    }

    @Test
    public void testDeleteNotes2() {
        NotesRepository notesRepository = mock(NotesRepository.class);
        doNothing().when(notesRepository).delete((Notes) any());
        when(notesRepository.findById(anyString())).thenReturn(Optional.<Notes>empty());
        NotesController notesController = new NotesController();
        notesController.notesRepository = notesRepository;
        assertThrows(ResourceNotFoundException.class, () -> notesController.deleteNotes("42"));
        verify(notesRepository).findById(anyString());
    }
}

