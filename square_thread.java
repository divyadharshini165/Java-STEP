class SquarePrinter implements Runnable {
    @Override
    public void run() {
        for (int i = 1; i <= 5; i++) {
            System.out.println("Square of " + i + " = " + (i * i));
        }
    }
}

public class square_thread {
    public static void main(String[] args) {
        SquarePrinter task = new SquarePrinter();
        Thread t = new Thread(task);  // pass Runnable to Thread
        t.start();  // start the thread
    }
}
