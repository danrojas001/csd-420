// Dan Rojas
// Mod 4.2
// 09-Apr-26

/*
In the case of the 50k LinkedList, the method using the iterator was about 340x faster than the method using get
(index) and about 46,000x faster with the 500k LinkedList. These times fluctuated and could have been improved by
warming up the JVM to allow it to pre-optimize code paths, though there would likely still be a large gap in the
traversal speeds between iterator and get(index) and the time to complete the 500k traversal could have gotten
ridiculous. The Iterator maintains a reference to the current node while get(index) must start from the first node
each time leading to far longer traversal times.
 */

// comparison between interator and get(index) on a LinkedList. Added loop counter to test that both methods are
// looping through the same amount of times.

import java.util.LinkedList;

public class IteratorVsGet {
    public static void main() {

        LinkedList<Integer> shortList = new LinkedList<>();
        for (int i = 0; i < 50000; i++) {
            shortList.add(i);
        }

        LinkedList<Integer> longList = new LinkedList<>();
        for (int i = 0; i < 500000; i++) {
            longList.add(i);
        }

        System.out.println("Iterator with 50k:");
        useIterator(shortList);
        System.out.println("get(index) with 50k:");
        useGet(shortList);
        System.out.println("Iterator with 500k:");
        useIterator(longList);
        System.out.println("get(index) with 500k:");
        useGet(longList);
    }


    public static void useIterator(LinkedList<Integer> list) {
        int testSteps = 0;

        long start = System.nanoTime();
        for (Integer _ : list) {
            testSteps += 1; // Added to verify iteration count was as expected
        }
        long end = System.nanoTime();

        System.out.println(testSteps + " loops");
        System.out.println("Time spent: " + (end - start) + " ns");
        System.out.println("-----------------");
    }

    public static void useGet(LinkedList<Integer> list) {
        int testSteps = 0;

        long start = System.nanoTime();
        for (Integer num = 0; num < list.size(); num++) {
            testSteps += 1; // Added to verify iteration count was as expected
            list.get(num);
        }
        long end = System.nanoTime();

        System.out.println(testSteps + " loops");
        System.out.println("Time spent: " + (end - start) + " ns");
        System.out.println("-----------------");
    }
}