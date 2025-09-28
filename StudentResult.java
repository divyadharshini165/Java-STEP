import java.util.Scanner;
public class StudentResult{
private int m1,m2,m3;
public StudentResult(int m1,int m2,int m3){
this.m1=m1;
this.m2=m2;
this.m3=m3;
}
public void printResult(){
double avg=(m1+m2+m3)/3.0;
System.out.println("Marks: "+m1+", "+m2+", "+m3);
System.out.println("Average: "+avg);
if(avg>=50){
System.out.println("Result: Pass");
}else{
System.out.println("Result: Fail");
}
}
public static void main(String[] args){
Scanner sc=new Scanner(System.in);
System.out.print("Enter marks in Subject 1: ");
int m1=sc.nextInt();
System.out.print("Enter marks in Subject 2: ");
int m2=sc.nextInt();
System.out.print("Enter marks in Subject 3: ");
int m3=sc.nextInt();
StudentResult sr=new StudentResult(m1,m2,m3);
sr.printResult();
sc.close();
}
}
