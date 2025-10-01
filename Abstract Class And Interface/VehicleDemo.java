// Abstract class Vehicle
abstract class Vehicle {
    // Abstract method
    public abstract void start();
}

// Subclass Car extending Vehicle
class Car extends Vehicle {
    @Override
    public void start() {
        System.out.println("Car starts with a key or push button.");
    }
}

// Subclass Bike extending Vehicle
class Bike extends Vehicle {
    @Override
    public void start() {
        System.out.println("Bike starts with a kick or self-start button.");
    }
}

// Main class to demonstrate
public class VehicleDemo {
    public static void main(String[] args) {
        // Using Vehicle reference for Car
        Vehicle v1 = new Car();
        v1.start();

        // Using Vehicle reference for Bike
        Vehicle v2 = new Bike();
        v2.start();
    }
}
