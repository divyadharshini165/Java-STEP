class HelloThread extends Thread {
    @Override
    public void run() {
        for (int i = 1; i <= 5; i++) {
            System.out.println("Hello");
        }
    }
}
class WelcomeThread extends Thread {
    @Override
    public void run() {
        for (int i = 1; i <= 5; i++) {
            System.out.println("Welcome");
        }
    }
}
public class HelloWelcome {
    public static void main(String[] args) {
        HelloThread t1 = new HelloThread();
        WelcomeThread t2 = new WelcomeThread();
        t1.start();  
        t2.start(); 
    }
}
