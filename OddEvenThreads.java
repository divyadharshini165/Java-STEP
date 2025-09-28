class OddEvenThread extends Thread {
    private int start, end;

    public OddEvenThread(int start, int end) {
        this.start = start;
        this.end = end;
    }

    @Override
    public void run() {
        for (int i = start; i <= end; i++) {
            if (i % 2 == 0) {
                System.out.println("Even: " + i);
            } else {
                System.out.println("Odd: " + i);
            }
        }
    }
}

public class OddEvenThreads {
    public static void main(String[] args) {
        OddEvenThread thread1 = new OddEvenThread(1, 5);  // One thread
        OddEvenThread thread2 = new OddEvenThread(6, 10); // Another thread

        thread1.start();
        thread2.start();
    }
}
