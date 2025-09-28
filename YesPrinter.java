import java.util.Scanner;
public class YesPrinter{
public static void main(String[] args){
Scanner sc=new Scanner(System.in);
String input="yes";
while(input.equalsIgnoreCase("yes")){
System.out.println("You typed YES!");
System.out.print("Do you want to continue? (yes/no): ");
input=sc.nextLine();
}
System.out.println("Loop ended.");
sc.close();
}
}
