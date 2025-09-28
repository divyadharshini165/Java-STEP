import java.util.Scanner;

public class CharFrequencyNestedLoops {

    // a. Method to find frequency of characters using nested loops
    public static String[] findFrequency(String text) {
        char[] chars = text.toCharArray(); // i. Store characters
        int[] freq = new int[chars.length]; // Frequency array

        // ii. Nested loops to find frequency
        for (int i = 0; i < chars.length; i++) {
            freq[i] = 1; // Initialize frequency

            if (chars[i] == '0') {
                continue; // Skip already counted
            }

            for (int j = i + 1; j < chars.length; j++) {
                if (chars[i] == chars[j]) {
                    freq[i]++;
                    chars[j] = '0'; // Mark as counted
                }
            }
        }

        // iii. Store result in String[]
        String[] result = new String[chars.length];
        int index = 0;
        for (int i = 0; i < chars.length; i++) {
            if (chars[i] != '0') {
                result[index] = "'" + chars[i] + "' : " + freq[i];
                index++;
            }
        }

        // Trim result to exact size
        String[] trimmed = new String[index];
        for (int i = 0; i < index; i++) {
            trimmed[i] = result[i];
        }

        return trimmed;
    }

    // b. Main method
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        System.out.print("Enter a string: ");
        String input = sc.nextLine();

        String[] freqData = findFrequency(input);

        System.out.println("Character Frequencies:");
        for (String entry : freqData) {
            System.out.println(entry);
        }

        sc.close();
    }
}
