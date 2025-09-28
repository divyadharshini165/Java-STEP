import java.util.Scanner;

public class WeatherCheck {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        System.out.print("Enter month: ");
        String month = sc.nextLine();

        // Check if month is December
        if (month.equalsIgnoreCase("December")) {
            System.out.println("It's cold! Wear warm clothes.");
        } else {
            System.out.println("Not December, normal weather.");
        }

        sc.close();
    }
}
