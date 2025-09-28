class OddPrinter extends Thread {
    @Override
    public void run() {
        for (int i = 1; i <= 10; i += 2) {
            System.out.println("Odd: " + i);
        }
    }
}

class EvenPrinter extends Thread {
    @Override
    public void run() {
        for (int i = 2; i <= 10; i += 2) {
            System.out.println("Even: " + i);
        }
    }
}

public class odd_even_threads {
    public static void main(String[] args) {
        OddPrinter oddThread = new OddPrinter();
        EvenPrinter evenThread = new EvenPrinter();

        oddThread.start();
        evenThread.start();
    }
}
