package com.mediscreen.rapport.proxy;

import com.mediscreen.rapport.model.Patient;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient(name = "ms-patient", url = "${patient.url.cross}")
public interface PatientProxy {

    /**
     * Return list of all patient
     * @return all patients
     */
    @GetMapping(value = "/api/patient")
    Patient getPatient();

    /**
     * Return a patient by ID
     * @param id of the patient
     * @return a patient with ID
     */
    @GetMapping(value = "/api/patient/{id}")
    Patient getPatientById (@PathVariable int id);

    /**
     * Return a patient with last and first name
     * @param lastName of patient
     * @param firstName of patient
     * @return the patient with last and first name
     */
    @GetMapping(value = "/api/patient/getPatientLastAndFirst")
    Patient getPatientByLastAndFirstName (@RequestParam("last") String lastName, @RequestParam("first") String firstName);

}
