import java.util.*;

public class CSVAnalyzer {

    // (b) Parse CSV manually
    public static String[][] parseCSV(String input) {
        List<String[]> rows = new ArrayList<>();
        List<String> currentRow = new ArrayList<>();

        boolean inQuotes = false;
        int start = 0;

        for (int i = 0; i < input.length(); i++) {
            char c = input.charAt(i);

            if (c == '"') {
                inQuotes = !inQuotes; // toggle quote mode
            } else if ((c == ',' && !inQuotes) || c == '\n') {
                currentRow.add(input.substring(start, i));
                start = i + 1;

                if (c == '\n') {
                    rows.add(currentRow.toArray(new String[0]));
                    currentRow.clear();
                }
            }
        }

        // last field
        currentRow.add(input.substring(start));
        rows.add(currentRow.toArray(new String[0]));

        return rows.toArray(new String[0][0]);
    }

    // (c) Clean + validate data
    public static String[][] cleanData(String[][] data) {
        for (int i = 0; i < data.length; i++) {
            for (int j = 0; j < data[i].length; j++) {
                if (data[i][j] != null) {
                    data[i][j] = data[i][j].trim();
                    if (data[i][j].startsWith("\"") && data[i][j].endsWith("\"")) {
                        data[i][j] = data[i][j].substring(1, data[i][j].length() - 1);
                    }
                } else {
                    data[i][j] = "";
                }
            }
        }
        return data;
    }

    // Check if string is numeric using ASCII
    public static boolean isNumeric(String s) {
        if (s.isEmpty()) return false;
        for (int i = 0; i < s.length(); i++) {
            int ascii = (int) s.charAt(i);
            if (!(ascii >= 48 && ascii <= 57) && ascii != 46 && ascii != 45) { // digits, dot, minus
                return false;
            }
        }
        return true;
    }

    // (d) Data Analysis
    public static void analyzeData(String[][] data) {
        String[] headers = data[0];
        int cols = headers.length;
        int rows = data.length - 1;

        Map<Integer, List<Double>> numericCols = new HashMap<>();
        Map<Integer, Set<String>> categoricalCols = new HashMap<>();
        int missingCount = 0;

        for (int j = 0; j < cols; j++) {
            boolean numeric = true;
            for (int i = 1; i < data.length; i++) {
                if (!isNumeric(data[i][j])) {
                    numeric = false;
                    break;
                }
            }
            if (numeric) numericCols.put(j, new ArrayList<>());
            else categoricalCols.put(j, new HashSet<>());
        }

        for (int i = 1; i < data.length; i++) {
            for (int j = 0; j < cols; j++) {
                String val = data[i][j];
                if (val.isEmpty()) {
                    missingCount++;
                } else if (numericCols.containsKey(j) && isNumeric(val)) {
                    numericCols.get(j).add(Double.parseDouble(val));
                } else if (categoricalCols.containsKey(j)) {
                    categoricalCols.get(j).add(val);
                }
            }
        }

        // (e) Format output
        formatOutput(data);

        // (f) Summary Report
        System.out.println("\n--- Data Summary Report ---");
        System.out.println("Total Records: " + rows);

        for (int j = 0; j < cols; j++) {
            if (numericCols.containsKey(j)) {
                List<Double> values = numericCols.get(j);
                double min = Double.MAX_VALUE, max = -Double.MAX_VALUE, sum = 0;
                for (double v : values) {
                    min = Math.min(min, v);
                    max = Math.max(max, v);
                    sum += v;
                }
                double avg = sum / values.size();
                System.out.printf("Column [%s] -> Min: %.2f, Max: %.2f, Avg: %.2f\n",
                        headers[j], min, max, avg);
            } else if (categoricalCols.containsKey(j)) {
                System.out.printf("Column [%s] -> Unique Values: %d\n",
                        headers[j], categoricalCols.get(j).size());
            }
        }

        double completeness = 100.0 * (rows * cols - missingCount) / (rows * cols);
        System.out.println("Missing/Invalid Entries: " + missingCount);
        System.out.printf("Data Completeness: %.2f%%\n", completeness);
    }

    // (e) Format output as table
    public static void formatOutput(String[][] data) {
        int cols = data[0].length;
        int[] colWidths = new int[cols];

        for (int j = 0; j < cols; j++) {
            int maxLen = 0;
            for (int i = 0; i < data.length; i++) {
                if (data[i][j] != null)
                    maxLen = Math.max(maxLen, data[i][j].length());
            }
            colWidths[j] = maxLen + 2;
        }

        System.out.println("\n--- Formatted Data Table ---");
        for (int i = 0; i < data.length; i++) {
            StringBuilder row = new StringBuilder("| ");
            for (int j = 0; j < cols; j++) {
                String val = (data[i][j].isEmpty()) ? "NA" : data[i][j];
                row.append(String.format("%-" + colWidths[j] + "s", val)).append("| ");
            }
            System.out.println(row);
        }
    }

    // (g) Main
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.println("Enter CSV-like data (end with empty line):");

        StringBuilder input = new StringBuilder();
        while (true) {
            String line = sc.nextLine();
            if (line.trim().isEmpty()) break;
            input.append(line).append("\n");
        }

        String[][] parsed = parseCSV(input.toString());
        String[][] cleaned = cleanData(parsed);

        analyzeData(cleaned);

        sc.close();
    }
}
