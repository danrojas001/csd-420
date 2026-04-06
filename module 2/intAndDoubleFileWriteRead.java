//Dan Rojas
//05 Apr 26
//mod 2.2

//Write a program that stores:
//An array of five random integers.
//An array of five random double values.
//Write the data in a file titled [yourname] datafile.dat.
//If there is no file, the file will be created.
//If there is a file, the data will be appended.
//Write a second program that will read the file and display the data.

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Random;
import java.util.Scanner;

public class intAndDoubleFileWriteRead {

    // First program for storing arrays to file
    static class intAndDoubleWriter {
        public static void writeToFile() {
            int[] randFiveInt = new int[5];
            double[] randFiveDouble = new double[5];
            Random rand = new Random();

            // Fill array with 5 random int values
            for (int i = 0; i < randFiveInt.length; i++) {
                randFiveInt[i] = rand.nextInt(100) + 1;
            }

            // Fill array with 5 random double values
            for (int i = 0; i < randFiveDouble.length; i++) {
                randFiveDouble[i] = rand.nextDouble();
            }

            // Try-with-resources to automatically close the file at then end of the block
            try (PrintWriter pw = new PrintWriter(new FileOutputStream("rojasd-data.dat", true))) {
                for (int i : randFiveInt) {
                    pw.println(i);
                }
                for (double d : randFiveDouble) {
                    pw.println(d);
                }
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }

    }

    // Second program for reading from file
    static class intAndDoubleReader {
        public static void readFromFile() {
            // Try-with-resources to automatically close the file at then end of the block
            try (Scanner sc = new Scanner(new File("rojasd-data.dat"))) {
                while (sc.hasNextLine()) {
                    String line = sc.nextLine();
                    System.out.println(line);
                }
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
    }
}

