// Base class
class LibraryUser {
    protected String name;
    protected String userId;

    public LibraryUser(String name, String userId) {
        this.name = name;
        this.userId = userId;
    }

    public void displayInfo() {
        System.out.println("User: " + name + " (ID: " + userId + ")");
    }

    public void logEntry() {
        System.out.println(name + " has entered the library.");
    }
}

// Student subclass
class Student extends LibraryUser {
    public Student(String name, String userId) {
        super(name, userId);
    }

    public void borrowBook() {
        System.out.println(" Student " + name + " borrowed a book.");
    }

    public void accessComputer() {
        System.out.println(" Student " + name + " is using a library computer.");
    }
}

// Faculty subclass
class Faculty extends LibraryUser {
    public Faculty(String name, String userId) {
        super(name, userId);
    }

    public void reserveBook() {
        System.out.println(" Faculty " + name + " reserved a book.");
    }

    public void accessResearchDatabase() {
        System.out.println(" Faculty " + name + " accessed research databases.");
    }
}

// Guest subclass
class Guest extends LibraryUser {
    public Guest(String name, String userId) {
        super(name, userId);
    }

    public void browseBooks() {
        System.out.println(" Guest " + name + " is browsing books.");
    }
}

// Test class
public class UniversityLibrary {
    public static void main(String[] args) {
        // Upcasting: Storing different user types in a common array
        LibraryUser[] users = new LibraryUser[3];
        users[0] = new Student("Alice", "S101");   // Upcast
        users[1] = new Faculty("Dr. Bob", "F202"); // Upcast
        users[2] = new Guest("Charlie", "G303");   // Upcast

        // Common operations (possible on base class reference)
        for (LibraryUser u : users) {
            u.logEntry();
            u.displayInfo();
            System.out.println("--------------------------------");
        }

        // Downcasting to access specialized behavior
        System.out.println(" Specialized Actions:");
        ((Student) users[0]).borrowBook();
        ((Student) users[0]).accessComputer();

        ((Faculty) users[1]).reserveBook();
        ((Faculty) users[1]).accessResearchDatabase();

        ((Guest) users[2]).browseBooks();
    }
}

