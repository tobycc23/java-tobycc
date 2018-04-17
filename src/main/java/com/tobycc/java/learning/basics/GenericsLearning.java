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


    /** Example 1.1
     *
     */
    class MaximumTest {
        // determines the largest of three Comparable objects
        public <T extends Comparable<T>> T maximum(T x, T y, T z) {
            T max = x;   // assume x is initially the largest
            if(y.compareTo(max) > 0) {
                max = y;   // y is the largest so far
            }
            if(z.compareTo(max) > 0) {
                max = z;   // z is the largest now
            }
            return max;   // returns the largest object
        }

        public  void main(String args[]) {
            System.out.printf("Max of %d, %d and %d is %d\n\n",
                    3, 4, 5, maximum( 3, 4, 5 ));

            System.out.printf("Max of %.1f,%.1f and %.1f is %.1f\n\n",
                    6.6, 8.8, 7.7, maximum( 6.6, 8.8, 7.7 ));

            System.out.printf("Max of %s, %s and %s is %s\n","pear",
                    "apple", "orange", maximum("pear", "apple", "orange"));
        }
    }
}
