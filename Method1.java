class TableThread extends Thread {
    private int number;

    public TableThread(int number) {
        this.number = number;
    }
    public void run() {
        for (int i = 1; i <= 10; i++) {
            System.out.println(number + " x " + i + " = " + (number * i));
        }
    }
}
public class Method1 {
    public static void main(String[] args) {
        new TableThread(5).start();
        new TableThread(10).start();
    }
}
