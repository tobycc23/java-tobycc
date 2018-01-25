package com.tobycc.java.learning;

import com.tobycc.java.model.Person;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Created by toby.christey-clover on 24/01/2018.
 */
public class LambdaLearning {

    //1. This is for specific case
    public static void printPersonsOlderThan(List<Person> roster, int ageLimit) {
        for (Person p : roster) {
            if (p.getAge() >= ageLimit) {
                p.printPerson();
            }
        }
    }


    //2. This is for generic case
    public interface CheckPerson {
        public boolean test(Person p);
    }

    public static void printPersons(List<Person> roster, CheckPerson tester) {
        for (Person p : roster) {
            if (tester.test(p)) {
                p.printPerson();
            }
        }
    }


    public static void main(String[] args) {


        //Using case 2. we can either implement CheckPerson in a class (not done here)
        //or we can form an anonymous class on the fly as done here
        printPersons(new ArrayList<Person>(), new CheckPerson() {
            public boolean test(Person p) { return p.getAge() >= 50; }
        });
    }
}
