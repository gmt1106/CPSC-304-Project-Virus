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

                    } else {
                        JOptionPane.showMessageDialog(null,"Database setup is failed.");
                    }
                } else {
                    JOptionPane.showMessageDialog(null, "Connection failed." + "\n\nPlease check your login ID and Password." +
                            "\nThe format should be     ID: ora_<CWL>     Password:a<studentNumber>" + "\n\nAlso ensure that you have created an ssh tunnel" +
                            "\nssh -l <CWL> -L localhost:1522:dbhost.students.cs.ubc.ca:1522 remote.students.cs.ubc.ca");
                }
            }
        });
    }

    public static void main(String[] args) {

        virusDatabase = new VirusDatabase();
        LogIn logIn = new LogIn();
        logIn.add(logIn.logIn);

        //set this LogIn Dialog
        logIn.setTitle("Log In");
        logIn.setDefaultCloseOperation(logIn.EXIT_ON_CLOSE);
        logIn.setSize(300, 300);
        logIn.setVisible(true);

    }
}
