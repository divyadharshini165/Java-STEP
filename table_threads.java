class TableOf5 extends Thread {
    @Override
    public void run() {
        for (int i = 1; i <= 10; i++) {
            System.out.println("5 x " + i + " = " + (5 * i));
        }
    }
}

class TableOf10 extends Thread {
    @Override
    public void run() {
        for (int i = 1; i <= 10; i++) {
            System.out.println("10 x " + i + " = " + (10 * i));
        }
    }
}

public class table_threads {
    public static void main(String[] args) {
        TableOf5 t1 = new TableOf5();
        TableOf10 t2 = new TableOf10();

        try {
            t1.start();
            t1.join();  // wait for t1 to finish before starting t2
            t2.start();
            t2.join();  // wait for t2 to finish (optional, ensures completion before main ends)
        } catch (InterruptedException e) {
            System.out.println("Thread interrupted: " + e.getMessage());
        }
    }
}
