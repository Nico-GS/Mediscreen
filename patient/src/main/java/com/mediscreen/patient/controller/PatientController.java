package com.mediscreen.patient.controller;

import com.mediscreen.patient.exception.ResourceNotFoundException;
import com.mediscreen.patient.model.Patient;
import com.mediscreen.patient.repository.PatientRepository;
import com.mediscreen.patient.service.PatientService;
import io.swagger.annotations.ApiOperation;
import io.swagger.models.Response;
import org.apache.logging.log4j.LogManager;

import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
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
public class PatientController {

    private final Logger LOGGER = LogManager.getLogger(PatientController.class);

    @Autowired
    private PatientRepository patientRepository;

    @Autowired
    private PatientService patientService;

    @GetMapping("/")
    public String homePatient() {
        LOGGER.info("Home OK");
        return "Hello !";
    }

    @ApiOperation(value = "Get list of all patients")
    @GetMapping("/patient")
    public List<Patient> getPatient() {
        LOGGER.info("GET Patient OK");
        return (List<Patient>) patientRepository.findAll();
    }

    @GetMapping("/patient/{id}")
    public ResponseEntity <Patient> getPatientById(@PathVariable int id) {
        Patient patient = patientRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Patient don't exist with ID : " + id));
        return ResponseEntity.ok(patient);
    }

    @ApiOperation(value = "Add a Patient with information : LastName, FirstName, Address, Sex, Date of Birth")
    @PostMapping("/patient")
    public ResponseEntity addPatient(@RequestBody @Valid @Validated Patient patient) {
        if (patientService.getById(patient.getId_patient()).isPresent()) {
            LOGGER.info("Error created Patient with ID : " + patient.getId_patient());
            return ResponseEntity.badRequest().build();
        }
        patientService.createPatient(patient);
        LOGGER.info("Create Patient OK : " + patient.getId_patient());
        return ResponseEntity.ok(HttpStatus.OK);
    }

    @ApiOperation(value = "Update a Patient with information : LastName, FirstName, Address, Sex, Date of Birth")
    @PutMapping("/patient/{id}")
    public ResponseEntity<Patient> updatePatient(@RequestBody @Valid @Validated Patient patients, @PathVariable int id) {
        Patient patient = patientRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Patient not found with ID :" + id));
        patient.setFirstName(patients.getFirstName());
        patient.setLastName(patients.getLastName());
        patient.setDateOfBirth(patients.getDateOfBirth());
        patient.setSex(patients.getSex());
        patient.setAddress(patients.getAddress());
        patient.setPhoneNumber(patients.getPhoneNumber());
        Patient updatePatient = patientRepository.save(patient);
        return ResponseEntity.ok(updatePatient);
    }

    @ApiOperation(value = "Delete a Patient by ID")
    @DeleteMapping("/patient/{id}")
    public ResponseEntity<Map<String, Boolean>> deletePatient(@PathVariable("id") int id) {
        Patient patient = patientRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Patient not found with ID : " + id));
        patientRepository.delete(patient);
        Map<String, Boolean> response = new HashMap<>();
        response.put("Deleted", Boolean.TRUE);
        return ResponseEntity.ok(response);
    }


}