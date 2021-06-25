package com.mediscreen.patient.service;

import static org.junit.jupiter.api.Assertions.assertSame;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.any;
import static org.mockito.Mockito.anyString;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import com.mediscreen.patient.model.Patient;
import com.mediscreen.patient.repository.PatientRepository;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

@ContextConfiguration(classes = {PatientService.class})
@ExtendWith(SpringExtension.class)
public class PatientServiceTest {

    @MockBean
    private PatientRepository patientRepository;

    @Autowired
    private PatientService patientService;

    @Test
    public void testCreatePatient() {
        Patient patient = new Patient();
        patient.setLastName("Doe");
        patient.setDateOfBirth(LocalDate.ofEpochDay(1L));
        patient.setId_patient(1);
        patient.setPhoneNumber(1L);
        patient.setAddress("42 Main St");
        patient.setFirstName("Jane");
        patient.setSex("Sex");
        when(this.patientRepository.save((Patient) any())).thenReturn(patient);
        this.patientService.createPatient(new Patient());
        verify(this.patientRepository).save((Patient) any());
    }

    @Test
    public void testCreatePatient2() {
        Patient patient = new Patient();
        patient.setLastName("Doe");
        patient.setDateOfBirth(LocalDate.ofEpochDay(1L));
        patient.setId_patient(1);
        patient.setPhoneNumber(1L);
        patient.setAddress("42 Main St");
        patient.setFirstName("Jane");
        patient.setSex("Sex");
        when(this.patientRepository.save((Patient) any())).thenReturn(patient);
        this.patientService.createPatient(new Patient());
        verify(this.patientRepository).save((Patient) any());
        assertTrue(((Collection<Patient>) this.patientService.findAllPatient()).isEmpty());
    }

    @Test
    public void testGetById() {
        Patient patient = new Patient();
        patient.setLastName("Doe");
        patient.setDateOfBirth(LocalDate.ofEpochDay(1L));
        patient.setId_patient(1);
        patient.setPhoneNumber(1L);
        patient.setAddress("42 Main St");
        patient.setFirstName("Jane");
        patient.setSex("Sex");
        Optional<Patient> ofResult = Optional.<Patient>of(patient);
        when(this.patientRepository.findById((Integer) any())).thenReturn(ofResult);
        this.patientService.getById(1);
        verify(this.patientRepository).findById((Integer) any());
    }

    @Test
    public void testGetById2() {
        Patient patient = new Patient();
        patient.setLastName("Doe");
        patient.setDateOfBirth(LocalDate.ofEpochDay(1L));
        patient.setId_patient(1);
        patient.setPhoneNumber(1L);
        patient.setAddress("42 Main St");
        patient.setFirstName("Jane");
        patient.setSex("Sex");
        Optional<Patient> ofResult = Optional.<Patient>of(patient);
        when(this.patientRepository.findById((Integer) any())).thenReturn(ofResult);
        Optional<Patient> actualById = this.patientService.getById(1);
        assertSame(ofResult, actualById);
        assertTrue(actualById.isPresent());
        verify(this.patientRepository).findById((Integer) any());
        assertTrue(((Collection<Patient>) this.patientService.findAllPatient()).isEmpty());
    }

    @Test
    public void testFindAllPatient() {
        ArrayList<Patient> patientList = new ArrayList<Patient>();
        when(this.patientRepository.findAll()).thenReturn(patientList);
        Iterable<Patient> actualFindAllPatientResult = this.patientService.findAllPatient();
        assertSame(patientList, actualFindAllPatientResult);
        assertTrue(((Collection<Patient>) actualFindAllPatientResult).isEmpty());
        verify(this.patientRepository).findAll();
    }

    @Test
    public void testFindPatientByLastAndFirstName() {
        Patient patient = new Patient();
        patient.setLastName("Doe");
        patient.setDateOfBirth(LocalDate.ofEpochDay(1L));
        patient.setId_patient(1);
        patient.setPhoneNumber(1L);
        patient.setAddress("42 Main St");
        patient.setFirstName("Jane");
        patient.setSex("Sex");
        when(this.patientRepository.findByLastNameAndFirstName(anyString(), anyString())).thenReturn(patient);
        assertSame(patient, this.patientService.findPatientByLastAndFirstName("Doe", "Jane"));
        verify(this.patientRepository).findByLastNameAndFirstName(anyString(), anyString());
        assertTrue(((Collection<Patient>) this.patientService.findAllPatient()).isEmpty());
    }

}

