package com.tobycc.java.learning.core;

import com.tobycc.java.model.Person;
import com.tobycc.java.model.enums.Gender;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Created by toby.christey-clover on 25/01/2018.
 */
public class AggregateOpsLearning {

    private static final List<Person> list = new ArrayList<>(Arrays.asList(Person.randomListOfPeople()));

    //1. comparing traditional way to manipulate Collection and Java 8 way of using aggregate functions
    public static void collectionVsAggregateOps() {
        //Obtain a source of objects, filter objects that match a Predicate object, map objects to another value as
        //specified by a Function object, then perform an action as specified by a Consumer object
        list.stream()  //Stream<E> stream()
                .filter(p -> p.getAge() >= 50) 	//Stream<T> filter(Predicate<? super T> predicate)
                .map(p -> p.getEmailAddress())  //<R> Stream<R> map(Function<? super T, ? extends R> mapper)
                .forEach(email -> System.out.println(email)); //void forEach(Consumer<? super T> action)

        //Unlike a collection, this is a pipeline of ops with a stream (sequence of elements) running through it

        //The above can be translated into the traditional way:
        for(Person p : list) {
            if(p.getAge() >= 50) {
                String email = p.getEmailAddress();
                System.out.println(email);
            }
        }
    }


    //2. Using aggregate operations further and reduction ops
    public static void furtherAggregateops() {
        double average;
        average = list.stream()
                .filter(p -> p.getGender() == Gender.MALE)
                .mapToInt(Person::getAge) //or use .mapToInt(p -> p.getAge()) ...same thing
                .average()
                .getAsDouble();
        //JDK contains many terminal operations (such as average, sum, min, max, and count) that return one value by
        //combining the contents of a stream; reduction ops.
        System.out.println(average);
    }


    //3. Reduction examples
    public static void reduceExample() {
        //sum() is a reduction operation
        Integer totalAge;
        totalAge = list.stream()
                .mapToInt(Person::getAge)
                .sum();
        System.out.println(totalAge);

        //Stream.reduce() is a general-purpose reduction op which takes in the Identity (init value / default if no
        //elements in stream) and Accumulator (takes parameters which are partial result of reduction so far and next
        //element, returning a new partial result)
        Integer totalAgeReduce;
        totalAgeReduce = list.stream()
                .map(Person::getAge)
                .reduce(0, (a, b) -> a + b);
                //.reduce(0, AggregateOpsLearning::aggSum);  //could use this line instead of the one above
        System.out.println(totalAgeReduce);
    }

    public static int aggSum(int a, int b) {
        return a+b;
    }


    //4. If your reduce op involves adding elements to a collection, note every time your accumulator processes
    //an element it creates a new collection to include the element, highly inefficient. With Stream.collect you update
    //existing collection instead.
    public static void collectExample() {
        Collection<String> emailAddressesOverFifties;
        emailAddressesOverFifties = list.stream()  //Stream<E> stream()
                .filter(p -> p.getAge() >= 50)
                .map(p -> p.getEmailAddress())
                .collect(Collectors.toList());
        System.out.println(emailAddressesOverFifties);
    }


    public static void main(String[] args) {
        //Using the example from LambdaLearning.java we can match the functional interfaces to aggregate operations
        collectionVsAggregateOps();
        furtherAggregateops();
        reduceExample();
        collectExample();
    }
}
