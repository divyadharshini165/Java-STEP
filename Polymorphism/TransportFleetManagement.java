// Parent class
abstract class Vehicle {
    protected String vehicleId;

    public Vehicle(String vehicleId) {
        this.vehicleId = vehicleId;
    }

    // Method to be overridden
    public abstract void dispatch();
}

// Bus subclass
class Bus extends Vehicle {
    private String route;
    private int passengerCapacity;

    public Bus(String vehicleId, String route, int passengerCapacity) {
        super(vehicleId);
        this.route = route;
        this.passengerCapacity = passengerCapacity;
    }

    @Override
    public void dispatch() {
        System.out.println(" Bus " + vehicleId + " dispatched.");
        System.out.println("Route: " + route + ", Passenger Capacity: " + passengerCapacity);
        System.out.println("--------------------------------");
    }
}

// Taxi subclass
class Taxi extends Vehicle {
    private double farePerKm;

    public Taxi(String vehicleId, double farePerKm) {
        super(vehicleId);
        this.farePerKm = farePerKm;
    }

    @Override
    public void dispatch() {
        System.out.println(" Taxi " + vehicleId + " dispatched.");
        System.out.println("Provides door-to-door service. Fare per km: ₹" + farePerKm);
        System.out.println("--------------------------------");
    }
}

// Train subclass
class Train extends Vehicle {
    private int cars;
    private String schedule;

    public Train(String vehicleId, int cars, String schedule) {
        super(vehicleId);
        this.cars = cars;
        this.schedule = schedule;
    }

    @Override
    public void dispatch() {
        System.out.println(" Train " + vehicleId + " dispatched.");
        System.out.println("Cars: " + cars + ", Schedule: " + schedule);
        System.out.println("--------------------------------");
    }
}

// Bike subclass
class Bike extends Vehicle {
    private boolean electric;

    public Bike(String vehicleId, boolean electric) {
        super(vehicleId);
        this.electric = electric;
    }

    @Override
    public void dispatch() {
        System.out.println(" Bike " + vehicleId + " dispatched.");
        System.out.println("Eco-friendly trip. Electric: " + (electric ? "Yes" : "No"));
        System.out.println("--------------------------------");
    }
}

// Main system
public class TransportFleetManagement {
    public static void main(String[] args) {
        Vehicle[] fleet = new Vehicle[4];
        fleet[0] = new Bus("B101", "Downtown - Airport", 50);
        fleet[1] = new Taxi("T202", 15.5);
        fleet[2] = new Train("TR303", 12, "Every 30 minutes");
        fleet[3] = new Bike("BK404", true);

        // Dynamic Method Dispatch in action
        for (Vehicle v : fleet) {
            v.dispatch(); // runtime decides which method to call
        }
    }
}


