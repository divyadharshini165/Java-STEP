// Abstract class Employee
abstract class Employee {
    protected String name;
    protected double salary;

    public Employee(String name, double salary) {
        this.name = name;
        this.salary = salary;
    }

    // Abstract method
    abstract double calculateBonus();

    // Concrete method (optional)
    public void displayEmployeeInfo() {
        System.out.println("Employee Name: " + name);
        System.out.println("Salary: " + salary);
    }
}

// Interface Payable
interface Payable {
    void generatePaySlip();
}

// Manager class extending Employee and implementing Payable
class Manager extends Employee implements Payable {
    private String department;

    public Manager(String name, double salary, String department) {
        super(name, salary);
        this.department = department;
    }

    @Override
    double calculateBonus() {
        // Example: 10% of salary as bonus
        return salary * 0.10;
    }

    @Override
    public void generatePaySlip() {
        System.out.println("Pay Slip for Manager: " + name);
        System.out.println("Department: " + department);
        System.out.println("Salary: " + salary);
        System.out.println("Bonus: " + calculateBonus());
        System.out.println("Total Pay: " + (salary + calculateBonus()));
    }
}

// Test class
public class PayrollDemo {
    public static void main(String[] args) {
        Manager mgr = new Manager("Alice Johnson", 80000, "IT");

        mgr.displayEmployeeInfo();   // concrete method from abstract class
        System.out.println("Calculated Bonus: " + mgr.calculateBonus()); // abstract method
        mgr.generatePaySlip();       // interface method
    }
}
