package com.mediscreen.notes.controller;

import com.mediscreen.notes.model.Notes;
import com.mediscreen.notes.repository.NotesRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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


}
