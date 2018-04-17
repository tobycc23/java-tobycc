/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.tobycc.java.learning.basics;

/**
 *
 * @author Toby
 */
public class PracticeSubclass extends PracticeSuperclass{
    
    //Remember: if super(x) not specified, default super() inserted as first line
    //in this constructor, so there must be no-arg constructor available in the superclass
    public PracticeSubclass() {
    }
    
    public PracticeSubclass(int messageN, String message) {
        super(messageN, message);
    }
    
    
    public void switchPlanets(int planet) {
        String monthString;
        switch (planet) {
            case 1:  monthString = "January";
                     break;
            case 2:  monthString = "February";
                     break;
            case 3:  monthString = "March";
                     break;
            case 4:  monthString = "April";
                     break;
            case 5:  monthString = "May";
                     break;
            case 6:  monthString = "June";
                     break;
            case 7:  monthString = "July";
                     break;
            case 8:  monthString = "August";
                     break;
            case 9:  monthString = "September";
                     break;
            case 10: monthString = "October";
                     break;
            case 11: monthString = "November";
                     break;
            case 12: monthString = "December";
                     break;
            default: monthString = "Invalid month";
                     break;
        }
        System.out.println(monthString);
    }
    
    //Override annotation makes it clearer
    @Override
    public void methodOverrideExample() {
        System.out.println("In PracticeSubclass: "+(SUN+moons+size));
    }
    
    //Cannot be accessed by subber...
    //Overloaded method
    public String getName() {
        return name;
    }
    
    //Cannot be accessed by subber...
    //Overloaded method
    public String getName(String extra) {
        return name+extra;
    }
    
    //Cannot be accessed by subber...
    public int getSize() {
        return size;
    }
    
    //Cannot be accessed by subber below as it breaks at compile time
    public void printSubMessage() {
        System.out.println("Here again");
    }
    
    public static void main (String[] args) {
        PracticeSuperclass subber = new PracticeSubclass(1, "Hello all");
        subber.printMessage();
        subber.methodOverrideExample();
        System.out.println(subber.getClass());
        
        //Upcasting and downcasting
        PracticeSubclass subsub = new PracticeSubclass(3, "Hello all friends");
        subsub.getSize();
        
        //The upcasting here (in brackets) is not needed as done automatically
        //Note that the actual object type does not change because of casting
        PracticeSuperclass subber2 = (PracticeSuperclass) subsub;
        subber2.methodOverrideExample();
        System.out.println(subber2.getClass());

        //Downcasting here from subber2 is okay as we know it holds a subclass object... 
        subsub = (PracticeSubclass) subber2;
        //But if this were to hold a superclass object a ClassCastException would be thrown  
        PracticeSuperclass nonsubber = new PracticeSuperclass(4, "Goodbye all friends");
        //subsub = (PracticeSubclass) nonsubber; this throws the exception
        //This can be rectified by running an instanceof() test
        
        //Condition tester
        int a  = 4<3 ? 2 : 10;
        System.out.println(a);
    }
   
}
