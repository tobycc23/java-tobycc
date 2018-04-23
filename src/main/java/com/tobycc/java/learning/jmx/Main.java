package com.tobycc.java.learning.jmx;

/*
 * Main.java - main class for the Standard MBean and QueueSampler MXBean example.
 * Create the Standard MBean and QueueSampler MXBean, register them in the platform
 * MBean server, then wait forever (or until the program is interrupted).
 */

import javax.management.MBeanServer;
import javax.management.ObjectName;
import java.lang.management.ManagementFactory;
import java.util.Queue;
import java.util.concurrent.ArrayBlockingQueue;

public class Main {
    public static void main(String[] args) throws Exception {
        // Get the Platform MBean Server
        MBeanServer mbs = ManagementFactory.getPlatformMBeanServer();

        // Standard MBean stuff
        // Construct the ObjectName for the Standard MBean we will register
        ObjectName mbeanName = new ObjectName("com.tobycc.java.learning.jmx:type=Standard");
        // Create the Standard World MBean
        Standard mbean = new Standard();
        // Register the Standard World MBean
        mbs.registerMBean(mbean, mbeanName);

        // MXBean stuff
        ObjectName mxbeanName = new ObjectName("com.example:type=QueueSampler");
        Queue<String> queue = new ArrayBlockingQueue<String>(10);
        queue.add("Request-1");
        queue.add("Request-2");
        queue.add("Request-3");
        QueueSampler mxbean = new QueueSampler(queue);
        mbs.registerMBean(mxbean, mxbeanName);

        // Wait forever
        System.out.println("Waiting for incoming requests...");
        Thread.sleep(Long.MAX_VALUE);
    }
}