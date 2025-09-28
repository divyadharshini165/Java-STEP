import javax.swing.*;
import java.awt.event.*;

public class PopupMenuExample {
    public static void main(String[] args) {
        JFrame frame = new JFrame("PopupMenu with Separator Example");
        frame.setSize(400, 300);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        // Create popup menu
        JPopupMenu popupMenu = new JPopupMenu();

        // Add menu items
        JMenuItem item1 = new JMenuItem("Never");
        popupMenu.add(item1);

        JMenuItem item2 = new JMenuItem("Stop");
        popupMenu.add(item2);

        // Add separator
        popupMenu.addSeparator();

        JMenuItem item3 = new JMenuItem("Learning");
        popupMenu.add(item3);

        // Add popup menu to main panel
        JPanel panel = new JPanel();
        panel.setComponentPopupMenu(popupMenu);

        // Optionally, show popup menu on mouse right-click
        panel.addMouseListener(new MouseAdapter() {
            public void mousePressed(MouseEvent e) {
                if (e.isPopupTrigger()) {
                    popupMenu.show(panel, e.getX(), e.getY());
                }
            }
            public void mouseReleased(MouseEvent e) {
                if (e.isPopupTrigger()) {
                    popupMenu.show(panel, e.getX(), e.getY());
                }
            }
        });

        frame.add(panel);
        frame.setVisible(true);
    }
}