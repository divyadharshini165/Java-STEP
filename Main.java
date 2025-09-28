// Parent class
class Transport {
    int speed;
    int fuel;
    Transport(int speed, int fuel) {
        this.speed = speed;
        this.fuel = fuel;
    }
    void start() {
        System.out.println("Transport started...");
    }
}
class Car extends Transport {
    boolean ac;
    Car(int speed, int fuel, boolean ac) {
        super(speed, fuel);
        this.ac = ac;
    }
    void show() {
        System.out.println("Car: speed=" + speed + " fuel=" + fuel + " AC=" + ac);
    }
}
class Bike extends Transport {
    boolean helmet;

    Bike(int speed, int fuel, boolean helmet) {
        super(speed, fuel);
        this.helmet = helmet;
    }
    void show() {
        System.out.println("Bike: speed=" + speed + " fuel=" + fuel + " Helmet=" + helmet);
    }
}
class Truck extends Transport {
    int load;
    Truck(int speed, int fuel, int load) {
        super(speed, fuel);
        this.load = load;
    }
    void show() {
        System.out.println("Truck: speed=" + speed + " fuel=" + fuel + " Load=" + load);
    }
}
public class Main {
    public static void main(String[] args) {
        Car c = new Car(180, 50, true);
        Bike b = new Bike(120, 15, true);
        Truck t = new Truck(90, 200, 10);
        c.start();
        c.show();
        b.start();
        b.show();
        t.start();
        t.show();
    }
}
