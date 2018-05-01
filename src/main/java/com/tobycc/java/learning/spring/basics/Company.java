package com.tobycc.java.learning.spring.basics;

import java.util.Iterator;
import java.util.List;

/**
 * Created by toby.christey-clover on 30/06/2017.
 */
public class Company {

    List<Employee> employees;
    String brand;

    public Company() {
        brand="Unknown";
    }

    public Company(String brand) {
        this.brand=brand;
    }


    public String getBrand() {
        return brand;
    }

    //Tells Spring that the member variable/field needs to be initialized via setter or in a <property> element,
    //otherwise throw exception. In addition, need to add to the applicationContext.xml the following:
    //<bean class=”org.springframework.beans.factory.annotation.RequiredAnnotationBeanPostProcessor” />

    //@Required
    public void setBrand(String brand) {
        this.brand = brand;
    }

    public List<Employee> getEmployees() {
        return employees;
    }

    public void setEmployees(List<Employee> employees) {
        this.employees = employees;
    }


    public void display() {
        Iterator<Employee> itr = employees.iterator();
        int count=1;
        while(itr.hasNext()) {
            System.out.println("Employee number "+count+": ");
            itr.next().whereDoTheyLive();
            count++;
        }
    }

    public void getNumberOfEmployees() {
        System.out.println("There are "+employees.size()+" employees in the company.");
    }

    
}
