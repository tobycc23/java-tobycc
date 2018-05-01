package com.tobycc.java.learning.spring.basics;

/**
 * Created by toby.christey-clover on 29/06/2017.
 */
public class Employee {


    /* Usual way
    Address address;
    Employee() {
        address= new Address();
    }
     */

    /* IoC way (no dependency/tight coupling between Employee and Address) */
    Address address;
    Employee(Address address) {
        this.address=address;
    }

    public void whereDoTheyLive() {
        System.out.println("This employee lives in " + address.getCity());
    }
}
