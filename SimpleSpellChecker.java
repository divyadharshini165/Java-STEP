import java.util.Scanner;

public class SimpleSpellChecker {

    // (b) Split sentence into words without using split()
    public static String[] splitWords(String sentence) {
        sentence = sentence + " "; // add trailing space for last word
        int start = 0;
        int count = 0;

        // count words
        for (int i = 0; i < sentence.length(); i++) {
            char ch = sentence.charAt(i);
            if (ch == ' ' || ch == '.' || ch == ',' || ch == '?' || ch == '!') {
                if (i > start) count++;
                start = i + 1;
            }
        }

        String[] words = new String[count];
        start = 0;
        int index = 0;

        // extract words
        for (int i = 0; i < sentence.length(); i++) {
            char ch = sentence.charAt(i);
            if (ch == ' ' || ch == '.' || ch == ',' || ch == '?' || ch == '!') {
                if (i > start) {
                    words[index++] = sentence.substring(start, i);
                }
                start = i + 1;
            }
        }
        return words;
    }

    // (c) Calculate string distance
    public static int stringDistance(String w1, String w2) {
        int len1 = w1.length();
        int len2 = w2.length();
        int distance = 0;

        // Case 1: same length → count char differences
        if (len1 == len2) {
            for (int i = 0; i < len1; i++) {
                if (w1.charAt(i) != w2.charAt(i)) distance++;
            }
        } 
        // Case 2: different length → insertion/deletion distance
        else {
            distance = Math.abs(len1 - len2);
            int minLen = Math.min(len1, len2);
            for (int i = 0; i < minLen; i++) {
                if (w1.charAt(i) != w2.charAt(i)) distance++;
            }
        }
        return distance;
    }

    // (d) Find closest matching word from dictionary
    public static String findClosestWord(String word, String[] dictionary) {
        String suggestion = word; // default: itself
        int minDist = Integer.MAX_VALUE;

        for (String dictWord : dictionary) {
            int dist = stringDistance(word.toLowerCase(), dictWord.toLowerCase());
            if (dist < minDist) {
                minDist = dist;
                suggestion = dictWord;
            }
        }

        // Only suggest if reasonably close (≤2)
        if (minDist <= 2) {
            return suggestion;
        }
        return word; // no good suggestion
    }

    // (e) Display spell check results
    public static void displayResults(String[] words, String[] dictionary) {
        System.out.printf("%-15s %-15s %-10s %-15s\n", "Original", "Suggestion", "Distance", "Status");
        System.out.println("---------------------------------------------------------------");

        for (String word : words) {
            String suggestion = findClosestWord(word, dictionary);
            int distance = stringDistance(word.toLowerCase(), suggestion.toLowerCase());
            String status = (distance == 0) ? "Correct" : "Misspelled";
            System.out.printf("%-15s %-15s %-10d %-15s\n", word, suggestion, distance, status);
        }
    }

    // (f) Main function
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        // Example dictionary
        String[] dictionary = {"hello", "world", "java", "programming", "simple", "spell", "checker"};

        System.out.println("Enter a sentence:");
        String sentence = sc.nextLine();

        String[] words = splitWords(sentence);

        displayResults(words, dictionary);

        sc.close();
    }
}
