//package com.mediscreen.patient.controller;
//
//import com.mediscreen.patient.exception.ResourceNotFoundException;
//import com.mediscreen.patient.model.Patient;
//import com.mediscreen.patient.repository.PatientRepository;
//import com.mediscreen.patient.service.PatientService;
//
//import java.util.ArrayList;
//
//import java.util.Map;
//
//import org.aspectj.lang.annotation.Before;
//import org.hamcrest.Matchers;
//import org.junit.jupiter.api.BeforeEach;
//import org.junit.jupiter.api.Test;
//import org.junit.jupiter.api.extension.ExtendWith;
//import org.mockito.InjectMocks;
//import org.mockito.Mock;
//import org.mockito.junit.jupiter.MockitoExtension;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.test.mock.mockito.MockBean;
//import org.springframework.http.HttpStatus;
//import org.springframework.http.ResponseEntity;
//
//import java.time.LocalDate;
//import java.util.Arrays;
//import java.util.Collections;
//import java.util.List;
//import java.util.Optional;
//
//import org.springframework.test.context.ContextConfiguration;
//import org.springframework.test.context.junit.jupiter.SpringExtension;
//import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;
//import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
//import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
//import org.springframework.test.web.servlet.setup.MockMvcBuilders;
//
//import static org.junit.jupiter.api.Assertions.assertEquals;
//import static org.junit.jupiter.api.Assertions.assertSame;
//import static org.junit.jupiter.api.Assertions.assertThrows;
//import static org.junit.jupiter.api.Assertions.assertTrue;
//import static org.mockito.ArgumentMatchers.any;
//import static org.mockito.Mockito.doNothing;
//import static org.mockito.Mockito.mock;
//import static org.mockito.Mockito.verify;
//import static org.mockito.Mockito.when;
//
//@ContextConfiguration(classes = {PatientController.class})
//@ExtendWith(MockitoExtension.class)
//class PatientControllerTest {
//
//    @Autowired
//    private PatientController patientController;
//
//    @MockBean
//    private PatientRepository patientRepository;
//
//    @MockBean
//    private PatientService patientService;
//
//    @Mock
//    private PatientRepository mockPatientRepository;
//    @Mock
//    private PatientService mockPatientService;
//
//    @InjectMocks
//    private PatientController patientControllerUnderTest;
//
//
//    @Test
//    void testGetPatient() {
//        final Iterable<Patient> patients = Arrays.asList(new Patient(0, "lastName", "firstName", LocalDate.of(2020, 1, 1), "address", 0L, "sex"));
//        when(mockPatientRepository.findAll()).thenReturn((List<Patient>) patients);
//        final List<Patient> result = patientControllerUnderTest.getPatient();
//    }
//
//    @Test
//    public void testGetPatient2() {
//        PatientRepository patientRepository = mock(PatientRepository.class);
//        ArrayList<Patient> patientList = new ArrayList<Patient>();
//        when(patientRepository.findAll()).thenReturn(patientList);
//        PatientController patientController = new PatientController();
//        patientController.patientRepository = patientRepository;
//        List<Patient> actualPatient = patientController.getPatient();
//        assertSame(patientList, actualPatient);
//        assertTrue(actualPatient.isEmpty());
//        verify(patientRepository).findAll();
//    }
//
//    @Test
//    void testGetPatient_PatientRepositoryReturnsNoItems() {
//        when(mockPatientRepository.findAll()).thenReturn(Collections.emptyList());
//        final List<Patient> result = patientControllerUnderTest.getPatient();
//    }
//
//    @Test
//    public void testGetPatientById() {
//        Patient patient = new Patient();
//        patient.setLastName("Doe");
//        patient.setDateOfBirth(LocalDate.ofEpochDay(1L));
//        patient.setId_patient(1);
//        patient.setPhoneNumber(1L);
//        patient.setAddress("42 Main St");
//        patient.setFirstName("Jane");
//        patient.setSex("Sex");
//        PatientRepository patientRepository = mock(PatientRepository.class);
//        when(patientRepository.findById((Integer) org.mockito.Mockito.any())).thenReturn(Optional.<Patient>of(patient));
//        PatientController patientController = new PatientController();
//        patientController.patientRepository = patientRepository;
//        ResponseEntity<Patient> actualPatientById = patientController.getPatientById(1);
//        assertEquals(HttpStatus.OK, actualPatientById.getStatusCode());
//        assertTrue(actualPatientById.hasBody());
//        verify(patientRepository).findById((Integer) org.mockito.Mockito.any());
//    }
//
//    @Test
//    public void testGetPatientById2() {
//        PatientRepository patientRepository = mock(PatientRepository.class);
//        when(patientRepository.findById((Integer) org.mockito.Mockito.any())).thenReturn(Optional.<Patient>empty());
//        PatientController patientController = new PatientController();
//        patientController.patientRepository = patientRepository;
//        assertThrows(ResourceNotFoundException.class, () -> patientController.getPatientById(1));
//        verify(patientRepository).findById((Integer) org.mockito.Mockito.any());
//    }
//
//
//    @Test
//    void testAddPatient() {
//        final Patient patient = new Patient(0, "lastName", "firstName", LocalDate.of(2020, 1, 1), "address", 0L, "M");
//        when(mockPatientService.getById(0)).thenReturn(Optional.empty());
//        final ResponseEntity result = patientControllerUnderTest.addPatient(patient);
//        verify(mockPatientService).createPatient(any(Patient.class));
//    }
//
//    @Test
//    public void testUpdatePatient() {
//        Patient patient = new Patient();
//        patient.setLastName("Doe");
//        patient.setDateOfBirth(LocalDate.ofEpochDay(1L));
//        patient.setId_patient(1);
//        patient.setPhoneNumber(1L);
//        patient.setAddress("42 Main St");
//        patient.setFirstName("Jane");
//        patient.setSex("Sex");
//        Optional<Patient> ofResult = Optional.<Patient>of(patient);
//
//        Patient patient1 = new Patient();
//        patient1.setLastName("Doe");
//        patient1.setDateOfBirth(LocalDate.ofEpochDay(1L));
//        patient1.setId_patient(1);
//        patient1.setPhoneNumber(1L);
//        patient1.setAddress("42 Main St");
//        patient1.setFirstName("Jane");
//        patient1.setSex("Sex");
//        PatientRepository patientRepository = mock(PatientRepository.class);
//        when(patientRepository.save((Patient) org.mockito.Mockito.any())).thenReturn(patient1);
//        when(patientRepository.findById((Integer) org.mockito.Mockito.any())).thenReturn(ofResult);
//        PatientController patientController = new PatientController();
//        patientController.patientRepository = patientRepository;
//        ResponseEntity<Patient> actualUpdatePatientResult = patientController.updatePatient(new Patient(), 1);
//        assertEquals(HttpStatus.OK, actualUpdatePatientResult.getStatusCode());
//        assertTrue(actualUpdatePatientResult.hasBody());
//        verify(patientRepository).save((Patient) org.mockito.Mockito.any());
//        verify(patientRepository).findById((Integer) org.mockito.Mockito.any());
//    }
//
//
//    @Test
//    public void testDeletePatient() {
//        Patient patient = new Patient();
//        patient.setLastName("Doe");
//        patient.setDateOfBirth(LocalDate.ofEpochDay(1L));
//        patient.setId_patient(1);
//        patient.setPhoneNumber(1L);
//        patient.setAddress("42 Main St");
//        patient.setFirstName("Jane");
//        patient.setSex("Sex");
//        Optional<Patient> ofResult = Optional.<Patient>of(patient);
//        PatientRepository patientRepository = mock(PatientRepository.class);
//        doNothing().when(patientRepository).delete((Patient) org.mockito.Mockito.any());
//        when(patientRepository.findById((Integer) org.mockito.Mockito.any())).thenReturn(ofResult);
//        PatientController patientController = new PatientController();
//        patientController.patientRepository = patientRepository;
//        ResponseEntity<Map<String, Boolean>> actualDeletePatientResult = patientController.deletePatient(1);
//        assertEquals("<200 OK OK,{Deleted=true},[]>", actualDeletePatientResult.toString());
//        assertEquals(1, actualDeletePatientResult.getBody().size());
//        assertTrue(actualDeletePatientResult.hasBody());
//        assertEquals(HttpStatus.OK, actualDeletePatientResult.getStatusCode());
//        verify(patientRepository).delete((Patient) org.mockito.Mockito.any());
//        verify(patientRepository).findById((Integer) org.mockito.Mockito.any());
//    }
//
//
//
//
//
//
//
//
//}
