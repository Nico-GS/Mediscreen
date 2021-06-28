package com.mediscreen.rapport.controller;

import com.mediscreen.rapport.exception.ResourceNotFoundException;
import com.mediscreen.rapport.model.Rapport;
import com.mediscreen.rapport.service.RapportService;
import io.swagger.annotations.ApiOperation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
@CrossOrigin(origins = "${mediscreen.front.cross.origin}")
@RequestMapping("/api/")
public class RapportController {

    private static final Logger LOGGER = LoggerFactory.getLogger(RapportController.class);
    private final RapportService rapportService;


    public RapportController(RapportService rapportService) {
        this.rapportService = rapportService;
    }

    /**
     * Get a report for a patient with last & first name
     * @param lastName the patient lastname
     * @param firstName the patient firstname
     * @return the report of the patient
     */
    @ApiOperation(value = "Get report patient with last and first name")
    @GetMapping("/reports/lastAndFirstName")
    public ResponseEntity<Rapport> getRapportByLastAndFirstName(@RequestParam("lastName") String lastName, @RequestParam("firstName") String firstName) {
        LOGGER.info("GET Rapport with last name : {} and first name : {}", lastName, firstName);
        Rapport rapport = rapportService.getRapportByLastAndFirstName(lastName, firstName);
        return new ResponseEntity<>(rapport, HttpStatus.OK);
    }

    /**
     * Get a report with patient ID
     * @param id the patient ID
     * @return the report of the patient
     */
    @ApiOperation(value = "Get a report with ID")
    @GetMapping("/reports/{id}")
    public ResponseEntity<Rapport> getRapportById (@PathVariable int id) {
        LOGGER.info("GET Rapport with ID : {} OK", id);
        Rapport rapport = rapportService.getRapportById(id);
        return new ResponseEntity<>(rapport, HttpStatus.OK);
    }

}
