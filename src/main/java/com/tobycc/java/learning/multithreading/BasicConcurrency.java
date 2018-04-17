package com.tobycc.java.learning.multithreading;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

/**
 * Created by toby.christey-clover on 17/04/2018.
 */
public class BasicConcurrency {

    /** Example 1:1
     *
     * @author Toby
     */
    //CLASS IMPLEMENTS RUNNABLE
    class RunnableDemo implements Runnable {
        private String threadName;

        RunnableDemo( String name) {
            threadName = name;
            System.out.println("Creating " +  threadName );
        }

        public void run() {
            System.out.println("Running " +  threadName );
            try {
                for(int i = 4; i > 0; i--) {
                    System.out.println("Thread: " + threadName + ", " + i);
                    // Let the thread sleep for a while.
                    Thread.sleep(50);
                }
            }catch (InterruptedException e) {
                System.out.println("Thread " +  threadName + " interrupted.");
            }
            System.out.println("Thread " +  threadName + " exiting.");
        }
    }

    class TestThread {
        public void main(String args[]) {
            Thread R1 = new Thread(new RunnableDemo( "Thread-1")); R1.start();
            Thread R2 = new Thread(new RunnableDemo( "Thread-2")); R2.start();
        }
    }


    /** Example 1:2
     *
     * @author Toby
     */
    //CLASS EXTENDS THREAD
    class ThreadDemo extends Thread {
        private String threadName;

        ThreadDemo( String name) {
            threadName = name;
            System.out.println("Creating " +  threadName );
        }

        public void run() {
            System.out.println("Running " +  threadName );
            try {
                for(int i = 4; i > 0; i--) {
                    System.out.println("Thread: " + threadName + ", " + i);
                    // Let the thread sleep for a while.
                    Thread.sleep(50);
                }
            }catch (InterruptedException e) {
                System.out.println("Thread " +  threadName + " interrupted.");
            }
            System.out.println("Thread " +  threadName + " exiting.");
        }
    }

    class TestThread2 {
        public void main(String args[]) {
            ThreadDemo T1 = new ThreadDemo( "Thread-1"); T1.start();
            ThreadDemo T2 = new ThreadDemo( "Thread-2"); T2.start();
        }
    }


    /** Example 1:3
     *
     * @author Toby
     */
    class PrintDemo {
        public void printCount() {
            try {
                for(int i = 5; i > 0; i--) {
                    System.out.println("Counter   ---   "  + i );
                }
            }catch (Exception e) {
                System.out.println("Thread  interrupted.");
            }
        }
    }

    class ThreadDemo2 extends Thread {
        private String threadName;
        PrintDemo  PD;

        ThreadDemo2( String name,  PrintDemo pd) {
            threadName = name;
            PD = pd;
        }

        @Override
        public void run() {
            synchronized(PD) {
                PD.printCount();
            }
            System.out.println("Thread " +  threadName + " exiting.");
        }

        @Override
        public void start () {
            System.out.println("Starting " +  threadName );
        }
    }

    class TestThread3 {
        public void main(String args[]) {
            PrintDemo PD = new PrintDemo();
            ThreadDemo2 T1 = new ThreadDemo2( "Thread - 1 ", PD );
            ThreadDemo2 T2 = new ThreadDemo2( "Thread - 2 ", PD );
            T1.start();
            T2.start();

            // wait for threads to end
            try {
                T1.join();
                T2.join();
            }catch( Exception e) {
                System.out.println("Interrupted");
            }
        }
    }


    /** Example 1:4 Deadlocking
     *
     * @author Toby
     */
    class DeadlockThread {
        public Object lock1 = new Object();
        public Object lock2 = new Object();

        public void main(String args[]) {
            DeadlockDemo1 T1 = new DeadlockDemo1();
            DeadlockDemo2 T2 = new DeadlockDemo2();
            T1.start();
            T2.start();
        }

        private class DeadlockDemo1 extends Thread {
            public void run() {
                synchronized (lock1) {
                    System.out.println("Thread 1: Holding lock 1...");

                    try { Thread.sleep(10); }
                    catch (InterruptedException e) {}
                    System.out.println("Thread 1: Waiting for lock 2...");

                    synchronized (lock2) {
                        System.out.println("Thread 1: Holding lock 1 & 2...");
                    }
                }
            }
        }
        private class DeadlockDemo2 extends Thread {
            public void run() {
                synchronized (lock2) {
                    System.out.println("Thread 2: Holding lock 2...");

                    try { Thread.sleep(10); }
                    catch (InterruptedException e) {}
                    System.out.println("Thread 2: Waiting for lock 1...");

                    synchronized (lock1) {
                        System.out.println("Thread 2: Holding lock 1 & 2...");
                    }
                }
            }
        }
    }


    /** Example 1:5 Thread Pools
     *
     * @author Toby
     */
    class Processor implements Runnable {
        private int id;
        public Processor(int id) {
            this.id = id;
            System.out.println("Created: " + this.id);
        }

        public void run() {
            System.out.println("Starting: " + id);
            try {
                Thread.sleep(5000);
            } catch (InterruptedException ignored) {
            }
            System.out.println("Completed: " + id);
        }
    }

    class App {
        public void main(String[] args) {
            /**
             * Created 2 threads, and assign tasks (Processor(i).run) to the threads
             */
            ExecutorService executor = Executors.newFixedThreadPool(2);//2 Threads
            for (int i = 0; i < 7; i++) { // call the (Processor(i).run) 7 times with 2 threads
                executor.submit(new Processor(i));
            }
            //executor will wait for threads to finish before shutdown(); but will
            //continue with main thread below.
            executor.shutdown();
            System.out.println("All tasks submitted.");
            //This can be used to make sure we terminate if not done otherwise in a day
            try {
                executor.awaitTermination(1, TimeUnit.DAYS);
            } catch (InterruptedException ignored) {
            }
            System.out.println("All tasks completed.");
        }
    }
}
