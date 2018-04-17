package com.tobycc.java.learning.io;

import java.io.*;
import java.util.Arrays;

/**
 * Created by toby.christey-clover on 30/11/2017.
 */
/*
Byte Streams handle I/O of raw binary data.
Character Streams handle I/O of character data, automatically handling translation to and from the local character set.
Buffered Streams optimize input and output by reducing the number of calls to the native API.
Scanning and Formatting allows a program to read and write formatted text.
I/O from the Command Line describes the Standard Streams and the Console object.
Data Streams handle binary I/O of primitive data type and String values.
Object Streams handle binary I/O of objects.*/
public class IOLearning {

    /** Example 1.1
     *
     * @author Toby
     */
    class CopyFile {
        public void main(String args[]) throws IOException {
            //This is for Java byte streams
            FileInputStream in = null;
            FileOutputStream out = null;
            try {
                in = new FileInputStream("input.txt");
                out = new FileOutputStream("output.txt");
                int c;
                while ((c = in.read()) != -1) {out.write(c);}
            }
            finally {
                if (in != null) {in.close();}
                if (out != null) {out.close();}
            }
        }
    }


    /** Example 1.2 InputStreamReader to read standard input stream
     * until the user types a "q".
     *
     * @author Toby
     */
    class ReadConsole {
        public void main(String args[]) throws IOException {
            InputStreamReader cin = null;
            try {
                cin = new InputStreamReader(System.in);
                System.out.println("Enter characters, 'q' to quit.");
                char c;
                do {
                    c = (char) cin.read();
                    System.out.print(c);
                }
                while(c != 'q');
            }
            finally {
                if (cin != null) {cin.close();}
            }
        }
    }


    /** Example 1.3
     *
     * @author Toby
     */
    class CopyLines {
        public void main(String[] args) throws IOException {
            //Character I/O usually occurs in bigger units than single characters.
            // One common unit is the line, terminated by '\r' or '\n' or both.
            BufferedReader inputStream = null;
            PrintWriter outputStream = null;

            try {
                inputStream = new BufferedReader(new FileReader("xanadu.txt"));
                outputStream = new PrintWriter(new FileWriter("characteroutput.txt"));

                String l;
                while ((l = inputStream.readLine()) != null) {
                    outputStream.println(l);
                }
            } finally {
                if (inputStream != null) {
                    inputStream.close();
                }
                if (outputStream != null) {
                    outputStream.close();
                }
            }
        }
    }


    /** Example 1.4
     *
     * @author Toby
     */
    class Password {

        public void main (String args[]) throws IOException {

            Console c = System.console();
            if (c == null) {
                System.err.println("No console.");
                System.exit(1);
            }

            String login = c.readLine("Enter your login: ");
            char [] oldPassword = c.readPassword("Enter your old password: ");

            if (verify(login, oldPassword)) {
                boolean noMatch;
                do {
                    char [] newPassword1 = c.readPassword("Enter your new password: ");
                    char [] newPassword2 = c.readPassword("Enter new password again: ");
                    noMatch = ! Arrays.equals(newPassword1, newPassword2);
                    if (noMatch) {
                        c.format("Passwords don't match. Try again.%n");
                    } else {
                        change(login, newPassword1);
                        c.format("Password for %s changed.%n", login);
                    }
                    //Filling these arrays with empty chars means there can be absolutely
                    //no way the password data can be stolen post checking
                    Arrays.fill(newPassword1, ' ');
                    Arrays.fill(newPassword2, ' ');
                } while (noMatch);
            }

            Arrays.fill(oldPassword, ' ');
        }

        // Dummy change method.
        public boolean verify(String login, char[] password) {
            // This method always returns
            // true in this example.
            // Modify this method to verify
            // password according to your rules.
            return true;
        }

        // Dummy change method.
        public void change(String login, char[] password) {
            // Modify this method to change
            // password according to your rules.
        }
    }
}

