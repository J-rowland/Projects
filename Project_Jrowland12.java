/**
 * A program to process a text file: count words, find unique words,
 * sort them, and search for specific phrases.
 *
 * @author Jon Rowland
 */

 import java.io.*;
import java.util.*;

public class Project_Jrowland12 {
    /**
     * Main method to execute the program. Reads file name from command line,
     * validates file existence, and processes the file.
     *
     * @param args command-line arguments (expects a file name)
     */
    public static void main(String[] args) {
        if (args.length < 1) {
            System.out.println("File does not exist.");
            return;
        }

        String fileName = args[0];
        File file = new File(fileName);
        if (!file.exists()) {
            System.out.println("File does not exist.");
            return;
        }

        try {
            // Reading file
            BufferedReader reader = new BufferedReader(new FileReader(file));
            ArrayList<String> lines = new ArrayList<>();
            ArrayList<String> words = new ArrayList<>();

            String line;
            while ((line = reader.readLine()) != null) {
                lines.add(line);
                String[] splitWords = line.split("\\s+");
                for (String word : splitWords) {
                    words.add(word);
                }
            }
            reader.close();

            // Count and display word details
            System.out.println("Total number of words in file: " + words.size());

            ArrayList<String> uniqueWords = createUniqueList(words);
            System.out.println("Total number of unique words in file: " + uniqueWords.size());

            sortList(uniqueWords);
            System.out.println("Unique words of the input file in ascending order:");
            for (String word : uniqueWords) {
                System.out.println(word);
            }

            // Search phrases
            search(lines);

        } catch (IOException e) {
            System.out.println("An error occurred while reading the file.");
        }
    }

    /**
     * Creates a list of unique words from a list that may have duplicates.
     *
     * @param wordList a list containing duplicate words
     * @return a new list containing only unique words
     */
    public static ArrayList<String> createUniqueList(ArrayList<String> wordList) {
    ArrayList<String> uniqueWords = new ArrayList<>();
    for (String word : wordList) {
        String lowerCaseWord = word.toLowerCase(); // Convert to lowercase
        if (!uniqueWords.contains(lowerCaseWord)) {
            uniqueWords.add(lowerCaseWord);
        }
    }
    return uniqueWords;
    }

    /**
     * Sorts a list of words in ascending order using bubble sort.
     *
     * @param wordList the list of words to be sorted
     */
    public static void sortList(ArrayList<String> wordList) {
        for (int i = 0; i < wordList.size(); i++) {
            for (int j = 0; j < wordList.size() - 1; j++) {
                if (wordList.get(j).compareTo(wordList.get(j + 1)) > 0) {
                    String temp = wordList.get(j);
                    wordList.set(j, wordList.get(j + 1));
                    wordList.set(j + 1, temp);
                }
            }
        }
    }

    /**
     * Prompts the user to search for phrases in the file and displays their occurrences.
     * Continues until the user enters "EINPUT".
     *
     * @param lines the lines of text from the file
     */
    public static void search(ArrayList<String> lines) {
        Scanner sc = new Scanner(System.in);

        while (true) {
            System.out.print("Enter Search Pattern: ");
            String phrase = sc.nextLine();

            if (phrase.equals("EINPUT")) {
                System.out.println("Bye!");
                break;
            }

            boolean found = false;
            for (int i = 0; i < lines.size(); i++) {
                if (lines.get(i).contains(phrase)) {
                    found = true;
                    System.out.println("Line number " + (i + 1) + ": " + lines.get(i));
                }
            }

            if (!found) {
                System.out.println("No matches found.");
            }
        }
        sc.close();
    }
}
