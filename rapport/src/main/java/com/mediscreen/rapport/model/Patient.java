package com.mediscreen.rapport.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDate;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class Patient {

    private int id_patient;
    private String lastName;
    private String firstName;
    private LocalDate dateOfBirth;
    private String address;
    private long phoneNumber;
    private Sex sex;

}
