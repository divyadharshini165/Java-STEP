import javax.swing.*;

public class JLabelDemo {
    public static void main(String[] args) {

        // Create frame
        JFrame frame = new JFrame("JLabel Constructors Demo");
        frame.setSize(800, 600);
        frame.setLayout(null);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        // 1. JLabel()
        JLabel label1 = new JLabel();
        label1.setText("Learning Labels");
        label1.setBounds(30, 30, 300, 25);
        frame.add(label1);

        // 2. JLabel(String s)
        JLabel label2 = new JLabel("Welcome");
        label2.setBounds(30, 70, 300, 25);
        frame.add(label2);

        // 3. JLabel(Icon i)
        ImageIcon icon = new ImageIcon("pyt.jpg"); 
        JLabel label3 = new JLabel(icon);
        label3.setBounds(100, 100, 100, 100);
        frame.add(label3);

        // 4. JLabel(String s, Icon i, int horizontalAlignment)
        JLabel label4 = new JLabel("Text with Icon", icon, SwingConstants.CENTER);
       label4.setBounds(150, 110, 250, 100);
       frame.add(label4);

        // Show the frame
        frame.setVisible(true);
    }
}
