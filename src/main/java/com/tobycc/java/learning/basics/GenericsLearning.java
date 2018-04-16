package com.tobycc.java.learning.basics;

/**
 * Created by toby.christey-clover on 13/04/2018.
 */
public class GenericsLearning {

    //This is not correct
    public static <T> int countGreaterThan(T[] anArray, T elem) {
        int count = 0;
        for (T e : anArray)
            //if (e > elem)  // compiler error
                ++count;
        return count;
    }

    //This is correct
    public static <T extends Comparable<T>> int countGreaterThan(T[] anArray, T elem) {
        int count = 0;
        for (T e : anArray)
            if (e.compareTo(elem) > 0)
                ++count;
        return count;
    }


    //This is correct
    public static <T extends MyComparable<T>> int myCountGreaterThan(T[] anArray, T elem) {
        int count = 0;
        for (T e : anArray)
            if (e.compareTo(elem) > 0)
                ++count;
        return count;
    }

    //MyComparable specifically says that if two elements are not equal, the second being compared to will assumed
    //to be less than the first - can be useful in certain circumstances
    class MyComparable<T> implements Comparable<T> {

        @Override
        public int compareTo(T t) {
            return this.equals(t) ? 0 : -1;
        }
    }
}
