/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.tobycc.java.learning.basics;

// File Name PracticeServer.java
import java.net.*;
import java.io.*;

public class PracticeServer extends Thread {
   private ServerSocket serverSocket;
   
   public PracticeServer(int port) throws IOException {
      serverSocket = new ServerSocket(port);
      serverSocket.setSoTimeout(100000);
   }

   public void run() {
      while(true) {
         try {
            System.out.println("Waiting for client on port " + 
               serverSocket.getLocalPort() + "...");
            //Below is the most important line - waiting to accept connection
            Socket server = serverSocket.accept();
            
            System.out.println("Just connected to " + server.getRemoteSocketAddress());
            
            DataInputStream in = new DataInputStream(server.getInputStream());
            System.out.println(in.readUTF());
            
            DataOutputStream out = new DataOutputStream(server.getOutputStream());
            out.writeUTF("Thank you for connecting to " + server.getLocalSocketAddress()
               + "\nGoodbye!");
            
            server.close();
         }catch(SocketTimeoutException s) {
            System.out.println("Socket timed out!");
            break;
         }catch(IOException e) {
            e.printStackTrace();
            break;
         }
      }
   }
   
   public static void main(String [] args) {
      int port = Integer.parseInt(args[0]);
      try {
         Thread t = new PracticeServer(port);
         t.start();
      }catch(IOException e) {
         e.printStackTrace();
      }
   }
}
