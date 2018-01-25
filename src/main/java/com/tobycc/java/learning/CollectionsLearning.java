package com.tobycc.java.learning;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Created by toby.christey-clover on 25/01/2018.
 */
public class CollectionsLearning {

    public static void usingAsList() throws UnsupportedOperationException {
        //The following will throw an UnsupportedOperationException, the List formed from asList(...) is immutable
        //in that it cannot have elements added or removed; the size must remain the same.
        try {
            List a = Arrays.asList("hello", "goodbye");
            a.remove(1);
        } catch (UnsupportedOperationException e) {
            System.out.println(e);
        }

        //The following will work, however, as it is wrapped in an ArrayList. Use this to initialise a list with values
        //as this list will be mutable.
        List b = new ArrayList(Arrays.asList("hello again", "goodbye again"));
        b.remove(1);
        System.out.println(b.size());
    }

    public static void main(String[] args) {
        usingAsList();
    }

}
