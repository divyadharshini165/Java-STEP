public class FoodDelivery {

    // 1. Basic Delivery: Only distance-based
    public double calculateDeliveryCharge(double distance) {
        double cost = distance * 5; // ₹5 per km
        System.out.println("Basic Delivery: Distance = " + distance + " km, Cost = ₹" + cost);
        return cost;
    }

    // 2. Premium Delivery: Distance + Priority Fee
    public double calculateDeliveryCharge(double distance, double priorityFee) {
        double cost = (distance * 5) + priorityFee;
        System.out.println("Premium Delivery: Distance = " + distance + " km, Priority Fee = ₹" 
                            + priorityFee + ", Total Cost = ₹" + cost);
        return cost;
    }

    // 3. Group Delivery: Distance + Number of Orders Discount
    public double calculateDeliveryCharge(double distance, int numberOfOrders) {
        double baseCost = distance * 5;
        double discount = numberOfOrders * 2; // ₹2 discount per order
        double totalCost = Math.max(0, baseCost - discount); // Avoid negative cost
        System.out.println("Group Delivery: Distance = " + distance + " km, Orders = " 
                            + numberOfOrders + ", Discount = ₹" + discount 
                            + ", Total Cost = ₹" + totalCost);
        return totalCost;
    }

    // 4. Festival Special: Distance + Discount % + Free delivery if bill exceeds limit
    public double calculateDeliveryCharge(double distance, double discountPercent, double freeLimit) {
        double baseCost = distance * 5;
        if (baseCost > freeLimit) {
            System.out.println("Festival Special: Free delivery applied! Base = ₹" + baseCost);
            return 0;
        } else {
            double discount = baseCost * (discountPercent / 100);
            double totalCost = baseCost - discount;
            System.out.println("Festival Special: Distance = " + distance + " km, Base = ₹" 
                                + baseCost + ", Discount = " + discountPercent 
                                + "% (₹" + discount + "), Total Cost = ₹" + totalCost);
            return totalCost;
        }
    }

    // Testing the overloaded methods
    public static void main(String[] args) {
        FoodDelivery app = new FoodDelivery();

        app.calculateDeliveryCharge(10); // Basic
        app.calculateDeliveryCharge(8, 50); // Premium
        app.calculateDeliveryCharge(12, 3); // Group
        app.calculateDeliveryCharge(15, 20, 100); // Festival
    }
}
