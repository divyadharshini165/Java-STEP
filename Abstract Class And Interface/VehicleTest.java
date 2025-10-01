// Abstract class Vehicle
abstract class Vehicle {
    // Abstract method
    abstract void start();

    // Concrete method
    public void stop() {
        System.out.println("Vehicle has stopped.");
    }
}

// Interface Fuel
interface Fuel {
    void refuel();
}

// Car class extending Vehicle and implementing Fuel
class Car extends Vehicle implements Fuel {
    @Override
    public void start() {
        System.out.println("Car engine started.");
    }

    @Override
    public void refuel() {
        System.out.println("Car is refueling.");
    }
}

// Test class
public class VehicleTest {
    public static void main(String[] args) {
        Car myCar = new Car();

        // Call methods
        myCar.start();    // from abstract class
        myCar.stop();     // concrete method from abstract class
        myCar.refuel();   // from interface
    }
}
