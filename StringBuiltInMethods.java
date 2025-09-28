public class StringBuiltInMethods {
    public static void main(String[] args) {
        String sampleText = " Java Programming is Fun and Challenging! ";

        // 1. Display original string length including spaces
        System.out.println("1. Original length (with spaces): " + sampleText.length());

        // 2. Remove leading and trailing spaces, show new length
        String trimmedText = sampleText.trim();
        System.out.println("2. Trimmed length (no leading/trailing spaces): " + trimmedText.length());

        // 3. Find and display the character at index 5
        System.out.println("3. Character at index 5: " + sampleText.charAt(5));

        // 4. Extract substring "Programming" from the text
        String programming = trimmedText.substring(5, 16);
        System.out.println("4. Substring 'Programming': " + programming);

        // 5. Find the index of the word "Fun"
        int funIndex = trimmedText.indexOf("Fun");
        System.out.println("5. Index of 'Fun': " + funIndex);

        // 6. Check if the string contains "Java" (case-sensitive)
        boolean containsJava = trimmedText.contains("Java");
        System.out.println("6. Contains 'Java'? " + containsJava);

        // 7. Check if the string starts with "Java" (after trimming)
        boolean startsWithJava = trimmedText.startsWith("Java");
        System.out.println("7. Starts with 'Java'? " + startsWithJava);

        // 8. Check if the string ends with an exclamation mark
        boolean endsWithExclamation = trimmedText.endsWith("!");
        System.out.println("8. Ends with '!': " + endsWithExclamation);

        // 9. Convert the entire string to uppercase
        System.out.println("9. Uppercase: " + trimmedText.toUpperCase());

        // 10. Convert the entire string to lowercase
        System.out.println("10. Lowercase: " + trimmedText.toLowerCase());

        // Count vowels
        int vowelCount = countVowels(trimmedText);
        System.out.println("11. Number of vowels: " + vowelCount);

        // Find all occurrences of a character
        System.out.print("12. Occurrences of letter 'a': ");
        findAllOccurrences(trimmedText, 'a');
    }

    // Method to count vowels in a string
    public static int countVowels(String text) {
        int count = 0;
        text = text.toLowerCase();
        for (int i = 0; i < text.length(); i++) {
            char ch = text.charAt(i);
            if (ch == 'a' || ch == 'e' || ch == 'i' || ch == 'o' || ch == 'u') {
                count++;
            }
        }
        return count;
    }

    // Method to find all positions of a character
    public static void findAllOccurrences(String text, char target) {
        boolean found = false;
        for (int i = 0; i < text.length(); i++) {
            if (text.charAt(i) == target) {
                System.out.print(i + " ");
                found = true;
            }
        }
        if (!found) {
            System.out.print("No occurrences found.");
        }
        System.out.println();
    }
}
