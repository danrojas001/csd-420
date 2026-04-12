// Dan Rojas
// Mod 3.2
// 08-Apr-26


import java.util.ArrayList;
import java.util.Random;

public class NoDupArrayListGen {

    public static void main(String[] args) {
        // Create original ArrayList of 50 random values.
        ArrayList<Integer> list = makeOriginalArrayList();
        System.out.println("Original ArrayList = " + list);

        // Create new Arraylist from original removing any duplicate values.
        ArrayList<Integer> newArrayList = removeDuplicates(list);
        System.out.println("Duplicates removed ArrayList = " + newArrayList);
    }

    static ArrayList<Integer> makeOriginalArrayList() {
        ArrayList<Integer> list = new ArrayList<Integer>();
        Random rand = new Random();

        for (int i = 0; i < 50; i++) {
            list.add(rand.nextInt(20) + 1);
        }
        return list;
    }

    public static <E> ArrayList<E> removeDuplicates(ArrayList<E> list) {
        ArrayList<E> noDupsArrList = new ArrayList<>();

        for (E item : list) {
            if (!noDupsArrList.contains(item)) {
                noDupsArrList.add(item);
            }
        }
        return noDupsArrList;
    }
}
