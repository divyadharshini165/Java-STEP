import java.util.Scanner;
public class TemperatureCheck{
private int temperature;
public TemperatureCheck(int temperature){
this.temperature=temperature;
}
public void checkWeather(){
if(temperature<25){
System.out.println("Cold");
}else{
System.out.println("Hot");
}
}
public static void main(String[] args){
Scanner sc=new Scanner(System.in);
int temp=sc.nextInt();
TemperatureCheck weather=new TemperatureCheck(temp);
weather.checkWeather();
sc.close();
}
}
