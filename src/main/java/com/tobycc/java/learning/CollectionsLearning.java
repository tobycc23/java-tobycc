package com.tobycc.java.learning;

import java.util.*;
import java.util.stream.Collectors;

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

        //or can use aggregate operations
        Set set2 = Arrays.asList("one","two","two","three").stream().collect(Collectors.toSet());
        System.out.println(set2);

        //or can simply use for loop or someCollection.Iterator and then the add() method (duplicates not added obviously)
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
        Set s1 = new HashSet<String>(Arrays.asList("one", "two", "three", "four", "seven"));
        Set s2 = new HashSet<String>(Arrays.asList("four", "five", "six", "seven", "eight", "nine"));

        //returns true if s2 a subset of s1
        s1.containsAll(s2);
        //transforms s1 into the union of s1 and s2
        s1.addAll(s2);
        //transforms s1 into the intersection of s1 and s2
        s1.retainAll(s2);
        //transforms s1 into the set difference of s1 and s2
        s1.removeAll(s2);

        //To get the set of elements contained in either of the sets but not in both do one of the following
        Set sA = new HashSet<String>(Arrays.asList("one", "two", "three", "four", "seven"));
        Set sB = new HashSet<String>(Arrays.asList("four", "five", "six", "seven", "eight", "nine"));
        Set symmetricDiff = new HashSet(sA);
        Set tmpA = new HashSet(sA);
        symmetricDiff.addAll(sB);
        tmpA.retainAll(sB);
        symmetricDiff.removeAll(tmpA);
        System.out.println(symmetricDiff);
        //or
        Set symmetricDiff2 = new HashSet(sA);
        Set tmpA2 = new HashSet(sA);
        Set tmpB = new HashSet(sB);
        symmetricDiff2.removeAll(sB);
        tmpB.removeAll(tmpA2);
        symmetricDiff2.addAll(tmpB);
        System.out.println(symmetricDiff2);

        //TreeSet will order the set (alphabetically)
        Set ts = new TreeSet(symmetricDiff2);
        System.out.println(ts);
    }



    public static void main(String[] args) {
        usingAsList();
        convertBetweenCollections();
        bulkOperations();
    }

}
