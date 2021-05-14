package com.mediscreen.rapport.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class Note {

    private String id;
    private int patientId;
    private String note;
    private LocalDate dateNote;

    public Note(int patientId, String note) {
        this.patientId = patientId;
        this.note = note;
    }

}
