// Dan Rojas
// Mod 4.2
// 18-Apr-26

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.TreeSet;

public class ReadAndSort {
    public static void displaySortedWords() {
        // TreeSet used for the sorting and removal of duplicates.
        TreeSet<String> sortedWords = sortAndRemoveDups();

        System.out.println("""
                
                -------------------
                | Ascending Order |
                -------------------""");
        for (String word : sortedWords) {
            System.out.println(word);
        }
        System.out.println("""
                
                --------------------
                | Descending Order |
                --------------------""");
        for (String word : sortedWords.descendingSet()) {
            System.out.println(word);
        }
    }

    private static TreeSet<String> sortAndRemoveDups() {
        TreeSet<String> sortedWords = new TreeSet<>();

        try {
            BufferedReader br = new BufferedReader(new FileReader("collection_of_words.txt"));
            String line;

            while ((line = br.readLine()) != null) {
                String[] words = line.split("[^a-zA-Z']+");

                for (String word : words) {
                    // Prevent empty strings elements being added from using split() .
                    if (!word.isEmpty()) {
                        // Prevent misordering due to lexicographic sort putting capitalized words first.
                        sortedWords.add(word.toLowerCase());
                    }
                }
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return sortedWords;
    }
}
