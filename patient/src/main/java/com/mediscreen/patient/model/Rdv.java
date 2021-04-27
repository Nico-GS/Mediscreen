package com.mediscreen.patient.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
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
    private String namePatient;

    @Column(name = "date_rdv")
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate datePriseRdv;

    @Column(name = "notes_rdv")
    private String notesRdv;

}
