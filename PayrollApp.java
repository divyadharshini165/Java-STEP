// PayrollApp.java - Simplified Employee Payroll & Attendance System

import java.util.*;

class Employee {
    String empId, empName, department, designation, empType; // empType: FullTime/PartTime/Contract
    double baseSalary;            // monthly for FullTime/Contract, daily for PartTime
    String joinDate;
    boolean[] attendance = new boolean[30]; // 30-day month
    int leavesRequested = 0;
    double performanceScore = 0.0; // 0.0 - 5.0 scale

    static int totalEmployees = 0;
    static int workingDaysPerMonth = 26; // default working days

    Employee(String id, String name, String dept, String desig, String type, double base, String join) {
        empId = id; empName = name; department = dept; designation = desig;
        empType = type; baseSalary = base; joinDate = join;
        totalEmployees++;
    }

    void markAttendance(int day, boolean present) {
        if (day < 1 || day > attendance.length) return;
        attendance[day-1] = present;
    }

    void requestLeave(int days) {
        leavesRequested += days;
        System.out.println(empName + " requested " + days + " day(s) leave.");
    }

    double calculatePresentDays() {
        int cnt = 0;
        for (boolean b : attendance) if (b) cnt++;
        return cnt;
    }

    // salary calculation by type
    double calculateSalary() {
        double present = calculatePresentDays();
        switch (empType) {
            case "FullTime":
                // pro-rate by days present, plus performance bonus
                double prorated = baseSalary * (present / workingDaysPerMonth);
                double bonus = calculateBonus(prorated);
                return roundTwo(prorated + bonus);
            case "PartTime":
                // baseSalary is daily wage
                return roundTwo(baseSalary * present);
            case "Contract":
                // fixed monthly if attended at least half working days, else pro-rated
                if (present >= workingDaysPerMonth / 2.0) return roundTwo(baseSalary + calculateBonus(baseSalary));
                else return roundTwo(baseSalary * (present / workingDaysPerMonth));
            default:
                return 0.0;
        }
    }

    double calculateBonus(double salaryComponent) {
        // simple performance-based bonus: score/5 * 10% of salaryComponent
        double bonusPercent = (performanceScore / 5.0) * 0.10;
        return roundTwo(salaryComponent * bonusPercent);
    }

    String generatePaySlip() {
        double present = calculatePresentDays();
        double salary = calculateSalary();
        double bonus = calculateBonus(empType.equals("PartTime") ? baseSalary * present : baseSalary);
        return String.format("Payslip for %s (%s)\nType: %s | Present: %.0f days\nBase: %.2f | Bonus: %.2f\nNet Pay: %.2f\n",
                empName, empId, empType, present, baseSalary, bonus, salary);
    }

    static double roundTwo(double v) { return Math.round(v * 100.0) / 100.0; }
}

class Department {
    String deptId, deptName;
    Employee manager;
    List<Employee> employees = new ArrayList<>();
    double budget;

    Department(String id, String name, Employee mgr, double bud) {
        deptId = id; deptName = name; manager = mgr; budget = bud;
        if (mgr != null) addEmployee(mgr);
    }

    void addEmployee(Employee e) {
        employees.add(e);
    }

    double getDepartmentExpense() {
        double sum = 0;
        for (Employee e : employees) sum += e.calculateSalary();
        return Employee.roundTwo(sum);
    }
}

class Company {
    static String companyName = "TechSolutions Pvt Ltd";
    static double totalSalaryExpense = 0.0;
    List<Employee> allEmployees = new ArrayList<>();
    List<Department> departments = new ArrayList<>();

    void addEmployee(Employee e) { allEmployees.add(e); }
    void addDepartment(Department d) { departments.add(d); }

    // calculates payroll for all employees and updates totalSalaryExpense
    double calculateCompanyPayroll() {
        double sum = 0;
        for (Employee e : allEmployees) sum += e.calculateSalary();
        totalSalaryExpense = Employee.roundTwo(sum);
        return totalSalaryExpense;
    }

    Map<String, Double> getDepartmentWiseExpenses() {
        Map<String, Double> map = new HashMap<>();
        for (Department d : departments) map.put(d.deptName, d.getDepartmentExpense());
        return map;
    }

    // attendance report: percent present per employee
    void getAttendanceReport() {
        System.out.println("Attendance Report:");
        for (Employee e : allEmployees) {
            double present = e.calculatePresentDays();
            double pct = Employee.roundTwo((present / e.attendance.length) * 100.0);
            System.out.println(e.empName + " (" + e.empId + "): " + (int)present + "/30 days (" + pct + "%)");
        }
    }
}

public class PayrollApp {
    public static void main(String[] args) {
        Company comp = new Company();

        // Create employees
        Employee e1 = new Employee("E001","Asha","IT","Developer","FullTime",50000,"2023-01-10");
        Employee e2 = new Employee("E002","Balu","HR","Coordinator","PartTime",800,"2024-03-05"); // 800/day
        Employee e3 = new Employee("E003","Chetan","IT","Contractor","Contract",60000,"2024-06-01");

        // set performance
        e1.performanceScore = 4.5;
        e2.performanceScore = 3.2;
        e3.performanceScore = 4.8;

        // mark some attendance (days 1..30). For brevity mark first 20 days present for e1, 15 days for e2, 18 for e3
        for (int d=1; d<=20; d++) e1.markAttendance(d,true);
        for (int d=1; d<=15; d++) e2.markAttendance(d,true);
        for (int d=1; d<=18; d++) e3.markAttendance(d,true);

        // some leaves and requests
        e1.requestLeave(2);
        e2.requestLeave(1);

        // Departments
        Department dIT = new Department("D01","IT", e1, 500000);
        dIT.addEmployee(e3);
        Department dHR = new Department("D02","HR", e2, 200000);

        // register with company
        comp.addEmployee(e1); comp.addEmployee(e2); comp.addEmployee(e3);
        comp.addDepartment(dIT); comp.addDepartment(dHR);

        // Payroll & reports
        System.out.println("=== PAYSLIPS ===");
        System.out.println(e1.generatePaySlip());
        System.out.println(e2.generatePaySlip());
        System.out.println(e3.generatePaySlip());

        System.out.println("Company Payroll Total: " + comp.calculateCompanyPayroll());
        System.out.println("Dept Expenses: " + comp.getDepartmentWiseExpenses());
        comp.getAttendanceReport();
    }
}
