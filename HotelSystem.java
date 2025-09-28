// Assignment 3: Hotel Reservation System (Simplified)

class Room {
    String roomNumber, roomType;
    double pricePerNight;
    boolean isAvailable = true;
    int maxOccupancy;

    Room(String num, String type, double price, int maxOcc) {
        roomNumber = num; roomType = type; pricePerNight = price; maxOccupancy = maxOcc;
    }
}

class Guest {
    String guestId, guestName, phoneNumber, email;
    String[] bookingHistory = new String[5];
    int historyCount = 0;

    Guest(String id, String name, String phone, String mail) {
        guestId = id; guestName = name; phoneNumber = phone; email = mail;
    }

    void addBookingHistory(String bookingId) {
        if (historyCount < bookingHistory.length)
            bookingHistory[historyCount++] = bookingId;
    }
}

class Booking {
    String bookingId, checkInDate, checkOutDate;
    Guest guest; Room room; double totalAmount;

    static int totalBookings = 0;
    static double hotelRevenue = 0;
    static String hotelName = "Sunrise Hotel";

    Booking(String id, Guest g, Room r, String in, String out, int nights) {
        bookingId = id; guest = g; room = r;
        checkInDate = in; checkOutDate = out;
        totalAmount = nights * r.pricePerNight;
        r.isAvailable = false; g.addBookingHistory(id);
        totalBookings++; hotelRevenue += totalAmount;
    }

    static double getTotalRevenue() { return hotelRevenue; }
}

public class HotelSystem {
    public static void main(String[] args) {
        // Create Rooms
        Room r1 = new Room("101", "Single", 1000, 1);
        Room r2 = new Room("102", "Double", 1800, 2);

        // Create Guest
        Guest g1 = new Guest("G001", "Alice", "99999", "alice@mail.com");

        // Make Reservation
        if (r1.isAvailable) {
            Booking b1 = new Booking("B001", g1, r1, "2025-09-01", "2025-09-03", 2);
            System.out.println("Booking Done: " + b1.bookingId + " Amount: " + b1.totalAmount);
        }

        // Show Reports
        System.out.println("Total Bookings: " + Booking.totalBookings);
        System.out.println("Hotel Revenue: " + Booking.getTotalRevenue());
    }
}
