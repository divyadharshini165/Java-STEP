class vehicle{
    void speed(){
        System.out.println("vehicle is running...");
    }
}
class car extends vehicle{
    void speed(){
        System.out.println("car runs at 100km/h");
    }
}
class bike extends vehicle{
    void speed(){
        System.out.println("bike runs at 80km/h");
    }
}
public class vehiclespeed{
    public static void main(String[] args){
        vehicle v1 = new car();
        vehicle v2 = new bike();
        v1.speed();
        v2.speed();
    }
}
