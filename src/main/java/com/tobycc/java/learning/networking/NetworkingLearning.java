package com.tobycc.java.learning.networking;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.*;

/**
 * Created by toby.christey-clover on 29/01/2018.
 */
public class NetworkingLearning {

    public static void definingURL() {
        //"protocol://hostname[:port]/filename"
        try {
            URL myURL = new URL("http://example.com/pages/");
            URL page1URL = new URL(myURL, "page1.html");
            URL splitURL = new URL("http", "example.com", "/pages/page1.html");

            //Remember, space in a URL given by special characters %20
            URL spaceUrl = new URL("http://example.com/hello%20world");
            //The above can be cumbersome if you don't know certain characters, better to do the following
            URI uri = new URI("http", "example.com", "/hello world/", "");
            URL url = uri.toURL();


            //Different parts of the URL
            URL aURL = new URL("http://example.com:80/docs/books/tutorial"
                    + "/index.html?name=networking#DOWNLOADING");
            System.out.println("protocol = " + aURL.getProtocol());
            System.out.println("authority = " + aURL.getAuthority());
            System.out.println("host = " + aURL.getHost());
            System.out.println("port = " + aURL.getPort());
            System.out.println("path = " + aURL.getPath());
            System.out.println("query = " + aURL.getQuery());
            System.out.println("filename = " + aURL.getFile());
            System.out.println("ref = " + aURL.getRef());

        } catch (MalformedURLException | URISyntaxException e) {
            System.out.println(e);
            e.printStackTrace();
        }
    }


    public static void urlReader() throws Exception {
        URL oracle = new URL("http://www.oracle.com/");
        BufferedReader in = new BufferedReader(new InputStreamReader(oracle.openStream()));
        //or can replace above line with something more specific
        URLConnection yc = oracle.openConnection();
        BufferedReader in2 = new BufferedReader(new InputStreamReader(yc.getInputStream()));

        String inputLine;
        while ((inputLine = in.readLine()) != null) {
            System.out.println(inputLine);
        }
        in.close();
    }

    //Many HTML pages contain forms — text fields and other GUI objects that let you enter data to send to the server.
    //Many use HTTP Post method to send data to the server, thus writing to a URL often called posting to a URL
    public static void urlWriter() throws Exception {
        String stringToReverse = URLEncoder.encode("Reverse this!", "UTF-8");

        URL url = new URL("/////////////////////////////////");
        URLConnection connection = url.openConnection();
        connection.setDoOutput(true);

        OutputStreamWriter out = new OutputStreamWriter(connection.getOutputStream());
        out.write("string=" + stringToReverse);
        out.close();

        Thread.sleep(5000);

        BufferedReader in = new BufferedReader(new InputStreamReader(connection.getInputStream()));
        String decodedString;
        while ((decodedString = in.readLine()) != null) {
            System.out.println(decodedString);
        }
        in.close();
    }


    public static void main(String[] args) {
        try {
            urlWriter();
        } catch(Exception e) {
            e.printStackTrace();
        }
    }
}
