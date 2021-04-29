package com.mediscreen.notes.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

import java.time.LocalDate;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Document(collection = "notes")
public class Notes {

    @Id
    private String id;

    @Field(value = "patientId")
    private int patientId;

    @Field(value = "note")
    private String note;

    @Field(value = "dateNote")
    private LocalDate dateNote;

}
