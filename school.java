public class School {
    static String schoolName = "Green Valley High";
    String studentName;
    int age;
    static {
        System.out.println("Static Block: School is opening...");
    }
    public School(String name, int age) {
        this.studentName = name;
        this.age = age;
    }
}
 void showStudentDetails() {
        System.out.println("Student: " + studentName + ", Age: " + age);
    }
    public static void main(String[] args) {
        School.showSchoolName();
        School s1 = new School("Alice", 14);
        School s2 = new School("Bob", 15);
        s1.showStudentDetails();
        s2.showStudentDetails();
    }
}