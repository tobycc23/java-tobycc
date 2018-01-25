package com.tobycc.java.learning;

import com.tobycc.java.model.Person;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Predicate;

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
        boolean test(Person p);
    }
    //...
    public static void printPersons(List<Person> roster, CheckPerson tester) {
        for (Person p : roster) {
            if (tester.test(p)) {
                p.printPerson();
            }
        }
    }


    //3. This is using standard functional interfaces
    public static void printPersonsFuncs(List<Person> roster,
                                         Predicate<Person> tester,
                                         Function<Person, String> mapper,
                                         Consumer<String> block) {
        for (Person p : roster) {
            if(tester.test(p)) {
                String data = mapper.apply(p);
                block.accept(data);
            }
        }
    }

    //4. Can make this generic...
    public static <X,Y> void printPersonsGenericFuncs(List<X> roster,
                                         Predicate<X> tester,
                                         Function<X, Y> mapper,
                                         Consumer<Y> block) {
        for (X p : roster) {
            if (tester.test(p)) {
                Y data = mapper.apply(p);
                block.accept(data);
            }
        }
    }


    //5.
    public static void methodReferenceExample(Comparator<Person> comparator, Person a, Person b) {
        comparator.compare(a, b);
    }


    public static void main(String[] args) {
        //2. we can either implement CheckPerson in a class (not done here) or we can form an
        //anonymous class on the fly as done here
        printPersons(new ArrayList<>(), new CheckPerson() {
            public boolean test(Person p) {
                return p.getAge() >= 50;
            }
        });

        //CheckPerson is a functional interface: contains only one abstract method (may contain multiple default or
        //static methods as well). Therefore can do the following instead, Lambda expression.
        //Need only the argument for the one method and then the implementation of it
        printPersons(new ArrayList<>(), (Person p) -> { return p.getAge() >= 50; });
        printPersons(new ArrayList<>(), p -> { return p.getAge() >= 50; });
        printPersons(new ArrayList<>(), p -> p.getAge() >= 50);
        //Can avoid curly brackets and return keyword if only one line is needed in the implementation


        //3. JDK defines several standard functional interfaces, which you can find in the package java.util.function
        //Can use interface Predicate<T> that contains the method boolean test(T t) for testing a predicate
        //Can use interface Function<T,R> that contains the method R apply(T t) to return a value R given some input T
        //Can use interface Consumer<T> that contains the method void accept(T t) to take some input and cause side effects
        printPersonsFuncs(new ArrayList<>(),
                p -> p.getAge() >= 50,
                p -> p.getEmailAddress(),
                email -> System.out.println(email));


        //5. Comparator has one method to implement making it a functional interface. The method takes two args and
        //returns an int
        Person a = new Person();
        a.setAge(50);
        Person b = new Person();
        b.setAge(51);
        //This lambda expression implements the Comparator functional interface using *an existing method*
        methodReferenceExample((Person pA, Person pB) -> Person.compareAges(pA,pB), a, b);
        //As it is using an existing method, can be shortened to the following syntax (The class and method called by
        //lambda expression). This is a method reference!
        methodReferenceExample(Person::compareAges, a, b);
        //Both lines above are analogous to one another

        //Can also us method references with containingObject::instanceMethodName, ContainingType::methodName, ClassName::new
    }
}
