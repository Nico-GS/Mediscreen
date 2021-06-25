package com.mediscreen.patient.service;

import com.mediscreen.patient.model.Patient;
import com.mediscreen.patient.repository.PatientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import javax.validation.Valid;
import java.util.Optional;

@Service
public class PatientService {

    @Autowired
    public PatientRepository patientRepo;

    /**
     * Create a new patient
     * @param patient the patient to create
     */
    public void createPatient (@Valid Patient patient) {
        patientRepo.save(patient);
    }

    /**
     * Get a patient by ID
     * @param id the patient ID
     * @return the patient
     */
    public Optional<Patient> getById (int id) {
        return patientRepo.findById(id);
    }

    /**
     * Find all the patients
     * @return all patients
     */
    public Iterable<Patient> findAllPatient() {
        return patientRepo.findAll();
    }

    /**
     * Find a patient with last & first name
     * @param lastName the patient lastname
     * @param firstName the patient firstname
     * @return the patient
     */
    public Patient findPatientByLastAndFirstName (String lastName, String firstName) {
        return patientRepo.findByLastNameAndFirstName(lastName, firstName);
    }


}
