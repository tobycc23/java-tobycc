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
// Planets with 3 moons and distance to sun closer than 5 million kms. 
public abstract class PracticeAbstract implements PracticeInterface {
    protected int distanceMin = 5;
    protected int moons = 3;
    
    @Override
    public String planetType(int type) {
        String temp;
        switch(type) {
            case 1: temp="Help";
            break;
            case 2: temp="Habitable";
            break;
            case 3: temp="Uninhabitable";
            break;
            case 4: temp="Perfect";
            break;
            default: temp="Neutral";
        }
        return temp;
    }
}
