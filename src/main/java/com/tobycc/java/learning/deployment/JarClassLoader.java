package com.tobycc.java.learning.deployment;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.net.JarURLConnection;
import java.net.URL;
import java.net.URLClassLoader;
import java.util.jar.Attributes;

/**
 * Created by toby.christey-clover on 29/01/2018.
 */
public class JarClassLoader extends URLClassLoader {

    private URL url;

    public JarClassLoader(URL url) {
        super(new URL[]{url});
        this.url = url;
    }

    //Need a way to determine which class in the JAR file is the application's entry point
    public String getMainClassName() throws IOException {
        //Exclamation separator indicates the URL refers to an entire JAR file
        URL u = new URL("jar", "", url + "!/");
        JarURLConnection uc = (JarURLConnection)u.openConnection();

        //The rest of this uses the manifest file, matching MAIN_CLASS to Main-Class:someClass pair
        Attributes attr = uc.getMainAttributes();
        return attr != null
                ? attr.getValue(Attributes.Name.MAIN_CLASS)
                : null;
    }

    //Need a way to invoke the main class of this separate JAR
    public void invokeClass(String name, String[] args)
            throws ClassNotFoundException,
            NoSuchMethodException,
            InvocationTargetException
    {
        //Inherited from java.lang.ClassLoader
        Class c = loadClass(name);
        //Reflection API used for the following
        Method m = c.getMethod("main", args.getClass());
        m.setAccessible(true);
        int mods = m.getModifiers();
        if (m.getReturnType() != void.class || !Modifier.isStatic(mods) ||
                !Modifier.isPublic(mods)) {
            throw new NoSuchMethodException("main");
        }
        try {
            m.invoke(null, new Object[] { args });
        } catch (IllegalAccessException e) {
            // This should not happen, as we have disabled access checks
        }
    }

}
