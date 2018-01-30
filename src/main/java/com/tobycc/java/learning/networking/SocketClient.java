package com.tobycc.java.learning.networking;

import java.io.*;
import java.net.*;

public class SocketClient {

    public static void main(String[] args) throws IOException {
        /*if (args.length != 2) {
            System.err.println(
                    "Usage: java SocketClient <host name> <port number>");
            System.exit(1);
        }*/

        InetAddress hostName;
        int portNumber;
        if (args.length == 2) {
            hostName = InetAddress.getByName(args[0]);
            portNumber = Integer.parseInt(args[1]);
        } else {
            hostName = InetAddress.getLoopbackAddress();
            portNumber = 26001;
        }


        //The client socket code is below, situated here in try-with-resources for ease
        try (Socket socketSocket = new Socket(hostName, portNumber);

             PrintWriter out = new PrintWriter(socketSocket.getOutputStream(), true);
             BufferedReader in = new BufferedReader(new InputStreamReader(socketSocket.getInputStream()));
             BufferedReader stdIn = new BufferedReader(new InputStreamReader(System.in)))
        {
            String userInput;
            while ((userInput = stdIn.readLine()) != null) {
                out.println(userInput);
                System.out.println("echo: " + in.readLine());
            }
        } catch (UnknownHostException e) {
            System.err.println("Don't know about host " + hostName);
            System.exit(1);
        } catch (IOException e) {
            System.err.println("Couldn't get I/O for the connection to " +
                    hostName);
            System.exit(1);
        }
    }
}