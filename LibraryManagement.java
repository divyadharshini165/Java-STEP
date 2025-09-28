import java.util.*;

// Base class
abstract class LibraryItem {
    protected String title;
    protected String author;
    protected int id;
    protected boolean isIssued;
    protected Date dueDate;

    public LibraryItem(String title, String author, int id) {
        this.title = title;
        this.author = author;
        this.id = id;
        this.isIssued = false;
    }

    public void issueItem(int days) {
        if (!isIssued) {
            Calendar cal = Calendar.getInstance();
            cal.add(Calendar.DAY_OF_MONTH, days);
            dueDate = cal.getTime();
            isIssued = true;
            System.out.println(title + " issued, due on: " + dueDate);
        } else {
            System.out.println(title + " is already issued.");
        }
    }

    public void returnItem() {
        if (isIssued) {
            Date today = new Date();
            if (today.after(dueDate)) {
                long diff = today.getTime() - dueDate.getTime();
                long daysLate = diff / (1000 * 60 * 60 * 24);
                double fee = calculateLateFee((int) daysLate);
                System.out.println(title + " returned late. Late fee: ₹" + fee);
            } else {
                System.out.println(title + " returned on time. No fee.");
            }
            isIssued = false;
            dueDate = null;
        } else {
            System.out.println(title + " was not issued.");
        }
    }

    // Abstract method for polymorphic late fee calculation
    public abstract double calculateLateFee(int daysLate);

    @Override
    public String toString() {
        return "ID: " + id + ", Title: " + title + ", Author: " + author + ", Issued: " + isIssued;
    }
}

// Subclass Book
class Book extends LibraryItem {
    public Book(String title, String author, int id) {
        super(title, author, id);
    }

    @Override
    public double calculateLateFee(int daysLate) {
        return daysLate * 2.0; // ₹2 per day
    }
}

// Subclass Magazine
class Magazine extends LibraryItem {
    public Magazine(String title, String author, int id) {
        super(title, author, id);
    }

    @Override
    public double calculateLateFee(int daysLate) {
        return daysLate * 1.0; // ₹1 per day
    }
}

// Subclass DVD
class DVD extends LibraryItem {
    public DVD(String title, String author, int id) {
        super(title, author, id);
    }

    @Override
    public double calculateLateFee(int daysLate) {
        return daysLate * 3.0; // ₹3 per day
    }
}

// Library class
class Library {
    private List<LibraryItem> items = new ArrayList<>();

    public void addItem(LibraryItem item) {
        items.add(item);
    }

    public void showItems() {
        for (LibraryItem item : items) {
            System.out.println(item);
        }
    }

    public LibraryItem findItemById(int id) {
        for (LibraryItem item : items) {
            if (item.id == id) return item;
        }
        return null;
    }
}

// Main
public class LibraryManagement {
    public static void main(String[] args) throws InterruptedException {
        Library lib = new Library();

        // Add items
        lib.addItem(new Book("Java Programming", "James Gosling", 101));
        lib.addItem(new Magazine("Tech Monthly", "Various", 102));
        lib.addItem(new DVD("Inception", "Christopher Nolan", 103));

        System.out.println("📚 Library Items:");
        lib.showItems();

        // Issue a book
        LibraryItem book = lib.findItemById(101);
        book.issueItem(3); // due in 3 days

        // Simulate late return (force waiting 2 sec == 2 days late for demo)
        Thread.sleep(2000); 
        book.dueDate = new Date(System.currentTimeMillis() - (2L * 24 * 60 * 60 * 1000)); // simulate past due

        // Return item
        book.returnItem();

        // Show final library state
        System.out.println("\n📚 Final Library State:");
        lib.showItems();
    }
}
