import java.time.LocalDate;

// Parent class
class Course {
    protected String title;
    protected String instructor;
    protected LocalDate enrollmentDate;

    public Course(String title, String instructor, LocalDate enrollmentDate) {
        this.title = title;
        this.instructor = instructor;
        this.enrollmentDate = enrollmentDate;
    }

    // Method to be overridden
    public void displayProgress() {
        System.out.println("📚 Course: " + title);
        System.out.println("Instructor: " + instructor);
        System.out.println("Enrollment Date: " + enrollmentDate);
        System.out.println("Progress: Generic course tracking...");
    }
}

// VideoCourse
class VideoCourse extends Course {
    private double completionPercent;
    private int watchTime; // in minutes

    public VideoCourse(String title, String instructor, LocalDate enrollmentDate,
                       double completionPercent, int watchTime) {
        super(title, instructor, enrollmentDate);
        this.completionPercent = completionPercent;
        this.watchTime = watchTime;
    }

    @Override
    public void displayProgress() {
        System.out.println("🎬 Video Course: " + title);
        System.out.println("Instructor: " + instructor);
        System.out.println("Enrolled on: " + enrollmentDate);
        System.out.println("Completion: " + completionPercent + "%");
        System.out.println("Watch Time: " + watchTime + " minutes");
        System.out.println("--------------------------------");
    }
}

// InteractiveCourse
class InteractiveCourse extends Course {
    private int quizScore;
    private int projectsCompleted;

    public InteractiveCourse(String title, String instructor, LocalDate enrollmentDate,
                             int quizScore, int projectsCompleted) {
        super(title, instructor, enrollmentDate);
        this.quizScore = quizScore;
        this.projectsCompleted = projectsCompleted;
    }

    @Override
    public void displayProgress() {
        System.out.println("🧑‍💻 Interactive Course: " + title);
        System.out.println("Instructor: " + instructor);
        System.out.println("Enrolled on: " + enrollmentDate);
        System.out.println("Quiz Score: " + quizScore + "%");
        System.out.println("Projects Completed: " + projectsCompleted);
        System.out.println("--------------------------------");
    }
}

// ReadingCourse
class ReadingCourse extends Course {
    private int pagesRead;
    private double notesProgress;

    public ReadingCourse(String title, String instructor, LocalDate enrollmentDate,
                         int pagesRead, double notesProgress) {
        super(title, instructor, enrollmentDate);
        this.pagesRead = pagesRead;
        this.notesProgress = notesProgress;
    }

    @Override
    public void displayProgress() {
        System.out.println("📖 Reading Course: " + title);
        System.out.println("Instructor: " + instructor);
        System.out.println("Enrolled on: " + enrollmentDate);
        System.out.println("Pages Read: " + pagesRead);
        System.out.println("Notes Progress: " + notesProgress + "%");
        System.out.println("--------------------------------");
    }
}

// CertificationCourse
class CertificationCourse extends Course {
    private int examAttempts;
    private boolean certified;

    public CertificationCourse(String title, String instructor, LocalDate enrollmentDate,
                               int examAttempts, boolean certified) {
        super(title, instructor, enrollmentDate);
        this.examAttempts = examAttempts;
        this.certified = certified;
    }

    @Override
    public void displayProgress() {
        System.out.println("🏅 Certification Course: " + title);
        System.out.println("Instructor: " + instructor);
        System.out.println("Enrolled on: " + enrollmentDate);
        System.out.println("Exam Attempts: " + examAttempts);
        System.out.println("Certification Status: " + (certified ? "✅ Certified" : "❌ Not Certified"));
        System.out.println("--------------------------------");
    }
}

// Test class
public class OnlineLearningPlatform {
    public static void main(String[] args) {
        Course[] courses = new Course[4];
        courses[0] = new VideoCourse("Java Basics", "Alice", LocalDate.of(2025, 1, 15), 75.5, 320);
        courses[1] = new InteractiveCourse("Web Development Bootcamp", "Bob", LocalDate.of(2025, 2, 10), 85, 3);
        courses[2] = new ReadingCourse("History of AI", "Carol", LocalDate.of(2025, 3, 5), 120, 60.0);
        courses[3] = new CertificationCourse("AWS Cloud Certification", "Dave", LocalDate.of(2025, 4, 20), 2, true);

        // Polymorphism in action
        for (Course c : courses) {
            c.displayProgress();
        }
    }
}

