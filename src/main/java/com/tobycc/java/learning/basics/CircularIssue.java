package com.tobycc.java.learning.basics;

/**
 * Created by toby.christey-clover on 18/04/2018.
 */
public class CircularIssue {

    //Do not do circular referencing otherwise the order of these matters! Bad example shown here
    public int a = check1();
    public int b = check();
    public int c;

    /* different from if b defined before a.
     * To avoid this use Integer, then a null pointer exception will be thrown in this case.
     */


    public int check1() {
        return b+2;
    }

    public int check() {
        System.out.println(a);
        System.out.println(b);
        //Primitive int defaults to 0 not null!
        System.out.println(c);
        return a + 5;
    }


    public static void main(String[] args) {
        System.out.println(new CircularIssue().a);
    }
}
