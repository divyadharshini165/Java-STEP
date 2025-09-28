public class Student {
    // Private instance variables
    private String studentId;
    private String name;
    private double grade;
    private String course;

    // Default constructor
    public Student() {
        this.studentId = "";
        this.name = "";
        this.grade = 0.0;
        this.course = "";
    }

    // Parameterized constructor
    public Student(String studentId, String name, double grade, String course) {
        this.studentId = studentId;
        this.name = name;
        this.grade = grade;
        this.course = course;
    }

    // Getters and Setters
    public String getStudentId() {
        return studentId;
    }

    public void setStudentId(String studentId) {
        this.studentId = studentId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getGrade() {
        return grade;
    }

    public void setGrade(double grade) {
        this.grade = grade;
    }

    public String getCourse() {
        return course;
    }

    public void setCourse(String course) {
        this.course = course;
    }

    // Method to calculate letter grade
    public String calculateLetterGrade() {
        if (grade >= 90) return "A";
        else if (grade >= 80) return "B";
        else if (grade >= 70) return "C";
        else if (grade >= 60) return "D";
        else return "F";
    }

    // Method to display student info
    public void displayStudent() {
        System.out.println("Student ID: " + studentId);
        System.out.println("Name: " + name);
        System.out.println("Course: " + course);
        System.out.println("Grade: " + grade + " (" + calculateLetterGrade() + ")");
        System.out.println("-------------------------");
    }

    // Main method for testing
    public static void main(String[] args) {
        // Student using default constructor
        Student s1 = new Student();
        s1.setStudentId("S101");
        s1.setName("Alice");
        s1.setGrade(85.5);
        s1.setCourse("Mathematics");

        // Student using parameterized constructor
        Student s2 = new Student("S102", "Bob", 72.0, "Computer Science");

        // Demonstrate getters
        System.out.println("Getting Student 1 Name via getter: " + s1.getName());
        System.out.println("Getting Student 2 Course via getter: " + s2.getCourse());
        System.out.println();

        // Display both students
        s1.displayStudent();
        s2.displayStudent();
    }
}
