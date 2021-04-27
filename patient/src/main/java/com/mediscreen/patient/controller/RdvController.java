package com.mediscreen.patient.controller;

import com.mediscreen.patient.exception.ResourceNotFoundException;
import com.mediscreen.patient.model.Patient;
import com.mediscreen.patient.model.Rdv;
import com.mediscreen.patient.repository.RdvRepository;
import com.mediscreen.patient.service.RdvService;
import io.swagger.annotations.ApiOperation;
import org.apache.logging.log4j.LogManager;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@CrossOrigin(origins = "http://localhost:3000")
@RequestMapping("/api/")
public class RdvController {

    private final Logger LOGGER = LoggerFactory.getLogger(RdvController.class);

    @Autowired
    private RdvRepository rdvRepository;

    @Autowired
    private RdvService rdvService;

    @ApiOperation(value = "Get all Rdv")
    @GetMapping("/rdv")
    public List<Rdv> getRdv() {
        LOGGER.info("Get RDV Ok");
        return rdvRepository.findAll();
    }

    @ApiOperation(value = "Get RDV by ID")
    @GetMapping("/rdv/{id}")
    public ResponseEntity<Rdv> getRdvById(@PathVariable int id) {
        Rdv rdv = rdvRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Rdv with ID " + id + "don't exist"));
        LOGGER.info("Get RDV Ok : " + id);
        return ResponseEntity.ok(rdv);
    }

    @ApiOperation(value = "Add a new RDV with name of the patient, the date & notes")
    @PostMapping("/rdv")
    public ResponseEntity<Rdv> addRdv(@RequestBody @Valid @Validated Rdv rdv) {
        if (rdvService.getById(rdv.getIdRdv()).isPresent()) {
            LOGGER.info("Error created RDV with ID : " + rdv.getIdRdv());
            return ResponseEntity.badRequest().build();
        }
        rdvService.createRdv(rdv);
        LOGGER.info("Create RDV OK : " + rdv.getIdRdv() + " ("+ rdv.getNamePatient()+")");
        return ResponseEntity.ok(rdv);
    }

    @ApiOperation(value = "Update a RDV")
    @PutMapping("/rdv/{id}")
    public ResponseEntity<Rdv> updateRdv (@RequestBody @Valid @Validated Rdv rdv, @PathVariable int id) {
        Rdv rdv1 = rdvRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("RDV not found with ID :" + id));
        rdv1.setNotesRdv(rdv.getNotesRdv());
        rdv1.setDatePriseRdv(rdv.getDatePriseRdv());
        rdv1.setNamePatient(rdv.getNamePatient());
        Rdv updateRdv = rdvRepository.save(rdv1);
        LOGGER.info("Update RDV OK : " + id);
        return ResponseEntity.ok(updateRdv);
    }

    @ApiOperation(value = "Delete RDV by ID")
    @DeleteMapping("/rdv/{id}")
    public ResponseEntity<Map<String, Boolean>> deleteRdv(@PathVariable("id") int id) {
        Rdv rdv1 = rdvRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("RDV not found with ID : " + id));
        rdvRepository.delete(rdv1);
        Map<String, Boolean> response = new HashMap<>();
        response.put("RDV Deleted", Boolean.TRUE);
        LOGGER.info("Delete RDV OK : " + id);
        return ResponseEntity.ok(response);
    }

}
