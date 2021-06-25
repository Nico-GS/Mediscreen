package com.mediscreen.notes.service;

import com.mediscreen.notes.model.Notes;
import com.mediscreen.notes.repository.NotesRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class NotesServices {

    @Autowired
    public NotesRepository notesRepository;

    /**
     * Get note by ID
     * @param id the note ID
     * @return the note
     */
    public Optional<Notes> getNotesById(String id) {
        return notesRepository.findById(id);
    }

    /**
     * Create a note
     * @param notes the note to create
     */
    public void createNotes (Notes notes) {
        notesRepository.save(notes);
    }

    /**
     * Find a note by patient last & first name
     * @param patientLastName the patient lastname
     * @param patientFirstName the patient firstname
     * @return the note by patient last & first name
     */
    public List<Notes> findNotesByLastAndFirstName (String patientLastName, String patientFirstName) {
        return notesRepository.findByPatientLastNameAndPatientFirstName(patientLastName, patientFirstName);
    }

}
