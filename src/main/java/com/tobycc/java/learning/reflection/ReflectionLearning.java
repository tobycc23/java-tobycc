package com.tobycc.java.learning.reflection;

import java.util.HashSet;

/**
 * Created by toby.christey-clover on 20/04/2018.
 */
public class ReflectionLearning {

    //Returns string class
    Class c1 = "foo".getClass();
    //Returns HashSet class
    Class c2 = new HashSet<Integer>().getClass();
    Class c3 = HashSet.class;

    //If the type is available but there is no instance then it is possible to obtain a Class by appending ".class" to the name of the type.
    /** boolean b;
    Class c4 = b.getClass();   //compile-time error */
    Class c4 = boolean.class;  //correct

    //his cannot be used for primitive types. The syntax for names of array classes is described by Class.getName().
    Class c5 = Class.forName("com.tobycc.java.model.Person");


    //Class.getSuperclass() returns the super class for the given class.
    Class c6 = javax.swing.JButton.class.getSuperclass();
    //Class.getClasses() returns all the public classes, interfaces, and enums that are members of the class including inherited members.
    Class<?>[] c7 = Character.class.getClasses();



    public ReflectionLearning() throws ClassNotFoundException {
    }
}
