import javax.swing.*;

public class JOptionPaneExample {
    public static void main(String[] args) {
        // 1. Empty JOptionPane (not displayed directly)
        JOptionPane optionPane = new JOptionPane();

        // 2. Simple message dialog
        JOptionPane.showMessageDialog(
            null, 
            "Welcome to JOptionPane Example!"
        );

        // 3. Message with type (Error)
        JOptionPane.showMessageDialog(
            null, 
            "An error has occurred!", 
            "Error", 
            JOptionPane.ERROR_MESSAGE
        );

        // 4. Message with type (Information)
        JOptionPane.showMessageDialog(
            null, 
            "Process completed successfully.", 
            "Info", 
            JOptionPane.INFORMATION_MESSAGE
        );
    }
}