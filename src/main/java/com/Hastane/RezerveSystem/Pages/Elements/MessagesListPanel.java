package com.Hastane.RezerveSystem.Pages.Elements;

import javax.swing.*;

import java.awt.*;
import java.sql.Date;

public class MessagesListPanel extends JPanel {

    public MessagesListPanel(String fullName, Date time, String message) {
        setLayout(null);

        ImageLabel imageLabel = new ImageLabel("C:\\Users\\abdur\\OneDrive\\Resimler\\Ekran Görüntüleri\\Screenshot 2024-12-02 154512.png");
        add(imageLabel);

        JLabel firstNameLabel = new JLabel(fullName);
        firstNameLabel.setFont(new Font("Arial", Font.BOLD, 16));
        firstNameLabel.setBounds(80, 10, 200, 20); // Adjusted x position to avoid overlap with image
        add(firstNameLabel);

        JLabel timeLabel = new JLabel(time.toString());
        timeLabel.setBounds(320, 10, 60, 20);
        add(timeLabel);

        JLabel messageLabel = new JLabel(message);
        messageLabel.setBounds(83, 40, 270, 20); // Adjusted y position to avoid overlap with other components
        add(messageLabel);

        setPreferredSize(new Dimension(380, 80)); // Adjusted height to fit all components
    }
}
