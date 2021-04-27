package com.mediscreen.patient.controller;

import com.mediscreen.patient.exception.ResourceNotFoundException;
import com.mediscreen.patient.model.Rdv;
import com.mediscreen.patient.repository.RdvRepository;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin(origins = "http://localhost:3000")
@RequestMapping("/api/")
public class RdvController {

    private final Logger LOGGER = LogManager.getLogger(RdvController.class);

    @Autowired
    private RdvRepository rdvRepository;

    @GetMapping("/rdv")
    public List<Rdv> getRdv() {
        LOGGER.info("Get RDV Ok");
        return rdvRepository.findAll();
    }

    @GetMapping("/rdv/{id}")
    public ResponseEntity<Rdv> getRdvById(@PathVariable int id) {
        Rdv rdv = rdvRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Rdv with ID " + id + "don't exist"));
        LOGGER.info("Get RDV Ok : " + id);
        return ResponseEntity.ok(rdv);
    }

}
