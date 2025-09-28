import java.util.Scanner;

public class CharFrequencyWithUnique {

    // a. Method to find unique characters using charAt() and nested loops
    public static char[] uniqueCharacters(String text) {
        int length = text.length();
        char[] temp = new char[length];
        int uniqueCount = 0;

        // Outer loop → pick each character
        for (int i = 0; i < length; i++) {
            char currentChar = text.charAt(i);
            boolean isUnique = true;

            // Inner loop → check if appeared before
            for (int j = 0; j < i; j++) {
                if (text.charAt(j) == currentChar) {
                    isUnique = false;
                    break;
                }
            }

            // Store only the first occurrence
            if (isUnique) {
                temp[uniqueCount] = currentChar;
                uniqueCount++;
            }
        }

        // Copy only unique chars into new array
        char[] result = new char[uniqueCount];
        for (int i = 0; i < uniqueCount; i++) {
            result[i] = temp[i];
        }
        return result;
    }

    // b. Method to find frequency using unique characters
    public static String[][] frequencyWithUnique(String text) {
        int[] freq = new int[256]; // ASCII frequency array

        // Step i & ii: Count frequency for all chars in text
        for (int i = 0; i < text.length(); i++) {
            char ch = text.charAt(i);
            freq[ch]++;
        }

        // Step iii: Get unique characters
        char[] uniques = uniqueCharacters(text);

        // Step iv: Create 2D String array for char + frequency
        String[][] result = new String[uniques.length][2];

        // Step v: Store characters and their frequencies
        for (int i = 0; i < uniques.length; i++) {
            result[i][0] = String.valueOf(uniques[i]);  // character
            result[i][1] = String.valueOf(freq[uniques[i]]); // frequency
        }

        return result;
    }

    // c. Main method
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        System.out.print("Enter a string: ");
        String input = sc.nextLine();

        String[][] freqData = frequencyWithUnique(input);

        System.out.println("Character Frequencies:");
        for (String[] row : freqData) {
            System.out.println("'" + row[0] + "' : " + row[1]);
        }

        sc.close();
    }
}
