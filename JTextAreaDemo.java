import javax.swing.*;

public class JTextAreaDemo {
    public static void main(String[] args) {
        JFrame frame = new JFrame("JTextArea Demo");
        frame.setSize(500, 400);
        frame.setLayout(null);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        // 1. JTextArea() → empty text area
        JTextArea area1 = new JTextArea();
        area1.setBounds(20, 20, 200, 60);
        area1.setBorder(BorderFactory.createTitledBorder("JTextArea()"));
        frame.add(area1);

        // 2. JTextArea(String s) → initialized with text
        JTextArea area2 = new JTextArea("Hello Students!");
        area2.setBounds(250, 20, 200, 60);
        area2.setBorder(BorderFactory.createTitledBorder("JTextArea(String s)"));
        frame.add(area2);

        // 3. JTextArea(int rows, int cols) → empty with size
        JTextArea area3 = new JTextArea(3, 15);
        area3.setBounds(20, 120, 200, 60);
        area3.setBorder(BorderFactory.createTitledBorder("JTextArea(int rows, int cols)"));
        frame.add(area3);

        // 4. JTextArea(String s, int rows, int cols) → text + size
        JTextArea area4 = new JTextArea("Write here...", 3, 20);
        area4.setBounds(250, 120, 200, 60);
        area4.setBorder(BorderFactory.createTitledBorder("JTextArea(String, rows, cols)"));
        frame.add(area4);

        // Button to read values
        JButton btn = new JButton("Show Text");
        btn.setBounds(170, 220, 150, 30);
        frame.add(btn);

        btn.addActionListener(e -> {
            String msg = "Area1: " + area1.getText()
                       + "\nArea2: " + area2.getText()
                       + "\nArea3: " + area3.getText()
                       + "\nArea4: " + area4.getText();
            JOptionPane.showMessageDialog(frame, msg);
        });

        frame.setVisible(true);
    }
}