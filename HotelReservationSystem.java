import java.io.*;
import java.util.*;

// ================== Room Class ==================
class Room {
    private int roomNumber;
    private String type;  // Single, Double, Suite
    private double price;
    private boolean available;

    public Room(int roomNumber, String type, double price) {
        this.roomNumber = roomNumber;
        this.type = type;
        this.price = price;
        this.available = true;
    }

    public int getRoomNumber() { return roomNumber; }
    public String getType() { return type; }
    public double getPrice() { return price; }
    public boolean isAvailable() { return available; }
    public void setAvailable(boolean available) { this.available = available; }

    @Override
    public String toString() {
        return "Room " + roomNumber + " (" + type + ", ₹" + price + ", Available: " + available + ")";
    }
}

// ================== Customer Class ==================
class Customer {
    private String name;
    private String contact;

    public Customer(String name, String contact) {
        this.name = name;
        this.contact = contact;
    }
kk
    public String getName() { return name; }
    public String getContact() { return contact; }

    @Override
    public String toString() {
        return name + " (" + contact + ")";
    }
}

// ================== Reservation Class ==================
class Reservation implements Serializable {
    private Customer customer;
    private Room room;
    private String date;

    public Reservation(Customer customer, Room room, String date) {
        this.customer = customer;
        this.room = room;
        this.date = date;
    }

    public Customer getCustomer() { return customer; }
    public Room getRoom() { return room; }
    public String getDate() { return date; }

    @Override
    public String toString() {
        return "Reservation: " + customer + " -> " + room + " on " + date;
    }
}

// ================== Hotel Class ==================
class Hotel {
    private List<Room> rooms;
    private List<Reservation> reservations;
    private final String FILE_NAME = "reservations.dat";

    public Hotel() {
        rooms = new ArrayList<>();
        reservations = new ArrayList<>();
        loadReservations();
    }

    // Add rooms to hotel
    public void addRoom(Room room) {
        rooms.add(room);                      
    }

    // Search available rooms by date
    public void searchAvailableRooms(String date) {
        System.out.println("Available rooms on " + date + ":");
        for (Room r : rooms) {
            boolean booked = false;
            for (Reservation res : reservations) {
                if (res.getRoom().getRoomNumber() == r.getRoomNumber() && res.getDate().equals(date)) {
                    booked = true;
                    break;
                }
            }
            if (!booked) {
                System.out.println(r);
            }
        }
    }

    // Book room
    public void bookRoom(Customer customer, int roomNumber, String date) {
        try {
            Room room = null;
            for (Room r : rooms) {
                if (r.getRoomNumber() == roomNumber) {
                    room = r;
                    break;
                }
            }

            if (room == null) {
                throw new Exception("Room not found!");
            }

            // Check if already booked
            for (Reservation res : reservations) {
                if (res.getRoom().getRoomNumber() == roomNumber && res.getDate().equals(date)) {
                    throw new Exception("Room already booked for this date.");
                }
            }

            Reservation reservation = new Reservation(customer, room, date);
            reservations.add(reservation);
            saveReservations();
            System.out.println("✅ Reservation successful: " + reservation);

        } catch (Exception e) {
            System.out.println("❌ Booking failed: " + e.getMessage());
        }
    }

    // Cancel reservation
    public void cancelReservation(String customerName, int roomNumber, String date) {
        Iterator<Reservation> it = reservations.iterator();
        while (it.hasNext()) {
            Reservation res = it.next();
            if (res.getCustomer().getName().equalsIgnoreCase(customerName)
                    && res.getRoom().getRoomNumber() == roomNumber
                    && res.getDate().equals(date)) {
                it.remove();
                saveReservations();
                System.out.println("🗑 Reservation canceled: " + res);
                return;
            }
        }
        System.out.println("❌ No matching reservation found.");
    }

    // Show all reservations
    public void showReservations() {
        if (reservations.isEmpty()) {
            System.out.println("📭 No reservations found.");
        } else {
            for (Reservation res : reservations) {
                System.out.println(res);
            }
        }
    }

    // Save reservations to file
    private void saveReservations() {
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(FILE_NAME))) {
            oos.writeObject(reservations);
        } catch (IOException e) {
            System.out.println("⚠ Error saving reservations: " + e.getMessage());
        }
    }

    // Load reservations from file
    private void loadReservations() {
        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(FILE_NAME))) {
            reservations = (List<Reservation>) ois.readObject();
        } catch (Exception e) {
            reservations = new ArrayList<>();
        }
    }
}

// ================== Main ==================
public class HotelReservationSystem {
    public static void main(String[] args) {
        Hotel hotel = new Hotel();

        // Add some rooms
        hotel.addRoom(new Room(101, "Single", 2000));
        hotel.addRoom(new Room(102, "Double", 3000));
        hotel.addRoom(new Room(201, "Suite", 5000));

        // Customers
        Customer c1 = new Customer("Velu", "9876543210");
        Customer c2 = new Customer("Anita", "9123456780");

        // Search available rooms
        hotel.searchAvailableRooms("2025-08-21");

        // Book rooms
        hotel.bookRoom(c1, 101, "2025-08-21");
        hotel.bookRoom(c2, 102, "2025-08-21");

        // Show reservations
        hotel.showReservations();

        // Cancel one
        hotel.cancelReservation("Velu", 101, "2025-08-21");

        // Show again
        hotel.showReservations();
    }
}
