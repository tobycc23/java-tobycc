package com.tobycc.java.model;


import com.tobycc.java.model.enums.Gender;
import java.time.LocalDate;

/**
 * Created by toby.christey-clover on 25/01/2018.
 */
public class Person {
    private String name;
    private LocalDate birthday;
    private Gender gender;
    private String emailAddress;
    private int age;

    public int getAge() {
        return age;
    }

    public void printPerson() {
        System.out.println(this.toString());
    }
}
