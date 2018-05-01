package com.tobycc.java.learning.spring.basics;

import org.springframework.beans.factory.annotation.Autowired;

/**
 * Created by toby.christey-clover on 03/07/2017.
 */
public class Building {

    public Company getCompany() {
        return company;
    }

    public void setCompany(Company company) {
        this.company = company;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Locat getBuildingLocation() {
        return buildingLocation;
    }

    //This autowires to the bean with the type Locat, then if multiple with same type looks at byName, then looks at
    //by qualifier <qualifier value="someRelation"/>
    @Autowired
    //@Qualifier("someRelation")
    public void setBuildingLocation(Locat buildingLocation) {
        this.buildingLocation = buildingLocation;
    }

    private Company company;

    private String name;

    private Locat buildingLocation;

}
