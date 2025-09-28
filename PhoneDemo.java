package week3;

public class PhoneDemo {
    public static void main(String[] args) {
        System.out.println("=== Creating Different Phones ===\n");

        // Creating objects from the Phone class
        Phone iPhone = new Phone("Apple", "iPhone 15", "Black");
        Phone samsung = new Phone("Samsung", "Galaxy S24", "Blue");
        Phone pixel = new Phone("Google", "Pixel 8", "White");

        System.out.println("\n--- Phone Information ---");
        iPhone.showPhoneInfo();
        samsung.showPhoneInfo();
        pixel.showPhoneInfo();

        System.out.println("\n--- Using iPhone ---");
        iPhone.makeCall("123-456-7890");
        iPhone.sendMessage("Hello World!");
        iPhone.playMusic();
        iPhone.playMusic(); // Uses more battery
        System.out.println("\n====using samsung===");
        samsung.sendMessage("hi from samsung";)
        samsung.makeCall("987-654-3210");
        System.out.println("\n===charging iphone===");
        iphone.chargeBattery();
        iphone.showPhoneInfo();
        System.out.println("\n===final status===");
        iphone.showPhoneInfo();
        samsung.showPhoneInfo();
        pixel.showPhoneInfo();
        class Phone {
    // ======= PROPERTIES (Attributes) =======
    String brand;
    String model;
    String color;
    int batteryLevel;

    // ======= CONSTRUCTOR =======
    public Phone(String phoneBrand, String phoneModel, String phoneColor) {
        brand = phoneBrand;
        model = phoneModel;
        color = phoneColor;
        batteryLevel = 100; // New phone comes fully charged
        System.out.println("New " + brand + " " + model + " created!");
    }

    // ======= METHODS =======
    public void makeCall(String number) {
        if (batteryLevel > 5) {
            System.out.println("Calling " + number + " from " + brand + " " + model);
            batteryLevel -= 5; // reduces battery after call
        } else {
            System.out.println("Battery too low to make a call.");
        }
    }
}
public void sendMessage(String message) {
        if (batteryLevel > 2) {
            batteryLevel -= 2;
            System.out.println("Message sent: " + message);
            System.out.println("Battery: " + batteryLevel + "%");
        } else {
            System.out.println("Battery too low to send message!");
        }
    }

    public void playMusic() {
        if (batteryLevel > 10) {
            batteryLevel -= 10;
            System.out.println("Playing music...");
            System.out.println("Battery: " + batteryLevel + "%");
        } else {
            System.out.println("Battery too low to play music!");
        }
    }

    public void chargeBattery() {
        batteryLevel = 100;
        System.out.println("Phone fully charged! Battery: 100%");
    }

    public void showPhoneInfo() {
        System.out.println("=== Phone Information ===");
        System.out.println("Brand: " + brand);
        System.out.println("Model: " + model);
        System.out.println("Color: " + color);
        System.out.println("Battery: " + batteryLevel + "%");
    }
    }