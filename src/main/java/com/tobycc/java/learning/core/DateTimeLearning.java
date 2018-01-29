package com.tobycc.java.learning.core;

import java.time.LocalDate;
import java.time.Month;

/**
 * Created by toby.christey-clover on 29/01/2018.
 */
public class DateTimeLearning {

    public static void dateTimeExamples() {
        //Date-time API
        LocalDate today = LocalDate.now();
        LocalDate birthday = LocalDate.of(1994, Month.JANUARY,28);
        LocalDate twentyFourthBday = birthday.plusYears(24);

        System.out.println(twentyFourthBday);
    }

    public static void main(String[] args) {
        dateTimeExamples();
    }

}
