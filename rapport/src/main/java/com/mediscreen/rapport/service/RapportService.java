package com.mediscreen.rapport.service;

import com.mediscreen.rapport.model.*;
import com.mediscreen.rapport.proxy.NoteProxy;
import com.mediscreen.rapport.proxy.PatientProxy;
import org.aspectj.weaver.ast.Not;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@Service
@PropertySource("declencheurs")
public class RapportService {

    private static final Logger LOGGER = LoggerFactory.getLogger(RapportService.class);
    private final NoteProxy noteProxy;
    private final PatientProxy patientProxy;
    private final String[] declencheurs;

    private final int THIRTEEN = 30;
    private final int TRIGGER_THREE = 2;
    private final int TRIGGER_FOUR = 4;
    private final int TRIGGER_FIVE = 5;
    private final int TRIGGER_SIX = 6;
    private final int TRIGGER_SEVEN = 7;


    public RapportService(NoteProxy noteProxy, PatientProxy patientProxy, @Value("${listDeclencheurs}") String[] declencheurs) {
        this.noteProxy = noteProxy;
        this.patientProxy = patientProxy;
        this.declencheurs = declencheurs;
    }

    private long getPatientAge (Patient patient) {
        return ChronoUnit.YEARS.between(patient.getDateOfBirth(), LocalDate.now());
    }

    public Status calculRisk (Patient patient, List<Note> notes) {
        long nbDeclencheurs = calculNbDeclencheurs(notes);
        long age = getPatientAge(patient);
        Status status = Status.None;

        // CONSTANT
        if( (age> 30 && nbDeclencheurs >= 8) ||
                (Sex.F.equals(patient.getSex()) && age <30 && nbDeclencheurs >= 7) ||
                (Sex.M.equals(patient.getSex()) && age <30 && nbDeclencheurs >= 5)
        ) {
            status = Status.EarlyOnset;
        } else if ( (age>30 && nbDeclencheurs>=6 ) ||
                (Sex.F.equals(patient.getSex()) && age <30 && nbDeclencheurs>=4 && nbDeclencheurs <= 6) ||
                (Sex.M.equals(patient.getSex()) && age <30 && nbDeclencheurs>=3 && nbDeclencheurs <= 4)
        ) {
            status = Status.InDanger;
        } else if ( age > 30 && nbDeclencheurs >= 2
        ) {
            status = Status.Borderline;
        }

        return status;
    }

    public long calculNbDeclencheurs (List<Note> notes) {

        String noteToStream = notes.stream()
                .map(Note::getNote)
                .map(String::trim)
                .collect(Collectors.joining());

        long nbDeclencheurs = Arrays.stream(declencheurs)
                .filter(noteToStream::contains)
                .distinct()
                .count();

        return nbDeclencheurs;

    }

    public Rapport getRapportByLastAndFirstName (String lastName, String firstName) {
        Patient patient = patientProxy.getPatientByLastAndFirstName(lastName, firstName);
        List<Note> notes = noteProxy.getNotesPatientByLastAndFirstName(lastName, firstName);
        Status status = calculRisk(patient, notes);
        Rapport rapport = new Rapport(patient.getLastName(), patient.getFirstName(), patient.getSex(), getPatientAge(patient), status);
        return rapport;
    }

    public Rapport getRapportById (int id) {
        Patient patient = patientProxy.getPatientById(id);
        List<Note> notes = noteProxy.getNotesPatientByLastAndFirstName(patient.getLastName(), patient.getFirstName());
        Status status = calculRisk(patient, notes);
        Rapport rapport = new Rapport(patient.getLastName(), patient.getFirstName(), patient.getSex(), getPatientAge(patient), status);
        return rapport;
    }



}
