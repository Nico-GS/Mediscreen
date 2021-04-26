package com.mediscreen.patient.controller;

import com.mediscreen.patient.model.Patient;
import com.mediscreen.patient.repository.PatientRepository;
import com.mediscreen.patient.service.PatientService;
import org.aspectj.lang.annotation.Before;
import org.hamcrest.Matchers;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.ResponseEntity;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ContextConfiguration(classes = {PatientController.class})
@ExtendWith(MockitoExtension.class)
class PatientControllerTest {

    @Autowired
    private PatientController patientController;

    @MockBean
    private PatientRepository patientRepository;

    @MockBean
    private PatientService patientService;

    @Mock
    private PatientRepository mockPatientRepository;
    @Mock
    private PatientService mockPatientService;

    @InjectMocks
    private PatientController patientControllerUnderTest;


    @Test
    void testGetPatient() {
        final Iterable<Patient> patients = Arrays.asList(new Patient(0, "lastName", "firstName", LocalDate.of(2020, 1, 1), "address", 0L, "sex"));
        when(mockPatientRepository.findAll()).thenReturn((List<Patient>) patients);
        final List<Patient> result = patientControllerUnderTest.getPatient();
    }

    @Test
    void testGetPatient_PatientRepositoryReturnsNoItems() {
        when(mockPatientRepository.findAll()).thenReturn(Collections.emptyList());
        final List<Patient> result = patientControllerUnderTest.getPatient();
    }


    @Test
    void testAddPatient() {
        final Patient patient = new Patient(0, "lastName", "firstName", LocalDate.of(2020, 1, 1), "address", 0L, "M");
        when(mockPatientService.getById(0)).thenReturn(Optional.empty());
        final ResponseEntity result = patientControllerUnderTest.addPatient(patient);
        verify(mockPatientService).createPatient(any(Patient.class));
    }




}
