import.util.Scanner;
package week2;
public class simpleasciidemo{
    public static void main(String[] args) {
        char ch = 'A';
        System.out.println(""+ ch + "' -> ASCII: " + (int)ch);
        int code = 97;
        System.out.println("ASCII" + code + " -> '" + (char)code + "'");
        System.out.println("difference between 'A' and 'a' : " + ('a' - 'A'));
        char test = '5';
        System.out.println("'" + test + "' is digit:" + (test>='0' && test<='9'));
    }
}