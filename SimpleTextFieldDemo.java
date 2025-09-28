import javax.swing.*;
import java.awt.event.*;

public class SimpleTextFieldDemo {
    public static void main(String[] args) {
        JFrame frame = new JFrame("JTextField Demo");
        frame.setSize(400, 250);
        frame.setLayout(null);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        // 1. JTextField(int cols) → empty box
        JTextField field1 = new JTextField(15);
        field1.setBounds(30, 30, 150, 25);
        frame.add(field1);

        // 2. JTextField(String str, int cols) → text + width
        JTextField field2 = new JTextField("Default Text", 1000);
        field2.setBounds(30, 70, 200, 25);
        frame.add(field2);

        // 3. JTextField(String str) → only text
        JTextField field3 = new JTextField("Hello Students!");
        field3.setBounds(30, 110, 200, 25);
        frame.add(field3);

        // Button to show values
        JButton btn = new JButton("Show");
        btn.setBounds(30, 150, 100, 30);
        frame.add(btn);

        // Button action
        btn.addActionListener(e -> {
            String msg = "Field1: " + field1.getText() +
                         "\nField2: " + field2.getText() +
                         "\nField3: " + field3.getText();
            JOptionPane.showMessageDialog(frame, msg);
        });

        frame.setVisible(true);
    }
}