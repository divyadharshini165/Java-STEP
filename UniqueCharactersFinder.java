import java.util.Scanner;

public class UniqueCharactersFinder {

    // a. Method to find length without using length()
    public static int getLength(String text) {
        int count = 0;
        try {
            while (true) {
                text.charAt(count); // Access character until exception occurs
                count++;
            }
        } catch (StringIndexOutOfBoundsException e) {
            // When out of range, loop stops
        }
        return count;
    }

    // b. Method to find unique characters using charAt()
    public static char[] findUniqueCharacters(String text) {
        int length = getLength(text);
        char[] temp = new char[length]; // Temp array to store possible uniques
        int uniqueCount = 0;

        // Outer loop — each character in text
        for (int i = 0; i < length; i++) {
            char currentChar = text.charAt(i);
            boolean isUnique = true;

            // Inner loop — check if it already appeared
            for (int j = 0; j < i; j++) {
                if (text.charAt(j) == currentChar) {
                    isUnique = false;
                    break;
                }
            }

            // If unique, store in temp
            if (isUnique) {
                temp[uniqueCount] = currentChar;
                uniqueCount++;
            }
        }

        // Create final array with exact size
        char[] result = new char[uniqueCount];
        for (int i = 0; i < uniqueCount; i++) {
            result[i] = temp[i];
        }

        return result;
    }

    // c. Main method
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        System.out.print("Enter a string: ");
        String input = sc.nextLine();

        char[] uniqueChars = findUniqueCharacters(input);

        System.out.print("Unique characters: ");
        for (char ch : uniqueChars) {
            System.out.print(ch + " ");
        }

        sc.close();
    }
}
