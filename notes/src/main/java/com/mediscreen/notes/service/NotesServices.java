package com.mediscreen.notes.service;

import com.mediscreen.notes.model.Notes;
import com.mediscreen.notes.repository.NotesRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class NotesServices {

    @Autowired
    private NotesRepository notesRepository;

    public Optional<Notes> getNotesById(String id) {
        return notesRepository.findById(id);
    }

    public void createNotes (Notes notes) {
        notesRepository.save(notes);
    }

}
