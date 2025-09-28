public class HotelBooking {

    // 1. Standard booking (room type + nights)
    public double calculateBookingCost(String roomType, int nights) {
        double rate = getRoomRate(roomType);
        double cost = rate * nights;

        System.out.println(" Standard Booking:");
        System.out.println("Room Type: " + roomType + ", Nights: " + nights);
        System.out.println("Rate per Night: ₹" + rate);
        System.out.println("Total Cost: ₹" + cost);
        System.out.println("--------------------------------");
        return cost;
    }

    // 2. Seasonal booking (room type + nights + seasonal multiplier)
    public double calculateBookingCost(String roomType, int nights, double seasonalMultiplier) {
        double rate = getRoomRate(roomType);
        double baseCost = rate * nights;
        double finalCost = baseCost * seasonalMultiplier;

        System.out.println(" Seasonal Booking:");
        System.out.println("Room Type: " + roomType + ", Nights: " + nights);
        System.out.println("Base Cost: ₹" + baseCost);
        System.out.println("Seasonal Multiplier: " + seasonalMultiplier);
        System.out.println("Final Cost: ₹" + finalCost);
        System.out.println("--------------------------------");
        return finalCost;
    }

    // 3. Corporate booking (room type + nights + corporate discount + meal package)
    public double calculateBookingCost(String roomType, int nights, double corporateDiscount, boolean mealPackage) {
        double rate = getRoomRate(roomType);
        double baseCost = rate * nights;
        double discount = baseCost * (corporateDiscount / 100);
        double meals = mealPackage ? 500 * nights : 0; // ₹500 per night for meals
        double finalCost = baseCost - discount + meals;

        System.out.println(" Corporate Booking:");
        System.out.println("Room Type: " + roomType + ", Nights: " + nights);
        System.out.println("Base Cost: ₹" + baseCost);
        System.out.println("Corporate Discount: " + corporateDiscount + "% (₹" + discount + ")");
        System.out.println("Meal Package: " + (mealPackage ? "₹" + meals : "Not Included"));
        System.out.println("Final Cost: ₹" + finalCost);
        System.out.println("--------------------------------");
        return finalCost;
    }

    // 4. Wedding package (room type + nights + guest count + decoration fee + catering option)
    public double calculateBookingCost(String roomType, int nights, int guestCount, double decorationFee, double cateringCostPerGuest) {
        double rate = getRoomRate(roomType);
        double roomCost = rate * nights;
        double catering = cateringCostPerGuest * guestCount;
        double finalCost = roomCost + decorationFee + catering;

        System.out.println(" Wedding Package Booking:");
        System.out.println("Room Type: " + roomType + ", Nights: " + nights);
        System.out.println("Room Cost: ₹" + roomCost);
        System.out.println("Decoration Fee: ₹" + decorationFee);
        System.out.println("Guests: " + guestCount + " | Catering Cost: ₹" + catering);
        System.out.println("Final Cost: ₹" + finalCost);
        System.out.println("--------------------------------");
        return finalCost;
    }

    // Helper: Room rates
    private double getRoomRate(String roomType) {
        switch (roomType.toLowerCase()) {
            case "standard": return 2000;
            case "deluxe": return 4000;
            case "suite": return 7000;
            default: return 2500;
        }
    }

    // Main method to test
    public static void main(String[] args) {
        HotelBooking booking = new HotelBooking();

        booking.calculateBookingCost("Standard", 3);
        booking.calculateBookingCost("Deluxe", 4, 1.2);
        booking.calculateBookingCost("Suite", 5, 15, true);
        booking.calculateBookingCost("Deluxe", 2, 100, 20000, 1200);
    }
}
