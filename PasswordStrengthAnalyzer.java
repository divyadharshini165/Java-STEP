import java.util.*;

public class PasswordStrengthAnalyzer {

    // (b) Analyze password using ASCII values
    public static int[] analyzePassword(String password) {
        int upper = 0, lower = 0, digit = 0, special = 0;

        for (int i = 0; i < password.length(); i++) {
            int ascii = (int) password.charAt(i);

            if (ascii >= 65 && ascii <= 90) upper++;         // Uppercase
            else if (ascii >= 97 && ascii <= 122) lower++;   // Lowercase
            else if (ascii >= 48 && ascii <= 57) digit++;    // Digits
            else if (ascii >= 33 && ascii <= 126) special++; // Special chars
        }

        return new int[]{upper, lower, digit, special};
    }

    // (c) Calculate password score
    public static int calculateScore(String password, int[] counts) {
        int length = password.length();
        int upper = counts[0], lower = counts[1], digit = counts[2], special = counts[3];
        int score = 0;

        // Length points
        if (length > 8) score += (length - 8) * 2;

        // Variety points
        if (upper > 0) score += 10;
        if (lower > 0) score += 10;
        if (digit > 0) score += 10;
        if (special > 0) score += 10;

        // Deduct points for common patterns
        String lowerPass = password.toLowerCase();
        String[] weakPatterns = {"123", "abc", "qwerty", "password", "111", "000"};
        for (String pat : weakPatterns) {
            if (lowerPass.contains(pat)) score -= 10;
        }

        return score;
    }

    public static String strengthLevel(int score) {
        if (score <= 20) return "Weak";
        else if (score <= 50) return "Medium";
        else return "Strong";
    }

    // (d) Generate strong password using StringBuilder
    public static String generatePassword(int length) {
        if (length < 4) length = 4; // must fit all categories

        String upperCase = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
        String lowerCase = "abcdefghijklmnopqrstuvwxyz";
        String digits = "0123456789";
        String special = "!@#$%^&*()-_=+<>?";

        Random rand = new Random();
        StringBuilder sb = new StringBuilder();

        // Ensure at least one from each
        sb.append(upperCase.charAt(rand.nextInt(upperCase.length())));
        sb.append(lowerCase.charAt(rand.nextInt(lowerCase.length())));
        sb.append(digits.charAt(rand.nextInt(digits.length())));
        sb.append(special.charAt(rand.nextInt(special.length())));

        // Fill remaining
        String allChars = upperCase + lowerCase + digits + special;
        for (int i = 4; i < length; i++) {
            sb.append(allChars.charAt(rand.nextInt(allChars.length())));
        }

        // Shuffle characters for randomness
        List<Character> chars = new ArrayList<>();
        for (char c : sb.toString().toCharArray()) chars.add(c);
        Collections.shuffle(chars);
        StringBuilder finalPass = new StringBuilder();
        for (char c : chars) finalPass.append(c);

        return finalPass.toString();
    }

    // (e) Display results
    public static void displayResults(String[] passwords) {
        System.out.printf("%-15s %-8s %-8s %-8s %-8s %-8s %-8s %-10s\n",
                "Password", "Length", "Upper", "Lower", "Digits", "Special", "Score", "Strength");
        System.out.println("-------------------------------------------------------------------------------");

        for (String pass : passwords) {
            int[] counts = analyzePassword(pass);
            int score = calculateScore(pass, counts);
            String level = strengthLevel(score);

            System.out.printf("%-15s %-8d %-8d %-8d %-8d %-8d %-8d %-10s\n",
                    pass, pass.length(), counts[0], counts[1], counts[2], counts[3], score, level);
        }
    }

    // (f) Main function
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        System.out.println("Enter number of passwords to analyze:");
        int n = sc.nextInt();
        sc.nextLine();

        String[] passwords = new String[n];
        System.out.println("Enter passwords:");
        for (int i = 0; i < n; i++) {
            passwords[i] = sc.nextLine();
        }

        // Display analysis
        displayResults(passwords);

        // Password generator
        System.out.println("\nDo you want to generate a strong password? (yes/no): ");
        String choice = sc.nextLine();

        if (choice.equalsIgnoreCase("yes")) {
            System.out.println("Enter desired password length: ");
            int len = sc.nextInt();
            String strongPass = generatePassword(len);
            System.out.println("Generated Strong Password: " + strongPass);
        }

        sc.close();
    }
}
