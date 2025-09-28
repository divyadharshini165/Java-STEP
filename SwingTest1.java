import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class SwingTest1 {
    public static void main(String[] args) {
        JFrame frame = new JFrame("Swing Component Demo (Full)");
        frame.setSize(620, 500);
        frame.setLayout(null); // Manual layout
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        // 1) LABEL + TEXTFIELD (Name)
        JLabel nameLabel = new JLabel("Name:");
        nameLabel.setBounds(20, 20, 80, 25);
        frame.add(nameLabel);

        JTextField nameField = new JTextField();
        nameField.setBounds(110, 20, 180, 25);
        frame.add(nameField);

        // 2) LABEL + PASSWORD FIELD
        JLabel passLabel = new JLabel("Password:");
        passLabel.setBounds(20, 60, 80, 25);
        frame.add(passLabel);

        JPasswordField passField = new JPasswordField();
        passField.setBounds(110, 60, 180, 25);
        frame.add(passField);

        // 3) CHECKBOX (Agree)
        JCheckBox agreeBox = new JCheckBox("I agree to terms");
        agreeBox.setBounds(20, 100, 200, 25);
        frame.add(agreeBox);

        // 4) RADIO BUTTONS (Gender)
        JLabel genderLabel = new JLabel("Gender:");
        genderLabel.setBounds(20, 140, 80, 25);
        frame.add(genderLabel);

        JRadioButton maleBtn = new JRadioButton("Male");
        maleBtn.setBounds(110, 140, 70, 25);
        JRadioButton femaleBtn = new JRadioButton("Female");
        femaleBtn.setBounds(190, 140, 80, 25);
        ButtonGroup genderGroup = new ButtonGroup();
        genderGroup.add(maleBtn);
        genderGroup.add(femaleBtn);
        frame.add(maleBtn);
        frame.add(femaleBtn);

        // 5) COMBO BOX (Course)
        JLabel courseLabel = new JLabel("Course:");
        courseLabel.setBounds(20, 180, 80, 25);
        frame.add(courseLabel);

        String[] courses = {"Java", "Python", "C++", "JavaScript"};
        JComboBox<String> courseBox = new JComboBox<>(courses);
        courseBox.setBounds(110, 180, 180, 25);
        frame.add(courseBox);

        // 6) TEXT AREA (Address) inside a SCROLL PANE
        JLabel addrLabel = new JLabel("Address:");
        addrLabel.setBounds(20, 220, 80, 25);
        frame.add(addrLabel);

        JTextArea addressArea = new JTextArea(4, 20);
        addressArea.setLineWrap(true);
        addressArea.setWrapStyleWord(true);
        JScrollPane addressPane = new JScrollPane(addressArea);
        addressPane.setBounds(110, 220, 180, 70);
        frame.add(addressPane);

        // 7) LIST (Hobbies) inside a SCROLL PANE
        JLabel hobbyLabel = new JLabel("Hobbies:");
        hobbyLabel.setBounds(20, 305, 80, 25);
        frame.add(hobbyLabel);

        String[] hobbies = {"Reading", "Sports", "Music", "Coding", "Travel", "Art"};
        JList<String> hobbyList = new JList<>(hobbies);
        hobbyList.setSelectionMode(ListSelectionModel.MULTIPLE_INTERVAL_SELECTION);
        JScrollPane listPane = new JScrollPane(hobbyList);
        listPane.setBounds(110, 305, 180, 80);
        frame.add(listPane);

        // 8) TABLE (Sample Data) inside a SCROLL PANE (right side)
        String[] cols = {"ID", "Name"};
        Object[][] data = {
                {1, "Alice"},
                {2, "Bob"},
                {3, "Charlie"},
                {4, "Diana"},
        };
        JTable table = new JTable(data, cols);
        JScrollPane tablePane = new JScrollPane(table);
        tablePane.setBounds(320, 20, 260, 120);
        frame.add(tablePane);

        // 9) SEPARATE SCROLLBAR (example)
        JScrollBar vScroll = new JScrollBar(JScrollBar.VERTICAL, 0, 10, 0, 100);
        vScroll.setBounds(580, 20, 15, 380);
        frame.add(vScroll);

        // Optional: show scroll value in title as you move it
        vScroll.addAdjustmentListener(e ->
                frame.setTitle("Swing Component Demo (Scroll: " + e.getValue() + ")")
        );

        // 10) SUBMIT BUTTON + OPTION PANE
        JButton submitBtn = new JButton("Submit");
        submitBtn.setBounds(250, 410, 100, 30);
        frame.add(submitBtn);

        submitBtn.addActionListener(e -> {
            String name = nameField.getText().trim();
            String pass = new String(passField.getPassword());
            String gender = maleBtn.isSelected() ? "Male" : (femaleBtn.isSelected() ? "Female" : "Not selected");
            String course = (String) courseBox.getSelectedItem();
            java.util.List<String> chosenHobbies = hobbyList.getSelectedValuesList();
            String address = addressArea.getText().trim();

            String msg = "Name: " + name +
                    "\nPassword: " + pass +
                    "\nAgree: " + (agreeBox.isSelected() ? "Yes" : "No") +
                    "\nGender: " + gender +
                    "\nCourse: " + course +
                    "\nHobbies: " + (chosenHobbies.isEmpty() ? "None" : chosenHobbies) +
                    "\nAddress: " + (address.isEmpty() ? "(empty)" : address);

            JOptionPane.showMessageDialog(frame, msg, "Form Data", JOptionPane.INFORMATION_MESSAGE);
        });

        frame.setVisible(true);
    }
}