import javax.swing.*;

public class SwingTest {
    public static void main(String[] args) {

        JFrame frame = new JFrame("Swing Component Demo");
        frame.setSize(400, 400);
        frame.setLayout(null); // Manual layout
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        // 1. JLabel
        JLabel nameLabel = new JLabel("Name:");
        nameLabel.setBounds(30, 30, 80, 25);
        frame.add(nameLabel);

        // 2. JTextField
        JTextField nameField = new JTextField();
        nameField.setBounds(120, 30, 150, 25);
        frame.add(nameField);

        // 3. JCheckBox
        JCheckBox agreeBox = new JCheckBox("I agree to terms");
        agreeBox.setBounds(30, 70, 200, 25);
        frame.add(agreeBox);

        // 4. JRadioButton
        JRadioButton maleBtn = new JRadioButton("Male");
        maleBtn.setBounds(30, 110, 80, 25);
        JRadioButton femaleBtn = new JRadioButton("Female");
        femaleBtn.setBounds(120, 110, 80, 25);

        ButtonGroup genderGroup = new ButtonGroup(); // Group radio buttons
        genderGroup.add(maleBtn);
        genderGroup.add(femaleBtn);

        frame.add(maleBtn);
        frame.add(femaleBtn);

        // 5. JComboBox
        String[] courses = {"Java", "Python", "C++", "JavaScript"};
        JComboBox<String> courseBox = new JComboBox<>(courses);
        courseBox.setBounds(30, 150, 150, 25);
        frame.add(courseBox);

        // 6. JButton
        JButton submitBtn = new JButton("Submit");
        submitBtn.setBounds(30, 200, 100, 30);
        frame.add(submitBtn);

        // Final step: Show the frame
        frame.setVisible(true);
    }
}