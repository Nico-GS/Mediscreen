package com.mediscreen.notes.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;
import org.springframework.format.annotation.DateTimeFormat;

import javax.validation.constraints.NotBlank;
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

    @Field(value = "lastName")
    private String patientLastName;

    @Field(value = "firstName")
    private String patientFirstName;

    @Field(value = "note")
    @NotBlank(message = "Note can't be blank")
    private String note;

    // heure
    @Field(value = "dateNote")
    @DateTimeFormat(pattern = "YYYY-mm-dd")
    private LocalDate dateNote;

}
