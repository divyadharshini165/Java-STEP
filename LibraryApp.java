// LibraryApp.java - Simplified Library Management System with Fine Calculation
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;
import java.util.*;

class Book {
    String bookId, title, author, isbn, category;
    boolean isIssued = false;
    String issueDate = null, dueDate = null;
    int timesIssued = 0;
    Book(String id,String t,String a,String i,String c){ bookId=id; title=t; author=a; isbn=i; category=c;}
    public String toString(){ return bookId+" | "+title+" | "+author+" | Issued:"+isIssued; }
}

class Member {
    String memberId, memberName, memberType, membershipDate;
    Book[] booksIssued;
    int issuedCount = 0;
    double totalFines = 0.0;
    Member(String id,String name,String type){ memberId=id; memberName=name; memberType=type;
        membershipDate = LocalDate.now().toString(); booksIssued = new Book[10]; }
}

class Library {
    // static configuration
    static int totalBooks=0, totalMembers=0;
    static String libraryName="City Library";
    static double finePerDay = 5.0; // currency units per overdue day
    // max allowed per member type
    static Map<String,Integer> maxBooksAllowed = Map.of("Student",3,"Faculty",10,"General",2);

    DateTimeFormatter DF = DateTimeFormatter.ISO_LOCAL_DATE;
    List<Book> books = new ArrayList<>();
    List<Member> members = new ArrayList<>();
    Map<String,Queue<String>> reservations = new HashMap<>(); // bookId -> queue of memberIds

    // add helpers
    void addBook(Book b){ books.add(b); totalBooks++; }
    void addMember(Member m){ members.add(m); totalMembers++; }

    Book findBookById(String id){ return books.stream().filter(b->b.bookId.equals(id)).findFirst().orElse(null);}
    Member findMemberById(String id){ return members.stream().filter(m->m.memberId.equals(id)).findFirst().orElse(null);}

    // issue book
    boolean issueBook(String bookId, String memberId, int days) {
        Book b = findBookById(bookId); Member m = findMemberById(memberId);
        if(b==null||m==null) return false;
        if(b.isIssued){ System.out.println("Book already issued."); return false; }
        int max = maxBooksAllowed.getOrDefault(m.memberType,2);
        if(m.issuedCount>=max){ System.out.println("Member exceeded max books."); return false; }
        b.isIssued = true; b.issueDate = LocalDate.now().toString();
        b.dueDate = LocalDate.now().plusDays(days).toString(); b.timesIssued++;
        m.booksIssued[m.issuedCount++] = b;
        System.out.println("Issued "+b.title+" to "+m.memberName+" due "+b.dueDate);
        return true;
    }

    // return book
    boolean returnBook(String bookId, String memberId) {
        Book b = findBookById(bookId); Member m = findMemberById(memberId);
        if(b==null||m==null) return false;
        // remove from member
        boolean found=false;
        for(int i=0;i<m.issuedCount;i++){
            if(m.booksIssued[i]!=null && m.booksIssued[i].bookId.equals(bookId)){
                // shift left
                for(int j=i;j<m.issuedCount-1;j++) m.booksIssued[j]=m.booksIssued[j+1];
                m.booksIssued[--m.issuedCount]=null; found=true; break;
            }
        }
        if(!found){ System.out.println("This member did not have the book."); return false; }
        // fine calc
        double fine = calculateFine(b.dueDate, LocalDate.now().toString());
        m.totalFines += fine;
        b.isIssued=false; b.issueDate=null; b.dueDate=null;
        System.out.println("Returned "+b.title+". Fine: "+fine);
        // handle reservation queue
        Queue<String> q = reservations.get(bookId);
        if(q!=null && !q.isEmpty()){
            String nextMemberId = q.poll();
            System.out.println("Book reserved by "+nextMemberId+" — auto-issuing for 7 days.");
            issueBook(bookId,nextMemberId,7);
        }
        return true;
    }

    // renew book (extend due date)
    boolean renewBook(String bookId, String memberId, int extendDays) {
        Book b = findBookById(bookId); Member m = findMemberById(memberId);
        if(b==null||m==null) return false;
        if(!b.isIssued){ System.out.println("Book not issued."); return false; }
        // check if reserved by others
        Queue<String> q = reservations.get(bookId);
        if(q!=null && !q.isEmpty()){ System.out.println("Cannot renew — reservations exist."); return false; }
        LocalDate newDue = LocalDate.parse(b.dueDate).plusDays(extendDays);
        b.dueDate = newDue.toString();
        System.out.println("Renewed "+b.title+" new due "+b.dueDate);
        return true;
    }

    // calculate fine given dueDate and returnDate
    double calculateFine(String dueDate, String returnDate) {
        LocalDate due = LocalDate.parse(dueDate);
        LocalDate ret = LocalDate.parse(returnDate);
        long days = ChronoUnit.DAYS.between(due, ret);
        if(days<=0) return 0.0;
        return days * finePerDay;
    }

    // search books by title/author/category (simple)
    List<Book> searchBooks(String q){
        String ql = q.toLowerCase();
        List<Book> out = new ArrayList<>();
        for(Book b: books) if(b.title.toLowerCase().contains(ql)||b.author.toLowerCase().contains(ql)||b.category.toLowerCase().contains(ql)) out.add(b);
        return out;
    }

    // reserve book
    boolean reserveBook(String bookId, String memberId) {
        Book b = findBookById(bookId); Member m = findMemberById(memberId);
        if(b==null||m==null) return false;
        reservations.putIfAbsent(bookId, new LinkedList<>());
        reservations.get(bookId).add(memberId);
        System.out.println(m.memberName+" reserved "+b.title);
        return true;
    }

    // REPORTS
    static String generateLibraryReport(Library lib){
        StringBuilder sb = new StringBuilder();
        sb.append("Library: ").append(libraryName).append("\n");
        sb.append("Total Books: ").append(totalBooks).append(" Total Members: ").append(totalMembers).append("\n");
        sb.append("Overdue Books: ").append(lib.getOverdueBooks().size()).append("\n");
        sb.append("Most Popular: ").append(getMostPopularBooks(lib,3)).append("\n");
        return sb.toString();
    }

    List<Book> getOverdueBooks(){
        List<Book> out = new ArrayList<>();
        String today = LocalDate.now().toString();
        for(Book b: books) if(b.isIssued && LocalDate.parse(b.dueDate).isBefore(LocalDate.parse(today))) out.add(b);
        return out;
    }

    static List<String> getMostPopularBooks(Library lib,int topN){
        List<Book> sorted = new ArrayList<>(lib.books);
        sorted.sort((a,b)->Integer.compare(b.timesIssued,a.timesIssued));
        List<String> top = new ArrayList<>();
        for(int i=0;i<Math.min(topN,sorted.size());i++) top.add(sorted.get(i).title);
        return top;
    }
}

public class LibraryApp {
    public static void main(String[] args) {
        Library lib = new Library();

        // seed books
        lib.addBook(new Book("B001","Java Basics","R. Kumar","ISBN001","Programming"));
        lib.addBook(new Book("B002","Data Structures","S. Rao","ISBN002","CS"));
        lib.addBook(new Book("B003","World History","P. Singh","ISBN003","History"));
        lib.addBook(new Book("B004","Physics 101","A. Verma","ISBN004","Science"));

        // seed members
        lib.addMember(new Member("M001","Alice","Student"));
        lib.addMember(new Member("M002","Dr. Bala","Faculty"));
        lib.addMember(new Member("M003","Charlie","General"));

        // demo operations
        lib.issueBook("B001","M001",14); // student borrows Java Basics for 14 days
        lib.issueBook("B002","M002",30); // faculty borrows DS
        lib.reserveBook("B001","M003");  // Charlie reserves B001
        // fast-forward: simulate return after overdue by adjusting book dueDate (for demo only)
        Book b = lib.findBookById("B001");
        b.dueDate = LocalDate.now().minusDays(3).toString(); // make it overdue by 3 days
        lib.returnBook("B001","M001"); // return will calculate fine and auto-issue to reserver M003

        // renew attempt (should fail if reserved)
        lib.renewBook("B002","M002",7);

        // search
        System.out.println("Search results for 'data': " + lib.searchBooks("data"));

        // reports
        System.out.println(Library.generateLibraryReport(lib));
        System.out.println("Overdue books list: " + lib.getOverdueBooks());
    }
}
