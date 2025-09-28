class Vehicle {
    protected String make;
    protected String model;
    protected int year;
    protected double fuelLevel;

    public Vehicle(String make, String model, int year, double fuelLevel) {
        this.make = make;
        this.model = model;
        this.year = year;
        this.fuelLevel = fuelLevel;
    }

    public void startVehicle() {
        System.out.println(make + " " + model + " started.");
    }

    public void stopVehicle() {
        System.out.println(make + " " + model + " stopped.");
    }

    public void refuel(double amount) {
        fuelLevel += amount;
        System.out.println(make + " " + model + " refueled. Fuel level: " + fuelLevel);
    }

    public void displayVehicleInfo() {
        System.out.println(year + " " + make + " " + model + " | Fuel Level: " + fuelLevel);
    }
}

class Car extends Vehicle {
    public Car(String make, String model, int year, double fuelLevel) {
        super(make, model, year, fuelLevel);
    }

    @Override
    public void startVehicle() {
        System.out.println("Car " + make + " " + model + " is ready to drive!");
    }
}

class Truck extends Vehicle {
    public Truck(String make, String model, int year, double fuelLevel) {
        super(make, model, year, fuelLevel);
    }

    @Override
    public void startVehicle() {
        System.out.println("Truck " + make + " " + model + " is hauling cargo!");
    }
}

class Motorcycle extends Vehicle {
    public Motorcycle(String make, String model, int year, double fuelLevel) {
        super(make, model, year, fuelLevel);
    }

    @Override
    public void startVehicle() {
        System.out.println("Motorcycle " + make + " " + model + " is revving up!");
    }
}

public class VehicleDemo {
    public static void main(String[] args) {
        Vehicle car = new Car("Toyota", "Corolla", 2020, 50);
        Vehicle truck = new Truck("Volvo", "FH16", 2019, 80);
        Vehicle bike = new Motorcycle("Yamaha", "R15", 2022, 20);

        Vehicle[] vehicles = {car, truck, bike};

        for (Vehicle v : vehicles) {
            v.displayVehicleInfo();
            v.startVehicle();
            v.refuel(10);
            v.stopVehicle();
            System.out.println();
        }
    }
}
