package com.tobycc.java.learning.rmi;

/**
 * Created by toby.christey-clover on 20/04/2018.
 *
 * SERVER SIDE RMI
 */

//RMI uses the Java object serialization mechanism to transport objects by value between Java virtual machines.
//For an object to be considered serializable, its class must implement the java.io.Serializable marker interface.
//Therefore, classes that implement the Task interface must also implement Serializable, as must the classes of objects
//used for task results.

public interface Task<T> {
    T execute();
}