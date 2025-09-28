import java.util.Scanner;
public class GameSelector{
public int choice;
public GameSelector(int choice){
this.choice=choice;
}
public void selectGame(){
switch(choice){
case 1: System.out.println("You selected: Football"); break;
case 2: System.out.println("You selected: Cricket"); break;
case 3: System.out.println("You selected: Basketball"); break;
case 4: System.out.println("You selected: Tennis"); break;
case 5: System.out.println("You selected: Chess"); break;
default: System.out.println("Invalid choice, please select between 1-5");
}
}
public static void main(String[] args){
Scanner sc=new Scanner(System.in);
System.out.print("Enter game number (1-5): ");
int num=sc.nextInt();
GameSelector gs=new GameSelector(num);
gs.selectGame();
sc.close();
}
}
