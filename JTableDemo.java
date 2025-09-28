import javax.swing.*;

public class JTableDemo {
    public static void main(String[] args) {
        JFrame frame = new JFrame("JTable Example");
        frame.setSize(400, 300);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        // Column names
        String[] columns = {"ID", "Name", "Course"};

        // Table data
        String[][] data = {
                {"1", "Alice", "Java"},
                {"2", "Bob", "Python"},
                {"3", "Charlie", "C++"}
        };

        // Create JTable
        JTable table = new JTable(data, columns);

        // Put table inside JScrollPane (so we can scroll)
        JScrollPane scrollPane = new JScrollPane(table);
        scrollPane.setBounds(30, 30, 300, 150);

        // Add to frame
        frame.setLayout(null);
        frame.add(scrollPane);//can scroll if the table is larger than the frame

        frame.setVisible(true);
    }
}