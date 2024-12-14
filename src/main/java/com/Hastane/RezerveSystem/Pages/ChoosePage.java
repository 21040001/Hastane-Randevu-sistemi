package com.Hastane.RezerveSystem.Pages;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import com.Hastane.RezerveSystem.BusinessLayer.BusinessLayer;

// Kullanıcı veya doktorun seçim yapabileceği ana menü sayfası
public class ChoosePage extends JFrame {

    private BusinessLayer business; // İş mantığını yöneten sınıf
    private int idUser; // Kullanıcı ID'si
    private boolean itIsDoctor; // Kullanıcı tipi (doktor olup olmadığını belirtir)

    // Constructor: İş mantığı, kullanıcı ID'si ve kullanıcı tipi bilgilerini alır
    public ChoosePage(BusinessLayer business, int idUser, boolean itIsDoctor) {
        this.itIsDoctor = itIsDoctor;
        this.business = business;
        this.idUser = idUser;
        initialize(); // Arayüz elemanlarını başlat
    }

    // Arayüz bileşenlerinin oluşturulması ve düzenlenmesi
    public void initialize() {
        setLayout(null); // Absolute layout kullanımı

        // Başlık etiketi
        JLabel label = new JLabel("Lütfen Birini Seçerek İlerleyiniz", SwingConstants.CENTER);
        label.setFont(new Font("Arial", Font.PLAIN, 23));
        label.setBounds(50, 76, 328, 41);
        add(label);

        // Hastaneden randevu almayı başlatan buton
        JButton button1 = new JButton("Hastaneden Randevu");
        button1.setBounds(20, 185, 177, 30);
        button1.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                dispose(); // Mevcut pencereyi kapat
                new GetRandevuPage(business, idUser, itIsDoctor).setVisible(true); // Hastaneden randevu sayfasına yönlendir
            }
        });
        add(button1);

        // Uzaktan doktor randevusu almayı başlatan buton
        JButton button2 = new JButton("Uzaktan Doktor Randevu");
        button2.setBounds(220, 185, 177, 30);
        button2.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                dispose(); // Mevcut pencereyi kapat
                new SendMailToDoctor(business, idUser, itIsDoctor).setVisible(true); // Doktora mesaj gönderme sayfasına yönlendir
            }
        });
        add(button2);

        // Profil sayfasına geçiş yapan buton
        JButton button3 = new JButton("Profil Sayfasına Git");
        button3.setBounds(140, 244, 141, 30);
        button3.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                if (itIsDoctor) { // Kullanıcı doktor ise doktor profil sayfasına yönlendirilir
                    System.out.println("Doctor");
                    dispose();
                    new DoctorAccountPage(business, idUser, itIsDoctor).setVisible(true);
                } else { // Kullanıcı hasta ise kullanıcı profil sayfasına yönlendirilir
                    dispose();
                    new UserAccountPage(business, idUser, itIsDoctor).setVisible(true);
                }
            }
        });
        add(button3);

        // Çıkış yapmayı sağlayan buton
        JButton button4 = new JButton("Çıkış Yap");
        button4.setBounds(140, 294, 141, 30);
        button4.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                dispose(); // Mevcut pencereyi kapat
                new LogIn(business).setVisible(true); // Giriş ekranına yönlendir
            }
        });
        add(button4);

        // Pencere başlığı ve özellikleri
        setTitle("Choose Page"); // Pencere başlığı
        setSize(450, 400); // Pencere boyutu
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); // Pencere kapatma davranışı
        setLocationRelativeTo(null); // Pencereyi ekranın ortasına yerleştir
    }
}
