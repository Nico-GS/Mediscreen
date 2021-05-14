package com.mediscreen.rapport.service;

import com.mediscreen.rapport.model.Note;
import com.mediscreen.rapport.model.Patient;
import com.mediscreen.rapport.model.Rapport;
import com.mediscreen.rapport.model.Status;
import com.mediscreen.rapport.proxy.NoteProxy;
import com.mediscreen.rapport.proxy.PatientProxy;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@PropertySource("declencheurs")
public class RapportService {

    private static final Logger LOGGER = LoggerFactory.getLogger(RapportService.class);
    private final NoteProxy noteProxy;
    private final PatientProxy patientProxy;
    private final String[] declencheurs;

    public RapportService(NoteProxy noteProxy, PatientProxy patientProxy, @Value("${listDeclencheurs}") String[] declencheurs) {
        this.noteProxy = noteProxy;
        this.patientProxy = patientProxy;
        this.declencheurs = declencheurs;
    }



}
