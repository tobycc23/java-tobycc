package com.tobycc.java.learning.deployment;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLClassLoader;

/**
 * Created by toby.christey-clover on 29/01/2018.
 */
public class DeploymentLearning {

    //Using JarClassLoader (or URLClassLoader) we can find and invoke the main class of another jar
    public static void loadingRunningAnotherJar() throws IOException, ClassNotFoundException,
            NoSuchMethodException, InvocationTargetException {

        JarClassLoader jarClassLoader = new JarClassLoader(new URL("someJarURL"));

        String mainClass = jarClassLoader.getMainClassName();
        if(mainClass==null) {
            throw new IOException();
        }

        jarClassLoader.invokeClass(mainClass, null);
    }

    public static void main(String[] args) {
        try {
            loadingRunningAnotherJar();
        }
        catch(IOException|ClassNotFoundException|NoSuchMethodException|InvocationTargetException e) {
            e.getMessage();
        }
    }
}
