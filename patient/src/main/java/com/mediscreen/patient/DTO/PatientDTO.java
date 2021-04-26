package com.mediscreen.patient.DTO;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class PatientDTO {

    private int id_patient;
    private String lastName;
    private String firstName;
    private Date dateOfBirth;
    private String address;
    private long phoneNumber;
    private String sex;

}
