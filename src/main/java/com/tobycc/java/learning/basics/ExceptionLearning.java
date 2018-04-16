package com.tobycc.java.learning.basics;

import java.io.IOException;

/**
 * Created by toby.christey-clover on 16/04/2018.
 */
public class ExceptionLearning {

    class AException {
        public void deposit(double amount) throws IOException {
            // Method implementation
            throw new IOException();
        }
        // Remainder of class definition
    }

    class BException {
        public void depoCheck(double amount) {
            try {
                new AException().deposit(amount);
            }
            catch(Exception e) {
                //throw new Exception(); //As this is a new checked exception, must be handled with 'throws Exception' in method definition
                throw new RuntimeException(); //As this is an unchecked exception, is does not have to be handled
            }
        }
    }
}
