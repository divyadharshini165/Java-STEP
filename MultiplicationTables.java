public class MultiplicationTables {
    public static void main(String[] args) {
        Thread t1 = new Thread() {
            @Override
            public void run() {
                for (int i = 1; i <= 10; i++) {
                    System.out.println("5 x " + i + " = " + (5 * i));
                }
            }
        };
        Thread t2 = new Thread() {
            @Override
            public void run() {
                for (int i = 1; i <= 10; i++) {
                    System.out.println("10 x " + i + " = " + (10 * i));
                }
            }
        };
        t1.start();
        t2.start();
    }
}
