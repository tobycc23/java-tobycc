package com.tobycc.java.learning.springboot.webservice;

/**
 * Created by toby.christey-clover on 23/04/2018.
 */
public class Greeting {

    private final long id;
    private final String content;

    public Greeting(long id, String content) {
        this.id = id;
        this.content = content;
    }

    public long getId() {
        return id;
    }

    public String getContent() {
        return content;
    }
}