package com.mediscreen.patient.service;

import com.mediscreen.patient.model.Patient;
import com.mediscreen.patient.model.Rdv;
import com.mediscreen.patient.repository.RdvRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class RdvService {

    @Autowired
    private RdvRepository rdvRepository;

    public void createRdv (Rdv rdv) {
        rdvRepository.save(rdv);
    }

    public Optional<Rdv> getById (int id) {
        return rdvRepository.findById(id);
    }

}
