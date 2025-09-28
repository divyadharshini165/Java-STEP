import java.util.*;

public class TextBasedCalculator {

    // (b) Validate expression
    public static boolean validateExpression(String expr) {
        int balance = 0;
        char prev = ' ';

        for (int i = 0; i < expr.length(); i++) {
            char c = expr.charAt(i);

            // Valid characters: digits, operators, spaces, parentheses
            if (!Character.isDigit(c) && "+-*/() ".indexOf(c) == -1) {
                return false;
            }

            // Parentheses balance
            if (c == '(') balance++;
            if (c == ')') balance--;

            if (balance < 0) return false; // More ) than (

            // Operator placement check
            if ("+-*/".indexOf(c) != -1) {
                if (i == 0 || i == expr.length() - 1) return false; // no start/end operator
                if ("+-*/".indexOf(prev) != -1) return false; // no consecutive operators
            }

            if (c != ' ') prev = c;
        }

        return balance == 0; // parentheses matched
    }

    // (c) Parse numbers and operators
    public static Object[] parseExpression(String expr) {
        List<Integer> numbers = new ArrayList<>();
        List<Character> operators = new ArrayList<>();

        int i = 0;
        while (i < expr.length()) {
            char c = expr.charAt(i);

            if (Character.isDigit(c)) {
                int start = i;
                while (i < expr.length() && Character.isDigit(expr.charAt(i))) {
                    i++;
                }
                String numStr = expr.substring(start, i);
                numbers.add(Integer.parseInt(numStr));
                continue; // skip i++ because loop moves
            } else if ("+-*/".indexOf(c) != -1) {
                operators.add(c);
            }
            i++;
        }

        return new Object[]{numbers, operators};
    }

    // (d) Evaluate without parentheses
    public static int evaluateSimple(String expr, StringBuilder steps) {
        // Parse numbers & operators
        Object[] parsed = parseExpression(expr);
        List<Integer> numbers = (List<Integer>) parsed[0];
        List<Character> operators = (List<Character>) parsed[1];

        // Handle * and /
        for (int i = 0; i < operators.size(); ) {
            char op = operators.get(i);
            if (op == '*' || op == '/') {
                int a = numbers.get(i);
                int b = numbers.get(i + 1);
                int result = (op == '*') ? (a * b) : (a / b);

                steps.append(a + " " + op + " " + b + " = " + result + "\n");

                numbers.set(i, result);
                numbers.remove(i + 1);
                operators.remove(i);
            } else {
                i++;
            }
        }

        // Handle + and -
        int result = numbers.get(0);
        for (int i = 0; i < operators.size(); i++) {
            char op = operators.get(i);
            int b = numbers.get(i + 1);
            int old = result;
            if (op == '+') result += b;
            else result -= b;
            steps.append(old + " " + op + " " + b + " = " + result + "\n");
        }

        return result;
    }

    // (e) Handle parentheses
    public static int evaluateWithParentheses(String expr, StringBuilder steps) {
        while (expr.contains("(")) {
            int open = expr.lastIndexOf("(");
            int close = expr.indexOf(")", open);

            String inside = expr.substring(open + 1, close);
            int val = evaluateSimple(inside, steps);

            expr = expr.substring(0, open) + val + expr.substring(close + 1);
        }
        return evaluateSimple(expr, steps);
    }

    // (f) Display steps
    public static void displayCalculation(String expr) {
        System.out.println("\nOriginal Expression: " + expr);
        StringBuilder steps = new StringBuilder();

        if (!validateExpression(expr)) {
            System.out.println("❌ Invalid expression!");
            return;
        }

        int result = evaluateWithParentheses(expr, steps);

        System.out.println("--- Steps ---");
        System.out.println(steps.toString());
        System.out.println("✅ Final Result: " + result);
    }

    // (g) Main
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.println("Enter number of expressions: ");
        int n = sc.nextInt();
        sc.nextLine();

        for (int i = 0; i < n; i++) {
            System.out.println("\nEnter expression " + (i + 1) + ":");
            String expr = sc.nextLine().replaceAll("\\s+", ""); // remove spaces
            displayCalculation(expr);
        }

        sc.close();
    }
}
