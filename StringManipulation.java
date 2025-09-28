import java.util.*;

public class StringManipulation {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        // Ask user for input
        System.out.println("Enter a sentence with mixed formatting:");
        String input = scanner.nextLine();

        // 1. trim() - Remove leading and trailing spaces
        String trimmed = input.trim();
        System.out.println("Trimmed: " + trimmed);

        // 2. replace() - Replace spaces with underscores
        String replacedSpaces = trimmed.replace(" ", "_");
        System.out.println("Spaces replaced with underscores: " + replacedSpaces);

        // 3. replaceAll() - Remove all digits using regex
        String noDigits = trimmed.replaceAll("\\d", "");
        System.out.println("Without digits: " + noDigits);

        // 4. split() - Split into words array
        String[] words = trimmed.split("\\s+");
        System.out.println("Split words: " + Arrays.toString(words));

        // 5. join() - Rejoin words with " | "
        String joined = String.join(" | ", words);
        System.out.println("Joined with '|': " + joined);

        // Additional processing
        String noPunctuation = removePunctuation(trimmed);
        System.out.println("Without punctuation: " + noPunctuation);

        String capitalized = capitalizeWords(trimmed);
        System.out.println("Capitalized words: " + capitalized);

        String reversedOrder = reverseWordOrder(trimmed);
        System.out.println("Reversed word order: " + reversedOrder);

        System.out.println("Word frequency:");
        countWordFrequency(trimmed);

        scanner.close();
    }

    // Method to remove punctuation
    public static String removePunctuation(String text) {
        return text.replaceAll("[\\p{Punct}]", "");
    }

    // Method to capitalize each word
    public static String capitalizeWords(String text) {
        String[] words = text.split("\\s+");
        StringBuilder sb = new StringBuilder();
        for (String word : words) {
            if (word.length() > 0) {
                sb.append(Character.toUpperCase(word.charAt(0)))
                  .append(word.substring(1).toLowerCase())
                  .append(" ");
            }
        }
        return sb.toString().trim();
    }

    // Method to reverse word order
    public static String reverseWordOrder(String text) {
        String[] words = text.split("\\s+");
        Collections.reverse(Arrays.asList(words));
        return String.join(" ", words);
    }

    // Method to count word frequency
    public static void countWordFrequency(String text) {
        String[] words = text.toLowerCase().replaceAll("[\\p{Punct}]", "").split("\\s+");
        Map<String, Integer> frequencyMap = new LinkedHashMap<>();
        for (String word : words) {
            frequencyMap.put(word, frequencyMap.getOrDefault(word, 0) + 1);
        }
        for (Map.Entry<String, Integer> entry : frequencyMap.entrySet()) {
            System.out.println(entry.getKey() + ": " + entry.getValue());
        }
    }
}
