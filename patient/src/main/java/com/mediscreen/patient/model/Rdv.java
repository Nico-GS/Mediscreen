package com.mediscreen.patient.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Date;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "rendez_vous")
public class Rdv {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_rdv")
    private int idRdv;

    @Column(name = "name_patient")
    @NotBlank(message = "Name of patient can't be blank")
    @NotNull
    private String namePatient;

    @Column(name = "date_rdv")
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    @NotNull(message = "Please add a date")
    private LocalDate datePriseRdv;

    @Column(name = "notes_rdv")
    @NotBlank(message = "LastName can't be blank")
    @NotNull
    private String notesRdv;

}
