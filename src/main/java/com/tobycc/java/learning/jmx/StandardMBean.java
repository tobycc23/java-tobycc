package com.tobycc.java.learning.jmx;

/**
 * Created by toby.christey-clover on 18/04/2018.
 *
 * StandardMBean.java - MBean interface describing the management operations and
 * attributes for the Standard MBean. In this case there are two operations,
 * "sayHello" and "add", and two attributes, "Name" and "CacheSize".
 */
public interface StandardMBean {
    // operations
    public void sayHello();
    public int add(int x, int y);

    // attributes
    // a read-only attribute called Name of type String
    public String getName();
    // a read-write attribute called CacheSize of type int
    public int getCacheSize();
    public void setCacheSize(int size);
}