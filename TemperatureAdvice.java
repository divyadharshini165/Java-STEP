import java.util.Scanner;
public class TemperatureAdvice{
public static void main(String[] args){
Scanner sc=new Scanner(System.in);
String choice;
do{
System.out.print("Enter temperature:");
int temp=sc.nextInt();
if(temp<15){
System.out.println("It's cold. Wear a jacket.");
}else if(temp<=30){
System.out.println("Weather is pleasant.");
}else{
System.out.println("It's hot. Stay hydrated.");
}
System.out.print("Type quit to stop or any key to continue:");
choice=sc.next();
}while(!choice.equalsIgnoreCase("quit"));
System.out.println("Program ended.");
sc.close();
}
}
