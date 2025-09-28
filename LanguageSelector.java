import java.util.Scanner;
public class LanguageSelector{
public int choice;
public LanguageSelector(int choice){
this.choice=choice;
}
public void selectLanguage(){
switch(choice){
case 1: System.out.println("You selected: Java"); break;
case 2: System.out.println("You selected: Python"); break;
case 3: System.out.println("You selected: C++"); break;
case 4: System.out.println("You selected: JavaScript"); break;
case 5: System.out.println("You selected: C#"); break;
default: System.out.println("Invalid choice");
}
}
public static void main(String[] args){
Scanner sc=new Scanner(System.in);
System.out.println("Select a programming language:");
System.out.println("1. Java");
System.out.println("2. Python");
System.out.println("3. C++");
System.out.println("4. JavaScript");
System.out.println("5. C#");
System.out.print("Enter your choice: ");
int num=sc.nextInt();
LanguageSelector ls=new LanguageSelector(num);
ls.selectLanguage();
sc.close();
}
}
