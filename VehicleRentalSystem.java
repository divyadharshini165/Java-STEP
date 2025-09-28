class Vehicle {
    // Instance variables (unique per vehicle)
    private String vehicleId;
    private String brand;
    private String model;
    private double rentPerDay;
    private boolean isAvailable;
    private int totalRentalDays; // rental history for this vehicle

    // Static variables (shared across all vehicles)
    private static int totalVehicles = 0;
    private static double totalRevenue = 0.0;
    private static String companyName = "Default Rentals";
    private static int rentalDays = 0; // total rental days across all vehicles

    // Constructor
    public Vehicle(String vehicleId, String brand, String model, double rentPerDay) {
        this.vehicleId = vehicleId;
        this.brand = brand;
        this.model = model;
        this.rentPerDay = rentPerDay;
        this.isAvailable = true;
        this.totalRentalDays = 0;
        totalVehicles++;
    }

    // Instance method: Rent a vehicle
    public void rentVehicle(int days) {
        if (!isAvailable) {
            System.out.println("Vehicle " + vehicleId + " is not available for rent!");
            return;
        }
        double rent = calculateRent(days);
        isAvailable = false; // mark vehicle unavailable
        totalRentalDays += days;
        rentalDays += days;
        System.out.println(vehicleId + " rented for " + days + " days. Total Rent: " + rent);
    }

    // Instance method: Return a vehicle
    public void returnVehicle() {
        if (isAvailable) {
            System.out.println("Vehicle " + vehicleId + " was not rented.");
        } else {
            isAvailable = true;
            System.out.println(vehicleId + " has been returned and is now available.");
        }
    }

    // Instance method: Calculate rent and update static revenue
    public double calculateRent(int days) {
        double rent = rentPerDay * days;
        totalRevenue += rent; // update company-wide revenue
        return rent;
    }

    // Instance method: Display vehicle info
    public void displayVehicleInfo() {
        System.out.println("Vehicle ID: " + vehicleId);
        System.out.println("Brand: " + brand);
        System.out.println("Model: " + model);
        System.out.println("Rent/Day: " + rentPerDay);
        System.out.println("Availability: " + (isAvailable ? "Available" : "Rented"));
        System.out.println("Total Rental Days (this vehicle): " + totalRentalDays);
        System.out.println("-----------------------------");
    }

    // Static methods
    public static void setCompanyName(String name) {
        companyName = name;
    }

    public static double getTotalRevenue() {
        return totalRevenue;
    }

    public static double getAverageRentPerDay() {
        if (rentalDays == 0) return 0;
        return totalRevenue / rentalDays;
    }

    public static void displayCompanyStats() {
        System.out.println("=== " + companyName + " Stats ===");
        System.out.println("Total Vehicles: " + totalVehicles);
        System.out.println("Total Revenue: " + totalRevenue);
        System.out.println("Total Rental Days: " + rentalDays);
        System.out.println("Average Rent/Day: " + getAverageRentPerDay());
        System.out.println("=============================");
    }
}

public class VehicleRentalSystem {
    public static void main(String[] args) {
        // Set company name (static)
        Vehicle.setCompanyName("SuperDrive Rentals");

        // Create vehicle objects (instance)
        Vehicle v1 = new Vehicle("V001", "Toyota", "Corolla", 1500);
        Vehicle v2 = new Vehicle("V002", "Honda", "Civic", 1800);
        Vehicle v3 = new Vehicle("V003", "Tesla", "Model 3", 3000);

        // Renting vehicles (instance methods)
        v1.rentVehicle(3);
        v2.rentVehicle(5);

        // Returning vehicles
        v1.returnVehicle();

        // Show individual vehicle details
        System.out.println("\n--- Vehicle Information ---");
        v1.displayVehicleInfo();
        v2.displayVehicleInfo();
        v3.displayVehicleInfo();

        // Show company-wide stats (static methods)
        System.out.println("\n--- Company Stats ---");
        Vehicle.displayCompanyStats();

        // Demonstrating static vs instance
        System.out.println("\n=== Static vs Instance Demo ===");
        System.out.println("v1 is unique with ID: " + "V001");
        System.out.println("v2 is unique with ID: " + "V002");
        System.out.println("But both share the same company: " + "SuperDrive Rentals");
        System.out.println("Total Revenue (shared): " + Vehicle.getTotalRevenue());
    }
}
