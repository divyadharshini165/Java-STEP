// FleetApp.java - Simplified Vehicle Fleet Management System

import java.util.*;

abstract class Vehicle {
    String vehicleId, brand, model, fuelType;
    int year;
    double mileage; // total km
    String currentStatus; // "Idle", "OnTrip", "Maintenance"
    double fuelConsumedLiters = 0.0; // cumulative
    double value; // vehicle monetary value

    // static fleet-wide
    static int totalVehicles = 0;
    static double fleetValue = 0.0;
    static String companyName = "TransCo";
    static double totalFuelConsumption = 0.0;

    Vehicle(String id, String brand, String model, int year, double mileage, String fuelType, double value) {
        this.vehicleId = id; this.brand = brand; this.model = model; this.year = year;
        this.mileage = mileage; this.fuelType = fuelType; this.value = value;
        this.currentStatus = "Idle";
        totalVehicles++; fleetValue += value;
    }

    // update odometer and fuel
    void updateMileage(double km, double litersUsed) {
        mileage += km;
        fuelConsumedLiters += litersUsed;
        totalFuelConsumption += litersUsed;
    }

    // assign driver
    void assignDriver(Driver d) {
        d.assignedVehicle = this;
        currentStatus = "Idle";
        System.out.println(d.driverName + " assigned to " + vehicleId);
    }

    // schedule maintenance toggles status; simple implementation
    void scheduleMaintenance() {
        currentStatus = "Maintenance";
        System.out.println(vehicleId + " scheduled for maintenance.");
    }

    // whether service due (every 10000 km)
    boolean checkServiceDue() {
        return mileage % 10000 > 9000; // simple: near multiple of 10000
    }

    // abstract running cost per km (depends on type)
    abstract double calculateRunningCostPerKm();

    // trip simulation: km, avgLitersPerKm, driver increments trips
    void performTrip(double km, double avgLitersPerKm, Driver driver) {
        if (currentStatus.equals("Maintenance")) { System.out.println(vehicleId + " under maintenance."); return; }
        currentStatus = "OnTrip";
        updateMileage(km, km * avgLitersPerKm);
        driver.totalTrips++;
        System.out.printf("Trip done: %s | km: %.1f | fuelL: %.2f | driver: %s\n", vehicleId, km, km*avgLitersPerKm, driver.driverName);
        currentStatus = "Idle";
    }

    public String toString() {
        return vehicleId + " [" + getClass().getSimpleName() + "] " + brand + " " + model + " | km:" + (int)mileage
                + " | status:" + currentStatus + " | fuelL:" + String.format("%.2f", fuelConsumedLiters);
    }
}

class Car extends Vehicle {
    int seats;
    Car(String id,String brand,String model,int year,double mileage,String fuelType,double value,int seats){
        super(id,brand,model,year,mileage,fuelType,value); this.seats = seats;
    }
    double calculateRunningCostPerKm(){ return 0.12; } // currency per km
}

class Bus extends Vehicle {
    int seatingCapacity;
    Bus(String id,String brand,String model,int year,double mileage,String fuelType,double value,int cap){
        super(id,brand,model,year,mileage,fuelType,value); this.seatingCapacity = cap;
    }
    double calculateRunningCostPerKm(){ return 0.5; }
}

class Truck extends Vehicle {
    double loadCapacityTons;
    Truck(String id,String brand,String model,int year,double mileage,String fuelType,double value,double tons){
        super(id,brand,model,year,mileage,fuelType,value); this.loadCapacityTons = tons;
    }
    double calculateRunningCostPerKm(){ return 0.8; }
}

class Driver {
    String driverId, driverName, licenseType;
    Vehicle assignedVehicle = null;
    int totalTrips = 0;
    Driver(String id,String name,String licence){ driverId=id; driverName=name; licenseType=licence;}
    public String toString(){ return driverId + " - " + driverName + " | trips:" + totalTrips + (assignedVehicle==null?"":" | veh:"+assignedVehicle.vehicleId); }
}

class FleetManager {
    List<Vehicle> vehicles = new ArrayList<>();
    List<Driver> drivers = new ArrayList<>();
    // add vehicle/driver
    void addVehicle(Vehicle v){ vehicles.add(v); }
    void addDriver(Driver d){ drivers.add(d); }

    // static summaries
    static double getFleetUtilization(List<Vehicle> vehicles) {
        if (vehicles.isEmpty()) return 0.0;
        long inUse = vehicles.stream().filter(v -> v.currentStatus.equals("OnTrip")).count();
        return (inUse * 100.0) / vehicles.size();
    }

    static double calculateTotalMaintenanceCost(List<Vehicle> vehicles) {
        // naive cost: vehicles near service due cost more; base service cost = value*0.01
        double sum = 0.0;
        for (Vehicle v: vehicles) {
            if (v.checkServiceDue()) sum += v.value * 0.01 + 100; else sum += 50;
        }
        return Math.round(sum*100.0)/100.0;
    }

    static Map<String, List<Vehicle>> getVehiclesByType(List<Vehicle> vehicles) {
        Map<String, List<Vehicle>> map = new HashMap<>();
        for (Vehicle v: vehicles) {
            map.computeIfAbsent(v.getClass().getSimpleName(), k->new ArrayList<>()).add(v);
        }
        return map;
    }

    // fuel consumption report
    double totalFuelConsumed() {
        return vehicles.stream().mapToDouble(v->v.fuelConsumedLiters).sum();
    }
}

/* ===== Demo main ===== */
public class FleetApp {
    public static void main(String[] args) {
        FleetManager fm = new FleetManager();

        // create vehicles
        Car c1 = new Car("V100","Toyota","Etios",2018,48000,"Petrol",700000,5);
        Bus b1 = new Bus("V200","Volvo","B9",2016,150000,"Diesel",5000000,40);
        Truck t1 = new Truck("V300","Tata","Ace",2019,80000,"Diesel",1200000,5.0);

        fm.addVehicle(c1); fm.addVehicle(b1); fm.addVehicle(t1);

        // create drivers
        Driver d1 = new Driver("D01","Ravi","Light");
        Driver d2 = new Driver("D02","Kumar","Heavy");
        fm.addDriver(d1); fm.addDriver(d2);

        // assign drivers
        c1.assignDriver(d1);
        t1.assignDriver(d2);

        // perform trips
        c1.performTrip(120.5, 0.08, d1); // car 0.08 L/km
        t1.performTrip(300, 0.25, d2);
        b1.performTrip(0, 0.18, d2); // bus not assigned driver but can run (demo)

        // update mileage manually
        b1.updateMileage(200, 36);

        // schedule maintenance and check service
        if (c1.checkServiceDue()) c1.scheduleMaintenance();
        if (b1.checkServiceDue()) b1.scheduleMaintenance();

        // reports
        System.out.println("\n=== Fleet ===");
        fm.vehicles.forEach(v->System.out.println(v));

        System.out.println("\n=== Drivers ===");
        fm.drivers.forEach(d->System.out.println(d));

        System.out.println("\n=== Fleet Summary ===");
        System.out.println("Total Vehicles: " + Vehicle.totalVehicles);
        System.out.println("Fleet Value: " + Vehicle.fleetValue);
        System.out.println("Total Fuel Consumed (L): " + String.format("%.2f", Vehicle.totalFuelConsumption));
        System.out.println("Fleet Utilization (% on trip): " + String.format("%.2f", FleetManager.getFleetUtilization(fm.vehicles)));
        System.out.println("Estimated Maintenance Cost: " + FleetManager.calculateTotalMaintenanceCost(fm.vehicles));
        System.out.println("Vehicles by Type: " + FleetManager.getVehiclesByType(fm.vehicles).keySet());
    }
}
