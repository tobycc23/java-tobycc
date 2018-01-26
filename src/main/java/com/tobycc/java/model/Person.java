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

    public Person() {
//..
    }

    public Person(String name, LocalDate birthday, Gender gender, String emailAddress, int age) {
        this.name = name;
        this.birthday = birthday;
        this.gender = gender;
        this.emailAddress = emailAddress;
        this.age = age;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) { this.age = age; }

    public Gender getGender() {
        return gender;
    }

    public void setGender(Gender gender) {
        this.gender = gender;
    }

    public String getEmailAddress() { return emailAddress; }

    public void setEmailAddress(String emailAddress) {
        this.emailAddress = emailAddress;
    }

    public void printPerson() {
        System.out.println(this.toString());
    }


    public static int compareAges(Person a, Person b) {
        return a.getAge()>b.getAge() ? 1 : 0;
    }

    public static Person[] randomListOfPeople() {
        Person a1 = new Person("a1", null, Gender.MALE, "a1@gmail.com", 55);
        Person a2 = new Person("a2", null, Gender.MALE, "a2@gmail.com", 24);
        Person a3 = new Person("a3", null, Gender.MALE, "a3@gmail.com", 33);
        Person b1 = new Person("b1", null, Gender.FEMALE, "b1@gmail.com", 21);
        Person b2 = new Person("b2", null, Gender.FEMALE, "b2@gmail.com", 49);
        Person b3 = new Person("b3", null, Gender.FEMALE, "b3@gmail.com", 80);
        Person b4 = new Person("b4", null, Gender.FEMALE, "b4@gmail.com", 34);

        return new Person[]{a1,a2,a3,b1,b2,b3,b4};
    }
}
