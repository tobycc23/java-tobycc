/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.tobycc.java.learning.basics;
import java.lang.*;
/**
 *
 * @author Toby
 */
// Planet X
public class PracticeSuperclass extends PracticeAbstract {
    protected int size = 4000;
    protected String name = "Planet X";
    private long population = 817938413;
    protected int messageN;
    protected String message;

    //This overrides moon above
    protected int moons = 4;
    protected static int staticMoons = 4;

    protected static String[] dust = {"dust"};

    public PracticeSuperclass() {
        
    }
    
    public PracticeSuperclass(int messageN, String message) {
        this.messageN = messageN;
        this.message = message;
    }
    
    public void printMessage() {
        System.out.println(message+"  "+messageN);
    }
    
    public void methodOverrideExample() {
        System.out.println("In PracticeSuperClass");
    }
    
    @Override
    public int daysToOrbit(String planet) {
        if(planet.equals("Planet Y") || planet.equals("Planet Z")) {
            return (int) (size/10 + population/1000000000);
        }
        return -1;
    }



    public static void main(String[] args) {
        System.out.println(new PracticeSuperclass().moons);
        //This calls the superclass method, need to override it
        System.out.println(PracticeSuperclass.getStaticMoons());

        System.out.println(PracticeSuperclass.dust.toString());
    }
}
