package com.mediscreen.patient.model;

import lombok.*;
import org.hibernate.validator.constraints.Length;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import java.time.LocalDate;


@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "patient")
public class Patient {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id_patient;


    @Column(name = "last_name")
    @Length(min = 2, max = 60)
    @NotBlank(message = "LastName can't be blank")
    @NotNull
    private String lastName;

    @Column(name = "first_name")
    @Length(min = 2, max = 60)
    @NotBlank(message = "FirstName can't be blank")
    @NotNull
    private String firstName;

    @Column(name = "date_birth")
    @NotNull(message = "Date of Birth can't be null")
    @DateTimeFormat(pattern = "YYYY-mm-dd")
    private LocalDate dateOfBirth;

    @Column(name = "address")
    @NotBlank(message = "Address can't be blank")
    private String address;

    @Column(name = "phone_number")
    private long phoneNumber;

    @Column(name = "sex")
    @NotBlank(message = "Sex can't be blank")
    private Sex sex;

}
