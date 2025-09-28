class Student {
    private int marks; 
    Student(int marks) {
        this.marks = marks;
    }
    public int getMarks() {
        return marks;
    }
    public void setMarks(int marks, boolean isTeacher) {
        if (isTeacher) {
            this.marks = marks;
            System.out.println("Marks updated by teacher.");
        } else {
            System.out.println("Access denied! Only teachers can update.");
        }
    }
}

public class StudentMarksSystem {
    public static void main(String[] args) {
        Student s = new Student(80);
        System.out.println("Marks: " + s.getMarks());
        s.setMarks(90, false);
        s.setMarks(90, true);
        System.out.println("Marks: " + s.getMarks());
    }
}
