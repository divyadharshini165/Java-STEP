import javax.swing.*;

public class PasswordFieldDemo {
    public static void main(String[] args) {

        JFrame frame = new JFrame("JPasswordField Example");
        frame.setSize(400, 300);
        frame.setLayout(null);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        // 1. Empty password field
        JLabel lbl1 = new JLabel("Empty Constructor:");
        lbl1.setBounds(30, 30, 150, 25);
        frame.add(lbl1);

        JPasswordField pf1 = new JPasswordField();
        pf1.setBounds(180, 30, 150, 25);
        frame.add(pf1);

        // 2. With columns
        JLabel lbl2 = new JLabel("With columns (10):");
        lbl2.setBounds(30, 70, 150, 25);
        frame.add(lbl2);

        JPasswordField pf2 = new JPasswordField(10);
        pf2.setBounds(180, 70, 150, 25);
        frame.add(pf2);

        // 3. With default text
        JLabel lbl3 = new JLabel("With text:");
        lbl3.setBounds(30, 110, 150, 25);
        frame.add(lbl3);

        JPasswordField pf3 = new JPasswordField("star123");
        pf3.setBounds(180, 110, 150, 25);
        frame.add(pf3);

        // 4. With text + columns
        JLabel lbl4 = new JLabel("Text + columns:");
        lbl4.setBounds(30, 150, 150, 25);
        frame.add(lbl4);

        JPasswordField pf4 = new JPasswordField("mypwd", 10);
        pf4.setBounds(180, 150, 150, 25);
        frame.add(pf4);

        // Show frame
        frame.setVisible(true);
    }
}