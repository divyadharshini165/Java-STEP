import javax.swing.*;
import java.awt.*;

public class SwingBetterLayout {
    public static void main(String[] args) {
        // Create main frame
        JFrame frame = new JFrame("Clean Form Layout");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(400, 350);

        // Main panel with vertical layout (Y_AXIS)
        JPanel mainPanel = new JPanel();
        mainPanel.setLayout(new BoxLayout(mainPanel, BoxLayout.Y_AXIS));
        mainPanel.setBorder(BorderFactory.createEmptyBorder(10, 20, 10, 20)); // margin

        // Row 1: Name
        JPanel namePanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        namePanel.add(new JLabel("Name:"));
        JTextField nameField = new JTextField(20);
        namePanel.add(nameField);
        mainPanel.add(namePanel);

        // Row 2: Checkbox
        JPanel checkPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        JCheckBox agreeBox = new JCheckBox("I agree to the terms and conditions");
        checkPanel.add(agreeBox);
        mainPanel.add(checkPanel);

        // Row 3: Radio Buttons
        JPanel radioPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        radioPanel.add(new JLabel("Gender:"));
        JRadioButton male = new JRadioButton("Male");
        JRadioButton female = new JRadioButton("Female");
        ButtonGroup group = new ButtonGroup();
        group.add(male);
        group.add(female);
        radioPanel.add(male);
        radioPanel.add(female);
        mainPanel.add(radioPanel);

        // Row 4: ComboBox
        JPanel comboPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        comboPanel.add(new JLabel("Course:"));
        String[] courses = {"Java", "Python", "C++", "JavaScript"};
        JComboBox<String> courseBox = new JComboBox<>(courses);
        comboPanel.add(courseBox);
        mainPanel.add(comboPanel);

        // Row 5: Submit Button
        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
        JButton submit = new JButton("Submit");
        buttonPanel.add(submit);
        mainPanel.add(buttonPanel);

        // Add the main panel to frame
        frame.getContentPane().add(mainPanel);
        frame.setVisible(true);
    }
}