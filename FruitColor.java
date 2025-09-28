import java.util.Scanner;
public class FruitColor{
private String fruit;
public FruitColor(String fruit){
this.fruit=fruit;
}
public void printColor(){
switch(fruit.toLowerCase()){
case "apple": System.out.println("Red"); break;
case "banana": System.out.println("Yellow"); break;
case "grape": System.out.println("Purple"); break;
case "orange": System.out.println("Orange"); break;
case "mango": System.out.println("Yellow/Orange"); break;
case "watermelon": System.out.println("Green outside, Red inside"); break;
default: System.out.println("Color not found for given fruit");
}
}
public static void main(String[] args){
Scanner sc=new Scanner(System.in);
System.out.print("Enter fruit name: ");
String fruitName=sc.nextLine();
FruitColor fc=new FruitColor(fruitName);
fc.printColor();
sc.close();
}
}
