package com.tobycc.java.learning.spring.basics;

/**
 * Created by toby.christey-clover on 29/06/2017.
 */
public class Address {

    private String city;

    public Address(String city) {
        this.city = city;
    }

    public Address() {}

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public void displayCity() {
        System.out.println("City: " + city);
    }
}
