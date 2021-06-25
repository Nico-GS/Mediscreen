package com.mediscreen.notes.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

import java.time.LocalDateTime;

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

    @Field(value = "lastName")
    private String patientLastName;

    @Field(value = "firstName")
    private String patientFirstName;

    @Field(value = "note")
    private String note;

    @Field(value = "dateNote")
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime dateNote;

}
