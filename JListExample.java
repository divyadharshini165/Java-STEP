import javax.swing.*;

public class JListExample {
    public static void main(String[] args) {
        // Step 1: Create the DefaultListModel
        DefaultListModel<String> model = new DefaultListModel<>();
        model.addElement("Java");
        model.addElement("Python");
        model.addElement("C++");
	model.addElement("Java");
        model.addElement("Python");
        model.addElement("C++");
	model.addElement("Java");
        model.addElement("Python");
        model.addElement("C++");
	model.addElement("Java");
        model.addElement("Python");
        model.addElement("C++");


        // Step 2: Create the JList using the model
        JList<String> list = new JList<>(model);

        // Step 3: Put the JList in a JScrollPane (for scrolling)
        JScrollPane scrollPane = new JScrollPane(list);

        // Step 4: Create a JFrame to display the list
        JFrame frame = new JFrame("JList with DefaultListModel Example");
        frame.setSize(200, 200);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        // Step 5: Add the scrollPane to the frame
        frame.add(scrollPane);

        // Step 6: Make the frame visible
        frame.setVisible(true);
    }
}