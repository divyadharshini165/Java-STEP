import java.util.Scanner;
package week2;
public class simplestringmethods{
    public static void main(String[] args) {
        String text = "welcome to java";
        System.out.println("length:" + text.length());
        System.out.println("first 'a' at index:" + text.indexOf(text));
        System.out.println("Substring(0,7):" + text.substring(0,7) + "'");
        System.out.println("trimmed" + text.trim() + "'");
        System.out.println("uppercase" + text.toUpperCase());
        String other = "welcome to java";
        System.out.println("equals ignore case:" + text.trim().equalsIgnoreCase(other));
    }
}