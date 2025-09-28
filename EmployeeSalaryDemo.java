class Employee {
    double baseSalary;

    Employee(double baseSalary) {
        this.baseSalary = baseSalary;
    }

    double calculateSalary() {
        return baseSalary;
    }
}

class Manager extends Employee {
    double bonus;

    Manager(double baseSalary, double bonus) {
        super(baseSalary);
        this.bonus = bonus;
    }

    @Override
    double calculateSalary() {
        return baseSalary + bonus;
    }
}

class Developer extends Employee {
    double overtimePay;

    Developer(double baseSalary, double overtimePay) {
        super(baseSalary);
        this.overtimePay = overtimePay;
    }

    @Override
    double calculateSalary() {
        return baseSalary + overtimePay;
    }
}

public class EmployeeSalaryDemo {
    public static void main(String[] args) {
        // Predefined employees
        Employee e1 = new Manager(50000, 10000);   // base salary + bonus
        Employee e2 = new Developer(40000, 8000); // base salary + overtime

        // Display results
        System.out.println("Manager Salary: " + e1.calculateSalary());
        System.out.println("Developer Salary: " + e2.calculateSalary());
    }
}
