import java.util.*;

public class TextCompression {

    // (b) Count character frequency without HashMap
    public static Object[] countFrequency(String text) {
        char[] chars = new char[text.length()];
        int[] freq = new int[text.length()];
        int size = 0;

        for (int i = 0; i < text.length(); i++) {
            char c = text.charAt(i);
            int index = -1;
            for (int j = 0; j < size; j++) {
                if (chars[j] == c) {
                    index = j;
                    break;
                }
            }
            if (index == -1) {
                chars[size] = c;
                freq[size] = 1;
                size++;
            } else {
                freq[index]++;
            }
        }

        // Trim arrays
        char[] uniqueChars = Arrays.copyOf(chars, size);
        int[] uniqueFreq = Arrays.copyOf(freq, size);

        return new Object[]{uniqueChars, uniqueFreq};
    }

    // (c) Create compression codes using StringBuilder
    public static String[][] createCodes(char[] chars, int[] freq) {
        int n = chars.length;
        String[][] mapping = new String[n][2];

        // Sort characters by frequency (descending)
        for (int i = 0; i < n - 1; i++) {
            for (int j = i + 1; j < n; j++) {
                if (freq[j] > freq[i]) {
                    int tempF = freq[i];
                    freq[i] = freq[j];
                    freq[j] = tempF;

                    char tempC = chars[i];
                    chars[i] = chars[j];
                    chars[j] = tempC;
                }
            }
        }

        // Assign codes (shorter for frequent chars)
        for (int i = 0; i < n; i++) {
            StringBuilder code = new StringBuilder();
            if (i < 10) code.append(i); // digits for top 10
            else if (i < 36) code.append((char) ('A' + (i - 10))); // A-Z
            else code.append("_" + i); // fallback
            mapping[i][0] = String.valueOf(chars[i]);
            mapping[i][1] = code.toString();
        }

        return mapping;
    }

    // (d) Compress text using codes
    public static String compress(String text, String[][] mapping) {
        StringBuilder sb = new StringBuilder();

        for (int i = 0; i < text.length(); i++) {
            char c = text.charAt(i);
            for (String[] map : mapping) {
                if (map[0].charAt(0) == c) {
                    sb.append(map[1]);
                    break;
                }
            }
        }
        return sb.toString();
    }

    // (e) Decompress text
    public static String decompress(String compressed, String[][] mapping) {
        StringBuilder sb = new StringBuilder();
        int i = 0;

        while (i < compressed.length()) {
            boolean found = false;

            // Try single char codes first (digits, letters)
            for (String[] map : mapping) {
                String code = map[1];
                if (compressed.startsWith(code, i)) {
                    sb.append(map[0]);
                    i += code.length();
                    found = true;
                    break;
                }
            }

            if (!found) { // Safety fallback
                i++;
            }
        }

        return sb.toString();
    }

    // (f) Display analysis
    public static void displayAnalysis(String text, String compressed, String decompressed, char[] chars, int[] freq, String[][] mapping) {
        System.out.println("\n--- Character Frequency Table ---");
        System.out.printf("%-10s %-10s\n", "Char", "Freq");
        for (int i = 0; i < chars.length; i++) {
            System.out.printf("%-10s %-10d\n", chars[i], freq[i]);
        }

        System.out.println("\n--- Compression Mapping ---");
        System.out.printf("%-10s %-10s\n", "Char", "Code");
        for (String[] map : mapping) {
            System.out.printf("%-10s %-10s\n", map[0], map[1]);
        }

        System.out.println("\n--- Compression Results ---");
        System.out.println("Original Text    : " + text);
        System.out.println("Compressed Text  : " + compressed);
        System.out.println("Decompressed Text: " + decompressed);

        int originalSize = text.length();
        int compressedSize = compressed.length();
        double efficiency = (1 - (compressedSize / (double) originalSize)) * 100;

        System.out.println("\nCompression Efficiency: " + String.format("%.2f", efficiency) + "%");
    }

    // (g) Main function
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.println("Enter text to compress:");
        String text = sc.nextLine();

        // Step 1: Frequency
        Object[] freqData = countFrequency(text);
        char[] chars = (char[]) freqData[0];
        int[] freq = (int[]) freqData[1];

        // Step 2: Mapping
        String[][] mapping = createCodes(chars, freq);

        // Step 3: Compression
        String compressed = compress(text, mapping);

        // Step 4: Decompression
        String decompressed = decompress(compressed, mapping);

        // Step 5: Display results
        displayAnalysis(text, compressed, decompressed, chars, freq, mapping);

        sc.close();
    }
}
