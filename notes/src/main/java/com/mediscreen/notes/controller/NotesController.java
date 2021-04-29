package com.mediscreen.notes.controller;

import com.mediscreen.notes.exception.ResourceNotFoundException;
import com.mediscreen.notes.model.Notes;
import com.mediscreen.notes.repository.NotesRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin(origins = "http://localhost:3000")
@RequestMapping("/api/")
public class NotesController {

    private Logger LOGGER = LoggerFactory.getLogger(NotesController.class);

    @Autowired
    private NotesRepository notesRepository;

    @GetMapping("/notes")
    public List<Notes> getNotes() {
        LOGGER.info("GET Notes OK");
        return notesRepository.findAll();
    }

    @GetMapping("/notes/{id}")
    public ResponseEntity<Notes> getNotesById(@PathVariable String id) {
        LOGGER.info("GET Patient by ID OK : " + id);
        Notes notes = notesRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Notes don't exist with ID : " + id));
        return ResponseEntity.ok(notes);
    }


}
