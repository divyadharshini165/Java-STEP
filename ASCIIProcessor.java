import java.util.Scanner;

public class ASCIIProcessor {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        // Ask user for a string
        System.out.print("Enter a string: ");
        String input = scanner.nextLine();

        System.out.println("\n--- Character Analysis ---");
        for (int i = 0; i < input.length(); i++) {
            char ch = input.charAt(i);
            int ascii = (int) ch;

            System.out.println("Character: '" + ch + "' | ASCII: " + ascii);
            String type = classifyCharacter(ch);
            System.out.println("Type: " + type);

            if (type.contains("Letter")) {
                char upper = Character.toUpperCase(ch);
                char lower = Character.toLowerCase(ch);
                System.out.println("Uppercase: '" + upper + "' (ASCII: " + (int) upper + ")");
                System.out.println("Lowercase: '" + lower + "' (ASCII: " + (int) lower + ")");
                System.out.println("Difference between upper & lower ASCII: " + Math.abs((int) upper - (int) lower));
            }
            System.out.println();
        }

        // ASCII Art (simple horizontal bar from ASCII values)
        System.out.println("--- ASCII Art ---");
        for (int ascii : stringToASCII(input)) {
            for (int j = 0; j < ascii % 10 + 1; j++) {
                System.out.print("*");
            }
            System.out.print(" ");
        }
        System.out.println("\n");

        // Caesar Cipher
        System.out.print("Enter Caesar cipher shift value: ");
        int shift = scanner.nextInt();
        scanner.nextLine();
        String encrypted = caesarCipher(input, shift);
        System.out.println("Encrypted Text: " + encrypted);
        System.out.println("Decrypted Text: " + caesarCipher(encrypted, -shift));

        // Display ASCII table for first 128 characters
        System.out.println("\n--- ASCII Table (32 to 126) ---");
        displayASCIITable(32, 126);

        scanner.close();
    }

    // Method to classify character type
    public static String classifyCharacter(char ch) {
        if (ch >= 'A' && ch <= 'Z') return "Uppercase Letter";
        else if (ch >= 'a' && ch <= 'z') return "Lowercase Letter";
        else if (ch >= '0' && ch <= '9') return "Digit";
        else return "Special Character";
    }

    // Method to toggle case
    public static char toggleCase(char ch) {
        if (ch >= 'A' && ch <= 'Z') {
            return (char) (ch + 32);
        } else if (ch >= 'a' && ch <= 'z') {
            return (char) (ch - 32);
        } else {
            return ch;
        }
    }

    // Caesar cipher
    public static String caesarCipher(String text, int shift) {
        StringBuilder result = new StringBuilder();
        for (char ch : text.toCharArray()) {
            if (Character.isLetter(ch)) {
                char base = Character.isUpperCase(ch) ? 'A' : 'a';
                result.append((char) ((ch - base + shift + 26) % 26 + base));
            } else {
                result.append(ch);
            }
        }
        return result.toString();
    }

    // Display ASCII table
    public static void displayASCIITable(int start, int end) {
        for (int i = start; i <= end; i++) {
            System.out.println(i + " -> " + (char) i);
        }
    }

    // Convert string to ASCII array
    public static int[] stringToASCII(String text) {
        int[] asciiArray = new int[text.length()];
        for (int i = 0; i < text.length(); i++) {
            asciiArray[i] = (int) text.charAt(i);
        }
        return asciiArray;
    }

    // Convert ASCII array to string
    public static String asciiToString(int[] asciiValues) {
        StringBuilder sb = new StringBuilder();
        for (int val : asciiValues) {
            sb.append((char) val);
        }
        return sb.toString();
    }
}