import java.util.Scanner;
public class NameReader{
public static void main(String[] args){
Scanner sc=new Scanner(System.in);
String name="";
while(true){
System.out.print("Enter a name (type 'exit' to stop): ");
name=sc.nextLine();
if(name.equalsIgnoreCase("exit")){
break;
}
System.out.println("You entered: "+name);
}
System.out.println("Program ended.");
sc.close();
}
}
