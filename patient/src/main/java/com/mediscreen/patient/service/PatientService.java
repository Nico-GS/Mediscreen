package com.mediscreen.patient.service;

import com.mediscreen.patient.model.Patient;
import com.mediscreen.patient.repository.PatientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.validation.Valid;
import java.util.Optional;

@Service
public class PatientService {

    @Autowired
    private PatientRepository patientRepo;

    public void createPatient (@Valid Patient patient) {
        patientRepo.save(patient);
    }

    public Optional<Patient> getById (int id) {
        return patientRepo.findById(id);
    }

    public Iterable<Patient> findAllPatient() {
        return patientRepo.findAll();
    }

    

}
