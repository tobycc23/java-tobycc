package com.tobycc.java.learning.io;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;

/**
 * Created by toby.christey-clover on 30/11/2017.
 */
public class IOLearning {
/*
Byte Streams handle I/O of raw binary data.
Character Streams handle I/O of character data, automatically handling translation to and from the local character set.
Buffered Streams optimize input and output by reducing the number of calls to the native API.
Scanning and Formatting allows a program to read and write formatted text.
I/O from the Command Line describes the Standard Streams and the Console object.
Data Streams handle binary I/O of primitive data type and String values.
Object Streams handle binary I/O of objects.*/

    public static void main(String args[]) {

        FileInputStream in = null;
        FileOutputStream out = null;

        try {
            in = new FileInputStream("xanadu.txt");
            out = new FileOutputStream("outagain.txt");
            int c;

            while ((c = in.read()) != -1) {
                out.write(c);
            }
        } catch(Exception e) {
            e.getMessage();
        } finally {
            try {
                if (in != null) {
                    in.close();
                }
                if (out != null) {
                    out.close();
                }
            }
            catch(Exception e) {
                e.getMessage();
            }
        }
    }

}

