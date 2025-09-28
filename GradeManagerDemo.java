// Assignment 4: Student Grade Management System (compact)
import java.util.*;

class Subject {
    String subjectCode, subjectName;
    int credits;
    String instructor;
    Subject(String code, String name, int cr, String instr) {
        subjectCode = code; subjectName = name; credits = cr; instructor = instr;
    }
}

class Student {
    String studentId, studentName, className;
    String[] subjects;        // subject codes
    double[][] marks;        // marks[i][0] = percentage for subject i (kept 2D as requested)
    double gpa;

    // Static school-wide
    static int totalStudents = 0;
    static String schoolName = "Greenfield High";
    // gradingScale entries like "A:85-100", "B:70-84", ...
    static String[] gradingScale = {"A:85-100","B:70-84","C:55-69","D:40-54","F:0-39"};
    static double passPercentage = 40.0;

    Student(String id, String name, String cls, String[] subj) {
        studentId = id; studentName = name; className = cls;
        subjects = subj;
        marks = new double[subj.length][1];
        gpa = 0.0;
        totalStudents++;
    }

    // add or set marks for a subject (subjectCodes list must match order)
    void addMarks(int subjectIndex, double percent) {
        if (subjectIndex >= 0 && subjectIndex < marks.length) marks[subjectIndex][0] = percent;
    }

    // grade point mapping: A=4, B=3, C=2, D=1, F=0
    double gradePointFromPercent(double p) {
        if (p >= 85) return 4.0;
        if (p >= 70) return 3.0;
        if (p >= 55) return 2.0;
        if (p >= 40) return 1.0;
        return 0.0;
    }

    // calculate GPA weighted by subject credits (subjectCredits provided in same order)
    void calculateGPA(int[] subjectCredits) {
        double totalPoints = 0.0;
        int totalCredits = 0;
        for (int i=0;i<subjects.length;i++) {
            double p = marks[i][0];
            double gp = gradePointFromPercent(p);
            int cr = subjectCredits[i];
            totalPoints += gp * cr;
            totalCredits += cr;
        }
        gpa = totalCredits==0 ? 0.0 : Math.round((totalPoints/totalCredits)*100.0)/100.0;
    }

    String gradeFromPercent(double p) {
        if (p >= 85) return "A";
        if (p >= 70) return "B";
        if (p >= 55) return "C";
        if (p >= 40) return "D";
        return "F";
    }

    String generateReportCard(int[] subjectCredits, Map<String,Subject> subjectMap) {
        StringBuilder sb = new StringBuilder();
        sb.append("Report Card - ").append(studentName).append(" (").append(studentId).append(")\n");
        sb.append("Class: ").append(className).append("\n");
        sb.append("Subj  | Marks% | Grade | Credits\n");
        for (int i=0;i<subjects.length;i++) {
            Subject s = subjectMap.get(subjects[i]);
            sb.append(String.format("%-5s |  %5.1f |   %-2s  |   %d\n",
                    subjects[i], marks[i][0], gradeFromPercent(marks[i][0]), subjectCredits[i]));
        }
        sb.append("GPA: ").append(gpa).append("\n");
        sb.append("Promotion Eligible: ").append(checkPromotionEligibility() ? "YES" : "NO").append("\n");
        return sb.toString();
    }

    boolean checkPromotionEligibility() {
        // eligible if all subjects >= passPercentage
        for (int i=0;i<marks.length;i++) if (marks[i][0] < passPercentage) return false;
        return true;
    }

    double averagePercent() {
        double sum=0; for(int i=0;i<marks.length;i++) sum += marks[i][0];
        return Math.round((sum/marks.length)*100.0)/100.0;
    }
}

/* --------- Static utilities (school-level) --------- */
class SchoolUtils {
    // set grading scale (simple replacement)
    static void setGradingScale(String[] newScale) { Student.gradingScale = newScale; }

    // calculate class average (average of student averages)
    static double calculateClassAverage(Student[] students) {
        double s=0; int cnt=0;
        for (Student st: students) { if (st!=null) { s += st.averagePercent(); cnt++; } }
        return cnt==0?0:Math.round((s/cnt)*100.0)/100.0;
    }

    // top performers by GPA
    static Student[] getTopPerformers(Student[] students, int count) {
        List<Student> list = new ArrayList<>();
        for (Student s: students) if (s!=null) list.add(s);
        list.sort((a,b)->Double.compare(b.gpa,a.gpa));
        Student[] out = new Student[Math.min(count,list.size())];
        for (int i=0;i<out.length;i++) out[i]=list.get(i);
        return out;
    }

    // generate school report
    static String generateSchoolReport(Student[] students) {
        StringBuilder sb = new StringBuilder();
        sb.append("School: ").append(Student.schoolName).append("\n");
        sb.append("Total Students: ").append(Student.totalStudents).append("\n");
        sb.append("Class Average: ").append(calculateClassAverage(students)).append("\n");
        Student[] top = getTopPerformers(students,3);
        sb.append("Top Performers:\n");
        for (Student s: top) sb.append(s.studentName).append(" (GPA ").append(s.gpa).append(")\n");
        return sb.toString();
    }
}

/* ----------------- Demo / Main ----------------- */
public class GradeManagerDemo {
    public static void main(String[] args) {
        // Subjects and credits
        Subject s1 = new Subject("MATH","Mathematics",4,"Mr. Rao");
        Subject s2 = new Subject("ENG","English",3,"Ms. Sharma");
        Subject s3 = new Subject("SCI","Science",4,"Mrs. Iyer");
        Subject s4 = new Subject("HIS","History",2,"Mr. Das");

        Map<String,Subject> subjectMap = new HashMap<>();
        subjectMap.put(s1.subjectCode,s1);
        subjectMap.put(s2.subjectCode,s2);
        subjectMap.put(s3.subjectCode,s3);
        subjectMap.put(s4.subjectCode,s4);

        int[] credits = {4,3,4,2}; // order must match students' subject arrays

        // Create 3 students (same subject order)
        Student st1 = new Student("S001","Asha","10A", new String[]{"MATH","ENG","SCI","HIS"});
        Student st2 = new Student("S002","Bala","10A", new String[]{"MATH","ENG","SCI","HIS"});
        Student st3 = new Student("S003","Chetan","10A", new String[]{"MATH","ENG","SCI","HIS"});

        // Add marks (percentages)
        st1.addMarks(0, 92); st1.addMarks(1, 83); st1.addMarks(2, 78); st1.addMarks(3, 69);
        st2.addMarks(0, 68); st2.addMarks(1, 74); st2.addMarks(2, 59); st2.addMarks(3, 80);
        st3.addMarks(0, 45); st3.addMarks(1, 52); st3.addMarks(2, 39); st3.addMarks(3, 41);

        // Calculate GPAs
        st1.calculateGPA(credits);
        st2.calculateGPA(credits);
        st3.calculateGPA(credits);

        // Students array for class report
        Student[] klass = new Student[]{st1,st2,st3};

        // Print individual reports (compact)
        System.out.println(st1.generateReportCard(credits, subjectMap));
        System.out.println(st2.generateReportCard(credits, subjectMap));
        System.out.println(st3.generateReportCard(credits, subjectMap));

        // School-level report
        System.out.println("----- School Report -----");
        System.out.println(SchoolUtils.generateSchoolReport(klass));
    }
}
