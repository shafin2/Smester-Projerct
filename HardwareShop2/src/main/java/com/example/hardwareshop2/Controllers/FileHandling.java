package com.example.hardwareshop2.Controllers;


// Importing input output classes
import java.io.*;
// Importing utility classes
import java.util.*;

// Class 1
// helper class
class MyObjectOutputStream extends ObjectOutputStream {

    // Constructor of this class
    // 1. Default
    MyObjectOutputStream() throws IOException
    {

        // Super keyword refers to parent class instance
        super();
    }

    // Constructor of this class
    // 1. Parameterized constructor
    MyObjectOutputStream(OutputStream o) throws IOException
    {
        super(o);
    }

    // Method of this class
    public void writeStreamHeader() throws IOException
    {
        return;
    }
}

public class FileHandling {

//    private static File f = new File("BankAccountt.txt");

    public static <T> ArrayList<T> readFile(File f)
    {
        ArrayList<T> arr=new ArrayList<>();
        boolean status = false;
        try {
            f.createNewFile();
        }
        catch (Exception e) {
        }

        if (f.length() != 0) {
            try {
                FileInputStream fis = null;
                fis = new FileInputStream(f);
                ObjectInputStream ois = new ObjectInputStream(fis);
                T c = null;
                while (fis.available() != 0) {
                    c = (T) ois.readObject();
                    arr.add(c);
                }
                ois.close();
                fis.close();
                status = true;
            }
            catch (Exception e) {
                System.out.println("Error Occurred" + e);
                e.printStackTrace();
            }
        }
        return arr;
    }

    // Method 2
    // To add a new customer
    public static <T> boolean AddNewTool(File f,T c,Boolean val)
    {
        boolean status = false;
        if (c != null) {
            try {
                FileOutputStream fos = null;
                fos = new FileOutputStream(f, val);
                if (f.length() == 0) {
                    ObjectOutputStream oos = new ObjectOutputStream(fos);
                    oos.writeObject(c);
                    oos.close();
                }
                else {
                    MyObjectOutputStream oos = null;
                    oos = new MyObjectOutputStream(fos);
                    oos.writeObject(c);
                    oos.close();
                }
                fos.close();
            }
            catch (Exception e) {
                System.out.println("Error Occurred" + e);
            }

            // Change the flag status
            status = true;
        }

        return status;
    }
}
