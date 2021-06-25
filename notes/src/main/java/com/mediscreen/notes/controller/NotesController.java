package com.mediscreen.notes.controller;

import com.mediscreen.notes.exception.ResourceNotFoundException;
import com.mediscreen.notes.model.Notes;
import com.mediscreen.notes.repository.NotesRepository;
import com.mediscreen.notes.service.NotesServices;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/")
@CrossOrigin(origins = {"${mediscreen.front.cross.origin}", "${rapport.cross}"})
public class NotesController {


    private final Logger LOGGER = LoggerFactory.getLogger(NotesController.class);

    @Autowired
    public NotesRepository notesRepository;

    @Autowired
    public NotesServices notesServices;

    /**
     * Get all notes
     * @return notes
     */
    @GetMapping("/notes")
    public List<Notes> getNotes() {
        LOGGER.info("GET Notes OK");
        return notesRepository.findAll();
    }

    /**
     * Return a note by ID
     * @param id the note ID
     * @return the note
     */
    @GetMapping("/notes/{id}")
    public ResponseEntity<Notes> getNotesById(@PathVariable String id) {
        LOGGER.info("GET Notes by ID OK : " + id);
        Notes notes = notesRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Notes don't exist with ID : " + id));
        return ResponseEntity.ok(notes);
    }

    /**
     * Retrieve a note by patient last and first name
     * @param patientLastName the patient lastname
     * @param patientFirstName the patient firstname
     * @return the note by first & last name
     */
    @GetMapping("/notes/findByLastAndFirstName")
    public ResponseEntity getNotesByFirstAndLastName(@RequestParam("lastName") String patientLastName, @RequestParam("firstName") String patientFirstName) {
        LOGGER.info("GET Notes patient by first and last name OK : {} - {} : ", patientLastName, patientFirstName);
        List<Notes> notes = notesServices.findNotesByLastAndFirstName(patientLastName, patientFirstName);
        return ResponseEntity.ok(notes);
    }

    /**
     * Create a note
     * @param notes the note to create
     * @return bad request if note already exist with ID | 200 OK if note created
     */
    @PostMapping("/notes")
    public ResponseEntity addNotes(@RequestBody Notes notes) {
        if (notesServices.getNotesById(notes.getId()).isPresent()) {
            LOGGER.info("Error created Notes with ID : " + notes.getId());
            return ResponseEntity.badRequest().build();
        }
        notesServices.createNotes(notes);
        LOGGER.info("Create Notes OK");
        return ResponseEntity.ok(HttpStatus.OK);
    }

    /**
     * Update a note
     * @param id the note to update
     * @param notes the note to update
     * @return 200 OK if note update success | Bad Request if note not found with ID
     */
    @PutMapping(value = "/notes/{id}")
    public ResponseEntity<Notes> updateNotes(@PathVariable String id, @RequestBody Notes notes) {
        Notes note = notesRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Note not found with ID :" + id));
        note.setNote(notes.getNote());
        note.setDateNote(notes.getDateNote());
        note.setPatientId(notes.getPatientId());
        Notes updateNotes = notesRepository.save(note);
        LOGGER.info("Update Notes OK : " + id);
        return ResponseEntity.ok(updateNotes);
    }

    /**
     * Delete a note by ID
     * @param id the note ID
     * @return 200 OK if note deleted | Bad Request if note don't exist with ID
     */
    @DeleteMapping("/notes/{id}")
    public ResponseEntity<Map<String, Boolean>> deleteNotes(@PathVariable("id") String id) {
        Notes note = notesRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Notes not found with ID : " + id));
        notesRepository.delete(note);
        Map<String, Boolean> response = new HashMap<>();
        response.put("Deleted", Boolean.TRUE);
        LOGGER.info("Delete Note OK : " + id);
        return ResponseEntity.ok(response);
    }


}
