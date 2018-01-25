package com.tobycc.java.learning;

import com.tobycc.java.model.Person;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by toby.christey-clover on 25/01/2018.
 */
public class AggregateOpsLearning {

    public static void main(String[] args) {
        //Using the example from LambdaLearning.java we can match the functional interfaces to aggregate operations

        //Obtain a source of objects, filter objects that match a Predicate object, map objects to another value as
        //specified by a Function object, then perform an action as specified by a Consumer object
        List<Person> list = new ArrayList<>();
        list.stream()  //Stream<E> stream()
                .filter(p -> p.getAge() >= 50) 	//Stream<T> filter(Predicate<? super T> predicate)
                .map(p -> p.getEmailAddress())  //<R> Stream<R> map(Function<? super T, ? extends R> mapper)
                .forEach(email -> System.out.println(email)); //void forEach(Consumer<? super T> action)

        //Unlike a collection, this is a pipeline of ops
    }
}
