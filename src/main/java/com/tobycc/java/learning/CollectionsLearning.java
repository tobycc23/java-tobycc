package com.tobycc.java.learning;

import java.util.*;

/**
 * Created by toby.christey-clover on 25/01/2018.
 */
public class CollectionsLearning {


    //Converting between collections is simple
    public static void convertBetweenCollections() {
        List list = new ArrayList();
        list.add("one");
        list.add("two");
        list.add("two");
        list.add("three");
        Set set = new HashSet(list);
        list = new ArrayList(set);
        //List now has duplicates removed but is ordered unlike set
        System.out.println(list);
    }

    //someCollection.toArray() converts a Collection to an Array
    public static void usingToArray() {
        List list = new ArrayList();
        list.add("h");
        list.add("g");
        String[] array = (String[]) list.toArray();
    }

    //Arrays.asList(someArray) converts Arrays to a Java Collection
    public static void usingAsList() throws UnsupportedOperationException {
        String[] array = new String[]{"h","g"};
        List list = Arrays.asList(array);

        //The following will throw an UnsupportedOperationException, the list formed from asList(...) is immutable
        //in that it cannot have elements added or removed; the size must remain the same.
        try {
            List a = Arrays.asList("hello", "goodbye");
            a.remove(1);
        } catch (UnsupportedOperationException e) {
            System.out.println(e);
        }

        //The following will work as it is wrapped in an ArrayList. Use this to initialise a list with values
        //as this list will be mutable.
        List b = new ArrayList(Arrays.asList("hello again", "goodbye again"));
        b.remove(1);
        System.out.println(b.size());
    }


    public static void bulkOperations() {
        Set s1 = new HashSet<String>();
    }


    public static void main(String[] args) {
        usingAsList();
        convertBetweenCollections();
    }

}
