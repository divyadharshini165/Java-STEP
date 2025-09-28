import java.util.Scanner;

public class AnagramChecker {

    // b. Method to check if two texts are anagrams
    public static boolean isAnagram(String text1, String text2) {

        // i. Check if lengths are equal
        if (text1.length() != text2.length()) {
            return false;
        }

        // ii. Create frequency arrays for ASCII characters
        int[] freq1 = new int[256];
        int[] freq2 = new int[256];

        // iii. Count frequency for both texts
        for (int i = 0; i < text1.length(); i++) {
            freq1[text1.charAt(i)]++;
            freq2[text2.charAt(i)]++;
        }

        // iv. Compare frequencies
        for (int i = 0; i < 256; i++) {
            if (freq1[i] != freq2[i]) {
                return false;
            }
        }

        return true;
    }

    // c. Main method
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        System.out.print("Enter first text: ");
        String str1 = sc.nextLine();

        System.out.print("Enter second text: ");
        String str2 = sc.nextLine();

        if (isAnagram(str1, str2)) {
            System.out.println("The texts are Anagrams.");
        } else {
            System.out.println("The texts are NOT Anagrams.");
        }

        sc.close();
    }
}
