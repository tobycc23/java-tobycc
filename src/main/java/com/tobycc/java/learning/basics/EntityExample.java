package com.tobycc.java.learning.basics;

import javax.ws.rs.client.Entity;
import javax.ws.rs.core.MediaType;

/**
 * Created by toby.christey-clover on 12/04/2018.
 */
public class EntityExample {

    public static void main(String[] args) {

        Entity<String> e = Entity.entity("<hello/>", MediaType.APPLICATION_XML);

        System.out.println(e.getEntity());
    }
}
