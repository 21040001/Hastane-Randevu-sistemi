package com.Hastane.RezerveSystem.Pages.Elements;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import com.Hastane.RezerveSystem.BusinessLayer.BusinessLayer;

public class ListPanel extends JPanel {

    private BusinessLayer business;

    public ListPanel(int randevuId, String time, String surname, String name, String randevuType, BusinessLayer business) {
        this.business = business;
        setLayout(null);

        JLabel timeLabel = new JLabel(time);
        timeLabel.setBounds(14, 18, 37, 20);
        add(timeLabel);

        JSeparator line = new JSeparator(SwingConstants.VERTICAL);
        line.setBounds(67, 14, 2, 41);
        add(line);

        JLabel nameLabel = new JLabel(name + " " + surname);
        nameLabel.setFont(new Font("System", Font.BOLD, 13));
        nameLabel.setBounds(79, 8, 171, 20);
        add(nameLabel);

        JLabel statusLabel = new JLabel(randevuType);
        statusLabel.setBounds(79, 31, 200, 20);
        add(statusLabel);

        JButton rejectButton = new JButton("Red et");
        rejectButton.setBounds(320, 13, 70, 25);
        rejectButton.setFocusable(false); 
        
        rejectButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.out.println("Butona basıldı!");
                business.deleteRandevu(randevuId);
                JOptionPane.showMessageDialog(null, "Kayıt başarıyla silindi.");
            }
        });
        add(rejectButton);

        setPreferredSize(new Dimension(400, 55));
    }
}
