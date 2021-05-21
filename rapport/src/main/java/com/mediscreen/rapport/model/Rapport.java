package com.mediscreen.rapport.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;


public class Rapport implements Serializable {

    private static final long serialVersionUID = 1L;

    private String lastName;
    private String firstName;
    private Sex sex;
    private long age;

    private Status status;

    public Rapport() {
    }

    public Rapport(String lastName, String firstName, Sex sex, long age, Status status) {
        this.lastName = lastName;
        this.firstName = firstName;
        this.sex = sex;
        this.age = age;
        this.status = status;
    }

    public Rapport(String lastName, String firstName, String sex, long patientAge, Status status) {

    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public Sex getSex() {
        return sex;
    }

    public void setSex(Sex sex) {
        this.sex = sex;
    }

    public long getAge() {
        return age;
    }

    public void setAge(long age) {
        this.age = age;
    }

    public Status getStatus() {
        return status;
    }

    public void setAssessment(Status status) {
        this.status = status;
    }

}
