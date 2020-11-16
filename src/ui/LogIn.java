package ui;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import controller.VirusDatabase;

public class LogIn extends JFrame {
    private static VirusDatabase virusDatabase = null;

    private JTextField userIDTextField;
    private JPasswordField passwordField;
    private JButton logInButton;
    private JLabel userID;
    private JLabel password;
    private JPanel logIn;

    public LogIn() {

        logInButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                boolean result = virusDatabase.login(userIDTextField.getText(), String.valueOf(passwordField.getPassword()));

                if (result) {
                    result = virusDatabase.createDatabase();
                    if (result) {
                        dispose();
                        DatabaseDialog databaseDialog = new DatabaseDialog(virusDatabase);
                        databaseDialog.add(databaseDialog.databaseDialog);

                        databaseDialog.setVisible(true);
                        databaseDialog.setTitle("Virus Database");
                        databaseDialog.setDefaultCloseOperation(databaseDialog.EXIT_ON_CLOSE);
                        databaseDialog.setSize(900, 900);
                        databaseDialog.setVisible(true);
                    } else {
                        JOptionPane.showMessageDialog(null,"Database setup is failed.");
                    }
                } else {
                    JOptionPane.showMessageDialog(null, e + "\n\nConnection failed.");
                }
            }
        });
    }

    public static void main(String[] args) {

        virusDatabase = new VirusDatabase();
        LogIn logIn = new LogIn();
        logIn.add(logIn.logIn);

        logIn.setTitle("Log In");
        logIn.setDefaultCloseOperation(logIn.EXIT_ON_CLOSE);
        logIn.setSize(300, 300);
        logIn.setVisible(true);

    }
}
