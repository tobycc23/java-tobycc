package com.tobycc.java.learning.jmx;

import javax.management.AttributeChangeNotification;
import javax.management.Notification;
import javax.management.NotificationBroadcasterSupport;

/**
 * Created by toby.christey-clover on 18/04/2018.
 */
//Extends NBS for notification facilities
public class Standard extends NotificationBroadcasterSupport implements StandardMBean {
        public void sayHello() {
            System.out.println("hello, world");
        }

    public int add(int x, int y) {
        return x + y;
    }

    public String getName() {
        return this.name;
    }

    public int getCacheSize() {
        return this.cacheSize;
    }

    public synchronized void setCacheSize(int size) {
        int oldSize = this.cacheSize;
        this.cacheSize = size;

        System.out.println("Cache size now " + this.cacheSize);

        //Notification section...

        //The object name of the source of the notification, namely the Standard MBean, represented by this
        //sequence number
        //timestamp
        //content of notification message
        //name of changed attribute
        //type of changed attribute
        //old attribute value
        //new attribute value
        Notification n = new AttributeChangeNotification(this,
                sequenceNumber++, System.currentTimeMillis(),
                "CacheSize changed", "CacheSize", "int",
                oldSize, this.cacheSize);

        //Final part needed for notification...
        sendNotification(n);
    }

    private final String name = "Reginald";
    private int cacheSize = DEFAULT_CACHE_SIZE;
    private static final int DEFAULT_CACHE_SIZE = 200;

    //Sequence number for notifications
    private long sequenceNumber = 1;
}
