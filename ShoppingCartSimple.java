import javax.swing.*;
import java.awt.event.*;
public class ShoppingCartSimple extends JFrame implements ActionListener {
    JCheckBox laptop, phone, headphones;
    JButton billButton;
    public ShoppingCartSimple() {
        setTitle("Shopping Cart");
        setSize(250, 200);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLayout(null); // absolute positioning
        laptop = new JCheckBox("Laptop-$800");
        laptop.setBounds(20, 20, 150, 20);
        phone = new JCheckBox("Phone-$500");
        phone.setBounds(20, 50, 150, 20);
        headphones = new JCheckBox("Headphones-$150");
        headphones.setBounds(20, 80, 150, 20);
        billButton = new JButton("Generate Bill");
        billButton.setBounds(50, 120, 130, 30);
        billButton.addActionListener(this);
        add(laptop);
        add(phone);
        add(headphones);
        add(billButton);
        setVisible(true);
    }
    @Override
    public void actionPerformed(ActionEvent e) {
        int total = 0;
        String bill = "Bill:\n";
        if (laptop.isSelected()) { bill += "Laptop-$800\n"; total += 800; }
        if (phone.isSelected()) { bill += "Phone-$500\n"; total += 500; }
        if (headphones.isSelected()) { bill += "Headphones-$150\n"; total += 150; }
        bill += "Total:$" + total;
        JOptionPane.showMessageDialog(this, bill);
    }
    public static void main(String[] args) {
        new ShoppingCartSimple();
    }
}
