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

public class greetings_threads {
    public static void main(String[] args) {
        HelloThread t1 = new HelloThread();
        WelcomeThread t2 = new WelcomeThread();

        // run Hello first
        t1.run();   // not start(), runs in main thread

        // then run Welcome
        t2.run();   // also in main thread
    }
}
