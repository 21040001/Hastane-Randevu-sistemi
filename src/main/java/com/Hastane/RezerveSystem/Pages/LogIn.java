package com.Hastane.RezerveSystem.Pages;

import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;

import com.Hastane.RezerveSystem.BusinessLayer.BusinessLayer;

public class LogIn extends JFrame {

    private BusinessLayer business;
    private JTextField usernameField;
    private JPasswordField passwordField;
    

    public LogIn(BusinessLayer business) {
        this.business = business;
        initialize();
    }

    public void initialize() {
        setLayout(null);

        JLabel usernameLabel = new JLabel("TC Number:");
        usernameLabel.setBounds(70, 100, 80, 25);
        add(usernameLabel);
        usernameField = new JTextField();
        usernameField.setBounds(170, 100, 160, 25);
        add(usernameField);

        JLabel passwordLabel = new JLabel("Password:");
        passwordLabel.setBounds(70, 140, 80, 25);
        add(passwordLabel);
        passwordField = new JPasswordField();
        passwordField.setBounds(170, 140, 160, 25);
        add(passwordField);

        JButton loginButton = new JButton("Login");
        loginButton.setBounds(170, 180, 80, 25);
        add(loginButton);
        loginButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String TcNumber = usernameField.getText();
                String password = new String(passwordField.getPassword());
                int idUser = business.getIdUserByTcNumber(Integer.valueOf(TcNumber));
                boolean doctor = business.itIsDoctor(Integer.valueOf(TcNumber));
                if (password.equals(business.getPasswordByTcNumber(Integer.valueOf(TcNumber)))) {
                    JOptionPane.showMessageDialog(null, "Login Successful!");
                    dispose();
                    new ChoosePage(business, idUser, doctor).setVisible(true);
                } else {
                    JOptionPane.showMessageDialog(null, "Invalid TcNumber or Password");
                }
            }
        });

        setTitle("Log In");
        setSize(400, 300);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
    }
}
