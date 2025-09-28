import java.util.Scanner;
public class VehicleTax{
public String vehicleType;
public VehicleTax(String vehicleType){
this.vehicleType=vehicleType;
}
public void showTax(){
switch(vehicleType.toLowerCase()){
case "car": System.out.println("Tax for Car: 5000"); break;
case "bike": System.out.println("Tax for Bike: 2000"); break;
case "bus": System.out.println("Tax for Bus: 8000"); break;
default: System.out.println("Invalid vehicle type");
}
}
public static void main(String[] args){
Scanner sc=new Scanner(System.in);
System.out.print("Enter vehicle type (car/bike/bus): ");
String type=sc.nextLine();
VehicleTax vt=new VehicleTax(type);
vt.showTax();
sc.close();
}
}
