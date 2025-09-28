import java.util.Scanner;
public class LoanEligibility{
private int age;
private double salary;
public LoanEligibility(int age,double salary){
this.age=age;
this.salary=salary;
}
public void checkEligibility(){
System.out.println("Age: "+age);
System.out.println("Salary: "+salary);
if(age>=21&&salary>=20000){
System.out.println("Eligible for Loan");
}else{
System.out.println("Not Eligible for Loan");
}
}
public static void main(String[] args){
Scanner sc=new Scanner(System.in);
System.out.print("Enter age: ");
int age=sc.nextInt();
System.out.print("Enter salary: ");
double salary=sc.nextDouble();
LoanEligibility loan=new LoanEligibility(age,salary);
loan.checkEligibility();
sc.close();
}
}
