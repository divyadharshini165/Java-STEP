class NumberPrinter extends Thread {
    @Override
    public void run() {
        for (int i = 1; i <= 10; i++) {
            System.out.println("Number: " + i);
        }
    }
}

public class ThreadExample {
    public static void main(String[] args) {
        NumberPrinter t1 = new NumberPrinter();
        t1.start();
    }
}
