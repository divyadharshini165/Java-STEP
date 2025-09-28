class Employee {
    // Private instance variables
    private String empId;
    private String empName;
    private String department;
    private double baseSalary;
    private String empType;

    // Static variable
    private static int totalEmployees = 0;
    private static int empCounter = 0;

    // Constructor for Full-time Employee
    public Employee(String empName, String department, double baseSalary) {
        this.empId = generateEmpId();
        this.empName = empName;
        this.department = department;
        this.baseSalary = baseSalary;
        this.empType = "Full-Time";
        totalEmployees++;
    }

    // Constructor for Part-time Employee
    public Employee(String empName, String department, double hourlyRate, int hours) {
        this.empId = generateEmpId();
        this.empName = empName;
        this.department = department;
        this.baseSalary = hourlyRate * hours; // Store computed salary
        this.empType = "Part-Time";
        totalEmployees++;
    }

    // Constructor for Contract Employee
    public Employee(String empName, String department, double contractAmount, boolean isContract) {
        this.empId = generateEmpId();
        this.empName = empName;
        this.department = department;
        this.baseSalary = contractAmount; // Fixed salary
        this.empType = "Contract";
        totalEmployees++;
    }

    // Overloaded calculateSalary methods
    public double calculateSalary(double bonus) { // Full-time
        if (empType.equals("Full-Time")) {
            return baseSalary + bonus;
        }
        return baseSalary;
    }

    public double calculateSalary(double hourlyRate, int hours) { // Part-time
        if (empType.equals("Part-Time")) {
            return hourlyRate * hours;
        }
        return baseSalary;
    }

    public double calculateSalary() { // Contract (fixed)
        if (empType.equals("Contract")) {
            return baseSalary;
        }
        return baseSalary;
    }

    // Overloaded calculateTax methods
    public double calculateTax(double salary) { // Full-time: 10%
        if (empType.equals("Full-Time")) {
            return salary * 0.10;
        }
        return 0;
    }

    public double calculateTax(double salary, int dummy) { // Part-time: 5%
        if (empType.equals("Part-Time")) {
            return salary * 0.05;
        }
        return 0;
    }

    public double calculateTax() { // Contract: 2%
        if (empType.equals("Contract")) {
            return baseSalary * 0.02;
        }
        return 0;
    }

    // Generate Pay Slip
    public void generatePaySlip() {
        System.out.println("----- Pay Slip -----");
        System.out.println("Employee ID: " + empId);
        System.out.println("Name: " + empName);
        System.out.println("Department: " + department);
        System.out.println("Type: " + empType);

        double salary = 0;
        double tax = 0;

        switch (empType) {
            case "Full-Time":
                salary = calculateSalary(5000); // adding bonus
                tax = calculateTax(salary);
                break;
            case "Part-Time":
                salary = calculateSalary(baseSalary, 1); // Already stored
                tax = calculateTax(salary, 0);
                break;
            case "Contract":
                salary = calculateSalary();
                tax = calculateTax();
                break;
        }

        System.out.println("Gross Salary: " + salary);
        System.out.println("Tax: " + tax);
        System.out.println("Net Salary: " + (salary - tax));
        System.out.println("---------------------\n");
    }

    // Display Employee Info
    public void displayEmployeeInfo() {
        System.out.println("[" + empId + "] " + empName + " (" + empType + "), Dept: " + department);
    }

    // Static method: total employees
    public static int getTotalEmployees() {
        return totalEmployees;
    }

    // Static payroll report
    public static void generatePayrollReport(Employee[] employees, int count) {
        System.out.println("==== Company Payroll Report ====");
        for (int i = 0; i < count; i++) {
            employees[i].generatePaySlip();
        }
        System.out.println("Total Employees: " + getTotalEmployees());
        System.out.println("===============================");
    }

    // Helper: Generate Employee ID
    private static String generateEmpId() {
        empCounter++;
        return String.format("EMP%03d", empCounter);
    }
}

public class PayrollSystem {
    public static void main(String[] args) {
        // Array of employees (no collections)
        Employee[] employees = new Employee[5];
        int empCount = 0;

        // Creating different employees
        employees[empCount++] = new Employee("Alice", "IT", 50000);              // Full-time
        employees[empCount++] = new Employee("Bob", "HR", 200, 80);              // Part-time
        employees[empCount++] = new Employee("Charlie", "Finance", 60000, true); // Contract

        // Display Info
        System.out.println("=== Employee Information ===");
        for (int i = 0; i < empCount; i++) {
            employees[i].displayEmployeeInfo();
        }

        System.out.println("\n=== Generating Payroll Report ===");
        Employee.generatePayrollReport(employees, empCount);
    }
}
