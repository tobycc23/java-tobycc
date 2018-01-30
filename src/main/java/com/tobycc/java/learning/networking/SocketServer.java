package com.tobycc.java.learning.networking;

import java.net.*;
import java.io.*;

public class SocketServer {

    public static void main(String[] args) throws IOException {
        /*if (args.length != 1) {
            System.err.println("Usage: java SocketServer <port number>");
            System.exit(1);
        }*/

        int portNumber;
        if(args.length == 1) {
            portNumber = Integer.parseInt(args[0]);
        } else {
            portNumber = 26001;
        }

        //The server socket code is as below, situated here in try-with-resources for ease
        try (ServerSocket serverSocket = new ServerSocket(portNumber);
             Socket clientSocket = serverSocket.accept();

             PrintWriter out = new PrintWriter(clientSocket.getOutputStream(), true);
             BufferedReader in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream())))
        {
            String inputLine;
            while ((inputLine = in.readLine()) != null) {
                out.println(inputLine);
            }
        } catch (IOException e) {
            System.out.println("Exception caught when trying to listen on port "
                    + portNumber + " or listening for a connection");
            System.out.println(e.getMessage());
        }
    }
}