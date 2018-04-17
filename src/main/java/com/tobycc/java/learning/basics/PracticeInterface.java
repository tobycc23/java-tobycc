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
// Interface contract for different planets
public interface PracticeInterface {
    static final int SOLARSYSTEM = 1;
    static final int SUN = 1;
    int daysToOrbit(String planet);
    String planetType(int type);
}
