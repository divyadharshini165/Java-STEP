class Student {
    // Private instance variables
    private String studentId;
    private String studentName;
    private int grade;
    private double[] marks; // 5 subjects
    private double totalMarks;
    private double percentage;

    // Constructor
    public Student(String studentId, String studentName, int grade) {
        this.studentId = studentId;
        this.studentName = studentName;
        this.grade = grade;
        this.marks = new double[5]; // initialize for 5 subjects
        this.totalMarks = 0;
        this.percentage = 0;
    }

    // Methods
    public void setMarks(int subjectIndex, double mark) {
        if (subjectIndex >= 0 && subjectIndex < marks.length) {
            marks[subjectIndex] = mark;
        }
    }

    public void calculateTotal() {
        totalMarks = 0;
        for (double m : marks) {
            totalMarks += m;
        }
    }

    public void calculatePercentage() {
        percentage = totalMarks / marks.length;
    }

    public boolean isPass(Subject[] subjects) {
        for (int i = 0; i < subjects.length; i++) {
            if (marks[i] < subjects[i].getPassMarks()) {
                return false;
            }
        }
        return true;
    }

    public void displayResult(Subject[] subjects) {
        System.out.println("----- Result of " + studentName + " -----");
        for (int i = 0; i < subjects.length; i++) {
            System.out.println(subjects[i].getSubjectName() + ": " + marks[i]);
        }
        System.out.println("Total Marks: " + totalMarks);
        System.out.println("Percentage: " + percentage + "%");
        System.out.println("Result: " + (isPass(subjects) ? "PASS" : "FAIL"));
        System.out.println("-----------------------------------");
    }

    public double getPercentage() {
        return percentage;
    }

    public String getStudentName() {
        return studentName;
    }

    // Static methods for statistics
    public static Student getTopStudent(Student[] students) {
        Student top = students[0];
        for (Student s : students) {
            if (s.getPercentage() > top.getPercentage()) {
                top = s;
            }
        }
        return top;
    }

    public static double getClassAverage(Student[] students) {
        double total = 0;
        for (Student s : students) {
            total += s.getPercentage();
        }
        return total / students.length;
    }

    public static double getPassPercentage(Student[] students, Subject[] subjects) {
        int passCount = 0;
        for (Student s : students) {
            if (s.isPass(subjects)) {
                passCount++;
            }
        }
        return (passCount * 100.0) / students.length;
    }
}

class Teacher {
    private String teacherId;
    private String teacherName;
    private String subject;
    private int studentsHandled;
    private static int totalTeachers = 0;

    public Teacher(String teacherId, String teacherName, String subject) {
        this.teacherId = teacherId;
        this.teacherName = teacherName;
        this.subject = subject;
        this.studentsHandled = 0;
        totalTeachers++;
    }

    // Assign grades to a student for a particular subject
    public void assignGrades(Student student, Subject subject, double marks, int subjectIndex) {
        student.setMarks(subjectIndex, marks);
        studentsHandled++;
        System.out.println("Teacher " + teacherName + " assigned " + marks + " to " + student.getStudentName() + " in " + subject.getSubjectName());
    }

    public void displayTeacherInfo() {
        System.out.println("Teacher ID: " + teacherId);
        System.out.println("Name: " + teacherName);
        System.out.println("Subject: " + subject);
        System.out.println("Students Handled: " + studentsHandled);
        System.out.println("-----------------------------------");
    }

    public static int getTotalTeachers() {
        return totalTeachers;
    }
}

class Subject {
    private String subjectCode;
    private String subjectName;
    private int maxMarks;
    private int passMarks;

    public Subject(String subjectCode, String subjectName, int maxMarks, int passMarks) {
        this.subjectCode = subjectCode;
        this.subjectName = subjectName;
        this.maxMarks = maxMarks;
        this.passMarks = passMarks;
    }

    public String getSubjectName() {
        return subjectName;
    }

    public int getPassMarks() {
        return passMarks;
    }
}

public class SchoolManagementSystem {
    public static void main(String[] args) {
        // Create Subjects
        Subject[] subjects = {
            new Subject("S101", "Math", 100, 35),
            new Subject("S102", "Science", 100, 35),
            new Subject("S103", "English", 100, 35),
            new Subject("S104", "History", 100, 35),
            new Subject("S105", "Computer", 100, 35)
        };

        // Create Students
        Student[] students = {
            new Student("ST001", "Alice", 10),
            new Student("ST002", "Bob", 10),
            new Student("ST003", "Charlie", 10)
        };

        // Create Teachers
        Teacher t1 = new Teacher("T001", "Mr. Sharma", "Math");
        Teacher t2 = new Teacher("T002", "Ms. Gupta", "Science");
        Teacher t3 = new Teacher("T003", "Mr. Khan", "English");
        Teacher t4 = new Teacher("T004", "Mrs. Das", "History");
        Teacher t5 = new Teacher("T005", "Mr. Roy", "Computer");

        Teacher[] teachers = {t1, t2, t3, t4, t5};

        // Teachers assign marks
        t1.assignGrades(students[0], subjects[0], 80, 0);
        t2.assignGrades(students[0], subjects[1], 70, 1);
        t3.assignGrades(students[0], subjects[2], 90, 2);
        t4.assignGrades(students[0], subjects[3], 60, 3);
        t5.assignGrades(students[0], subjects[4], 85, 4);

        t1.assignGrades(students[1], subjects[0], 40, 0);
        t2.assignGrades(students[1], subjects[1], 55, 1);
        t3.assignGrades(students[1], subjects[2], 35, 2);
        t4.assignGrades(students[1], subjects[3], 45, 3);
        t5.assignGrades(students[1], subjects[4], 50, 4);

        t1.assignGrades(students[2], subjects[0], 90, 0);
        t2.assignGrades(students[2], subjects[1], 85, 1);
        t3.assignGrades(students[2], subjects[2], 95, 2);
        t4.assignGrades(students[2], subjects[3], 88, 3);
        t5.assignGrades(students[2], subjects[4], 92, 4);

        // Calculate totals and percentages
        for (Student s : students) {
            s.calculateTotal();
            s.calculatePercentage();
        }

        // Display results
        System.out.println("\n=== Student Results ===");
        for (Student s : students) {
            s.displayResult(subjects);
        }

        // Display teacher info
        System.out.println("\n=== Teacher Information ===");
        for (Teacher t : teachers) {
            t.displayTeacherInfo();
        }

        // School-wide stats
        System.out.println("\n=== School Statistics ===");
        System.out.println("Top Student: " + Student.getTopStudent(students).getStudentName());
        System.out.println("Class Average: " + Student.getClassAverage(students));
        System.out.println("Pass Percentage: " + Student.getPassPercentage(students, subjects) + "%");
        System.out.println("Total Teachers: " + Teacher.getTotalTeachers());
    }
}
