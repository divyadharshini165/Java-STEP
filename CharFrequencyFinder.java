import java.util.Scanner;

public class CharFrequencyFinder {

    // Method to find frequency of characters
    public static Object[][] findCharFrequency(String text) {
        int[] freq = new int[256]; // ASCII size

        // Step 1: Count frequency of each character
        for (int i = 0; i < text.length(); i++) {
            char ch = text.charAt(i);
            freq[ch]++;
        }

        // Step 2: Find number of unique characters
        int uniqueCount = 0;
        for (int i = 0; i < text.length(); i++) {
            if (freq[text.charAt(i)] > 0) {
                uniqueCount++;
                freq[text.charAt(i)] = -freq[text.charAt(i)]; 
                // Negative mark to avoid counting again
            }
        }

        // Reset frequency array (positive values again)
        for (int i = 0; i < 256; i++) {
            if (freq[i] < 0) {
                freq[i] = -freq[i];
            }
        }

        // Step 3: Create result array
        Object[][] result = new Object[uniqueCount][2];
        int index = 0;

        // Step 4: Store characters and their frequencies
        boolean[] stored = new boolean[256]; // To avoid duplicates
        for (int i = 0; i < text.length(); i++) {
            char ch = text.charAt(i);
            if (!stored[ch]) {
                result[index][0] = ch;
                result[index][1] = freq[ch];
                stored[ch] = true;
                index++;
            }
        }

        return result;
    }

    // Main method
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        System.out.print("Enter a string: ");
        String input = sc.nextLine();

        Object[][] freqData = findCharFrequency(input);

        System.out.println("Character Frequencies:");
        for (Object[] row : freqData) {
            System.out.println("'" + row[0] + "' : " + row[1]);
        }

        sc.close();
    }
}
