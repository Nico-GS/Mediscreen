package com.mediscreen.rapport.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class Rapport {

    private String lastName;
    private String firstName;
    private Sex sex;
    private long age;
    private Status status;

}
