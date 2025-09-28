import java.util.Scanner;

public class AdvancedStringAnalyzer {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("=== ADVANCED STRING ANALYZER ===");

        // Ask user for two strings
        System.out.print("Enter first string: ");
        String str1 = scanner.nextLine();
        System.out.print("Enter second string: ");
        String str2 = scanner.nextLine();

        // Perform comparisons
        performAllComparisons(str1, str2);

        // Calculate similarity percentage
        double similarity = calculateSimilarity(str1, str2);
        System.out.printf("Similarity: %.2f%%\n", similarity);

        // Analyze memory usage
        analyzeMemoryUsage(str1, str2);

        // Optimize string processing example
        String[] words = {"Java", "is", "fast", "and", "fun"};
        System.out.println("Optimized processing result: " + optimizedStringProcessing(words));

        // Demonstrate intern() method
        demonstrateStringIntern();

        scanner.close();
    }

    // Calculate similarity using Levenshtein distance
    public static double calculateSimilarity(String str1, String str2) {
        int distance = levenshteinDistance(str1, str2);
        int maxLength = Math.max(str1.length(), str2.length());
        if (maxLength == 0) return 100.0; // Both strings empty
        return ((double) (maxLength - distance) / maxLength) * 100;
    }

    // Helper method for Levenshtein distance
    private static int levenshteinDistance(String s1, String s2) {
        int[][] dp = new int[s1.length() + 1][s2.length() + 1];
        for (int i = 0; i <= s1.length(); i++) dp[i][0] = i;
        for (int j = 0; j <= s2.length(); j++) dp[0][j] = j;

        for (int i = 1; i <= s1.length(); i++) {
            for (int j = 1; j <= s2.length(); j++) {
                int cost = (s1.charAt(i - 1) == s2.charAt(j - 1)) ? 0 : 1;
                dp[i][j] = Math.min(
                    Math.min(dp[i - 1][j] + 1, dp[i][j - 1] + 1),
                    dp[i - 1][j - 1] + cost
                );
            }
        }
        return dp[s1.length()][s2.length()];
    }

    // Perform all comparisons
    public static void performAllComparisons(String str1, String str2) {
        System.out.println("\n=== COMPARISON RESULTS ===");
        System.out.println("Reference equality (==): " + (str1 == str2));
        System.out.println("Content equality (equals): " + str1.equals(str2));
        System.out.println("Case-insensitive equality: " + str1.equalsIgnoreCase(str2));
        System.out.println("Lexicographic compareTo: " + str1.compareTo(str2));
        System.out.println("Case-insensitive compareTo: " + str1.compareToIgnoreCase(str2));
    }

    // Analyze memory usage
    public static void analyzeMemoryUsage(String... strings) {
        Runtime runtime = Runtime.getRuntime();
        long before = runtime.freeMemory();
        // Force creation of new objects to measure
        for (String s : strings) {
            new String(s); // avoid interning
        }
        long after = runtime.freeMemory();
        System.out.println("\nMemory used for storing strings: " + (before - after) + " bytes (approximate)");
    }

    // Optimize string operations using StringBuilder
    public static String optimizedStringProcessing(String[] inputs) {
        StringBuilder sb = new StringBuilder();
        for (String word : inputs) {
            sb.append(word).append(" ");
        }
        return sb.toString().trim();
    }

    // Demonstrate intern()
    public static void demonstrateStringIntern() {
        System.out.println("\n=== INTERN() DEMO ===");
        String a = new String("Java");
        String b = "Java";
        String c = a.intern(); // returns reference from string pool

        System.out.println("a == b before intern(): " + (a == b));
        System.out.println("c == b after intern(): " + (c == b));
    }
}
