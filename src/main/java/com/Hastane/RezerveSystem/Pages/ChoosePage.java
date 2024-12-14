package com.Hastane.RezerveSystem.Pages;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import com.Hastane.RezerveSystem.BusinessLayer.BusinessLayer;

public class ChoosePage extends JFrame {

    private BusinessLayer business;
    private int idUser;
    private boolean itIsDoctor;

    public ChoosePage(BusinessLayer business, int idUser, boolean itIsDoctor) {
    	this.itIsDoctor = itIsDoctor;
        this.business = business;
        this.idUser = idUser;
        initialize();
    }

    public void initialize() {
        setLayout(null);

        JLabel label = new JLabel("Lütfen Birini Seçerek İlerleyiniz", SwingConstants.CENTER);
        label.setFont(new Font("Arial", Font.PLAIN, 23));
        label.setBounds(50, 76, 328, 41);
        add(label);

        JButton button1 = new JButton("Hastaneden Randevu");
        button1.setBounds(20, 185, 177, 30);
        button1.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
            	dispose();
            	new GetRandevuPage(business, idUser, itIsDoctor).setVisible(true);
            }
        });
        add(button1);

        JButton button2 = new JButton("Uzaktan Doktor Randevu");
        button2.setBounds(220, 185, 177, 30);
        button2.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
            	dispose();
        		new SendMailToDoctor(business, idUser,itIsDoctor).setVisible(true);
            }
        });
        add(button2);

        JButton button3 = new JButton("Profil Sayfasına Git");
        button3.setBounds(140, 244, 141, 30);
        button3.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
            	if(itIsDoctor) {
            		System.out.println("Doctor");
            		dispose();
            		new DoctorAccountPage(business,idUser, itIsDoctor).setVisible(true);
            	}else {
            		dispose();
            		new UserAccountPage(business, idUser, itIsDoctor).setVisible(true);
            	}
            }
        });
        add(button3);

        JButton button4 = new JButton("Çıkış Yap");
        button4.setBounds(140, 294, 141, 30);
        button4.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
            	dispose();
            	new LogIn(business).setVisible(true);
            }
        });
        add(button4);

        setTitle("Choose Page");
        setSize(450, 400);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
    }
}
