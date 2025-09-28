class Employee{
    protected double baseSalary;
    Employee(double baseSalary){
        this.baseSalary=baseSalary;
    }
    public double calculateSalary(){
        return baseSalary;
    }
}
class Manager extends Employee{
    private double bonus;
    Manager(double baseSalary,double bonus){
        super(baseSalary);
        this.bonus=bonus;
    }
    @Override
    public double calculateSalary(){
        return baseSalary+bonus;
    }
}
class Developer extends Employee{
    private double overtimePay;
    Developer(double baseSalary,double overtimePay){
        super(baseSalary);
        this.overtimePay=overtimePay;
    }
    @Override
    public double calculateSalary(){
        return baseSalary+overtimePay;
    }
}
public class EmployeeSalaryDemo{
    public static void main(String[] args){
        Manager m=new Manager(50000,10000);
        Developer d=new Developer(40000,5000);
        System.out.println("Manager Salary:"+m.calculateSalary());
        System.out.println("Developer Salary:"+d.calculateSalary());
    }
}
