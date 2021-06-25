package com.mediscreen.rapport.proxy;

import com.mediscreen.rapport.model.Note;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@FeignClient(name = "ms-notes", url = "${notes.url.cross}")
public interface NoteProxy {

    /**
     * Request GET to ms-notes, find note by last and first name
     * @param lastName the patient last name
     * @param firstName the patient first name
     * @return the note
     */
    @GetMapping(value = "/api/notes/findByLastAndFirstName")
    List<Note> getNotesPatientByLastAndFirstName(@RequestParam("lastName") String lastName, @RequestParam("firstName") String firstName);

}
