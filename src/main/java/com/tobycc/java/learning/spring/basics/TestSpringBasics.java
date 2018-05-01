package com.tobycc.java.learning.spring.basics;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * Created by toby.christey-clover on 29/06/2017.
 */
public class TestSpringBasics {
    public static void main(String[] args) {
        ApplicationContext factory = new ClassPathXmlApplicationContext("applicationContext.xml");

        /*
        // This object is within the Spring/IoC container
        Address address = (Address) factory.getBean("addressbean");
        address.displayCity();

        Employee employee = (Employee) factory.getBean("employeebean");
        employee.whereDoTheyLive();

        Employee employee2 = (Employee) factory.getBean("employeebean2");
        employee2.whereDoTheyLive();
        */

        Company company = (Company) factory.getBean("company");
        company.display();
        System.out.println(company.getBrand());


        Building building = (Building) factory.getBean("building");
        building.getCompany().getNumberOfEmployees();
        building.setName("BuildingX");

        //Singleton default means building1 gets the same building bean, not a new initialization
        //So the name of building has been changed to "BuildingX"
        Building building1 = (Building) factory.getBean("building");
        System.out.println(building1.getName());

        Building building2 = (Building) factory.getBean("building2");
        System.out.println(building2.getBuildingLocation().getZone());
        //The below will fail as this bean is not autowired so will have no company information
        //building2.getCompany().getNumberOfEmployees();

        Management management = (Management) factory.getBean("management");
        management.echo();

    }
}
