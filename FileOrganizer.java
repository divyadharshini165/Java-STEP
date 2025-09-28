import java.util.*;

public class FileOrganizer {

    // Structure to store file info
    static class FileInfo {
        String originalName;
        String baseName;
        String extension;
        String category;
        String suggestedName;
        String contentCategory;
        boolean valid;

        FileInfo(String originalName) {
            this.originalName = originalName;
            this.baseName = "";
            this.extension = "";
            this.category = "Unknown";
            this.suggestedName = "";
            this.contentCategory = "Uncategorized";
            this.valid = true;
        }
    }

    // a. Extract file components without using split()
    public static FileInfo extractFileComponents(String filename) {
        FileInfo fi = new FileInfo(filename);

        int dotIndex = filename.lastIndexOf(".");
        if (dotIndex == -1 || dotIndex == 0 || dotIndex == filename.length() - 1) {
            fi.valid = false; // invalid file format
            return fi;
        }

        fi.baseName = filename.substring(0, dotIndex);
        fi.extension = filename.substring(dotIndex + 1).toLowerCase();

        // validate characters (only alphanumeric and underscores)
        for (int i = 0; i < fi.baseName.length(); i++) {
            char c = fi.baseName.charAt(i);
            if (!Character.isLetterOrDigit(c) && c != '_') {
                fi.valid = false;
                break;
            }
        }
        return fi;
    }

    // b. Categorize files by extension
    public static void categorizeFile(FileInfo fi) {
        if (!fi.valid) {
            fi.category = "Invalid";
            return;
        }

        switch (fi.extension) {
            case "txt":
            case "doc":
                fi.category = "Document";
                break;
            case "jpg":
            case "png":
                fi.category = "Image";
                break;
            case "mp3":
            case "wav":
                fi.category = "Audio";
                break;
            case "mp4":
            case "mkv":
                fi.category = "Video";
                break;
            default:
                fi.category = "Unknown";
        }
    }

    // c. Generate new suggested names using StringBuilder
    public static void generateSuggestedName(FileInfo fi, int counter) {
        if (!fi.valid) {
            fi.suggestedName = "INVALID_" + counter;
            return;
        }

        StringBuilder sb = new StringBuilder();
        sb.append(fi.category);
        sb.append("_");
        sb.append(System.currentTimeMillis() % 100000); // small timestamp
        sb.append("_");
        sb.append(counter);
        sb.append(".");
        sb.append(fi.extension);

        fi.suggestedName = sb.toString();
    }

    // d. Simulate content-based analysis (basic simulation)
    public static void analyzeContent(FileInfo fi) {
        if (fi.category.equals("Document")) {
            // Fake content check using filename keywords
            if (fi.baseName.toLowerCase().contains("resume"))
                fi.contentCategory = "Resume";
            else if (fi.baseName.toLowerCase().contains("report"))
                fi.contentCategory = "Report";
            else if (fi.baseName.toLowerCase().contains("code"))
                fi.contentCategory = "SourceCode";
            else
                fi.contentCategory = "GeneralDoc";
        }
    }

    // e. Display file organization report
    public static void displayReport(List<FileInfo> files) {
        System.out.println("==== FILE ORGANIZATION REPORT ====");
        System.out.printf("%-20s %-12s %-25s %-15s\n",
                "Original Name", "Category", "Suggested Name", "Content Category");

        for (FileInfo fi : files) {
            System.out.printf("%-20s %-12s %-25s %-15s\n",
                    fi.originalName, fi.category, fi.suggestedName, fi.contentCategory);
        }

        // Category counts
        Map<String, Integer> categoryCounts = new HashMap<>();
        for (FileInfo fi : files) {
            categoryCounts.put(fi.category, categoryCounts.getOrDefault(fi.category, 0) + 1);
        }

        System.out.println("\n--- Category-wise File Counts ---");
        for (String cat : categoryCounts.keySet()) {
            System.out.println(cat + ": " + categoryCounts.get(cat));
        }
    }

    // f. Generate batch rename commands
    public static void generateRenameCommands(List<FileInfo> files) {
        System.out.println("\n--- Batch Rename Commands ---");
        for (FileInfo fi : files) {
            if (fi.valid) {
                System.out.println("Rename: " + fi.originalName + " -> " + fi.suggestedName);
            } else {
                System.out.println("Invalid file skipped: " + fi.originalName);
            }
        }
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        // a. Take user input
        System.out.println("Enter number of files:");
        int n = Integer.parseInt(sc.nextLine());

        List<FileInfo> files = new ArrayList<>();

        System.out.println("Enter file names with extensions:");
        for (int i = 0; i < n; i++) {
            String fname = sc.nextLine();
            FileInfo fi = extractFileComponents(fname);
            categorizeFile(fi);
            analyzeContent(fi);
            generateSuggestedName(fi, i + 1);
            files.add(fi);
        }

        // Display results
        displayReport(files);
        generateRenameCommands(files);
    }
}
