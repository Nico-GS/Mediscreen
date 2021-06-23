package com.mediscreen.notes.model;

import com.fasterxml.jackson.annotation.JsonFormat;
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
//    @NotBlank(message = "Note can't be blank")
    private String note;

    @Field(value = "dateNote")
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss")
//    @DateTimeFormat(pattern = "YYYY-mm-dd hh:mm:ss")
    private LocalDateTime dateNote;

}
