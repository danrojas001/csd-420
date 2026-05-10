//Dan Rojas
//Mod 8
//03-May-26

//use three threads to output three types of characters to a text area for display.
//In the first thread, you are to output random letter characters such as a, b, c, d …
//In the second thread, you are to output random number digits such as 0, 1, 2, 3, 4, 5, 6, 7, 8, 9.
//In the third thread, you are to output random characters such as !, @, #, $, %, &, *
//Display a minimum of 10,000 for each of the three sets.
//Lastly, do not display all data for each thread together.  Each character is to be displayed as each one is generated

import java.util.Random;

public class DanThreeThreads {
    public static void main(String[] args) throws InterruptedException {
        DanThreeThreads danThreeThreads = new DanThreeThreads();
        danThreeThreads.makeThreeThreads();
        danThreeThreads.runThreeThreads();
    }

    int count = 10000;
    StringBuffer sb = new StringBuffer();
    Thread t1;
    Thread t2;
    Thread t3;

    // Create three threads and assign unique logic to each using lambdas
    public void makeThreeThreads() {
        t1 = new Thread(() -> {
            String letters = "abcdefghijklmnopqrstuvwxyz";
            Random rand = new Random();
            for (int i = 0; i < count; i++) {
                sb.append(letters.charAt(rand.nextInt(letters.length())));
            }
        });

        t2 = new Thread(() -> {
            String numbers = "1234567890";
            Random rand = new Random();
            for (int i = 0; i < count; i++) {
                sb.append(numbers.charAt(rand.nextInt(numbers.length())));
            }
        });

        t3 = new Thread(() -> {
            String characters = "!@#$%^&*()";
            Random rand = new Random();
            for (int i = 0; i < count; i++) {
                sb.append(characters.charAt(rand.nextInt(characters.length())));
            }
        });
    }

    public void runThreeThreads() throws InterruptedException {
        // Start the threads
        t1.start();
        t2.start();
        t3.start();

        // Main thread waits for all before being allowed to exit
        t1.join();
        t2.join();
        t3.join();

        System.out.println();
        System.out.println(sb.toString());
        System.out.println();
        System.out.println("Total length of above string is: " + sb.length());
        System.out.println("Count of 30000 means all strings appended their content to the StringBuffer.");
    }
}
