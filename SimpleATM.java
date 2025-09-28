import javax.swing.*;
import java.awt.event.*;

public class SimpleATM extends JFrame implements ActionListener {
    JPasswordField pinField;
    JButton loginBtn, balBtn, depBtn, witBtn;
    int balance = 1000;

    public SimpleATM() {
        setTitle("ATM");
        setSize(250, 200);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLayout(null);

        JLabel l = new JLabel("PIN:");
        l.setBounds(30, 30, 40, 20);
        add(l);

        pinField = new JPasswordField();
        pinField.setBounds(70, 30, 100, 20);
        add(pinField);

        loginBtn = new JButton("Login");
        loginBtn.setBounds(70, 60, 80, 25);
        add(loginBtn);
        loginBtn.addActionListener(this);

        setVisible(true);
    }

    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == loginBtn) {
            String pin = new String(pinField.getPassword());
            if (pin.equals("1234")) {
                showMenu();
            } else {
                JOptionPane.showMessageDialog(this, "Wrong PIN!");
            }
        } else if (e.getSource() == balBtn) {
            JOptionPane.showMessageDialog(this, "Balance: $" + balance);
        } else if (e.getSource() == depBtn) {
            String amt = JOptionPane.showInputDialog("Deposit amount:");
            if (amt != null) balance += Integer.parseInt(amt);
        } else if (e.getSource() == witBtn) {
            String amt = JOptionPane.showInputDialog("Withdraw amount:");
            if (amt != null) {
                int a = Integer.parseInt(amt);
                if (a <= balance) balance -= a;
                else JOptionPane.showMessageDialog(this, "Insufficient funds!");
            }
        }
    }

    void showMenu() {
        getContentPane().removeAll();
        setLayout(null);

        balBtn = new JButton("Balance");
        balBtn.setBounds(60, 30, 120, 25);
        add(balBtn);
        balBtn.addActionListener(this);

        depBtn = new JButton("Deposit");
        depBtn.setBounds(60, 70, 120, 25);
        add(depBtn);
        depBtn.addActionListener(this);

        witBtn = new JButton("Withdraw");
        witBtn.setBounds(60, 110, 120, 25);
        add(witBtn);
        witBtn.addActionListener(this);

        revalidate();
        repaint();
    }

    public static void main(String[] args) {
        new SimpleATM();
    }
}
