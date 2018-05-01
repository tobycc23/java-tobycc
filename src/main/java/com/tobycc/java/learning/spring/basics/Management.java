package com.tobycc.java.learning.spring.basics;

import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by toby.christey-clover on 04/07/2017.
 */

//This creates a component bean called "management" which can be accessed in the usual way
@Component
public class Management {

    List<Building> manageBuildings = new ArrayList<Building>();
    List<Company> manageCompanies = new ArrayList<Company>();

    public void echo() {
        System.out.println("We are management.");
    }
}
