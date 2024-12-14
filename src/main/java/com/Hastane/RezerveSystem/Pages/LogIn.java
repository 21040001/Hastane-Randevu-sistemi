package com.Hastane.RezerveSystem.Pages;

import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;

import com.Hastane.RezerveSystem.BusinessLayer.BusinessLayer;

// Giriş ekranı: Kullanıcıların TC numarası ve şifre ile giriş yapmasını sağlar
public class LogIn extends JFrame {

    private BusinessLayer business; // İş mantığını yöneten sınıf
    private JTextField usernameField; // Kullanıcı TC numarasını girecek alan
    private JPasswordField passwordField; // Kullanıcı şifresini girecek alan

    // Constructor: İş mantığı sınıfını alır ve arayüzü başlatır
    public LogIn(BusinessLayer business) {
        this.business = business;
        initialize(); // Arayüz elemanlarını başlat
    }

    // Arayüz bileşenlerinin oluşturulması ve düzenlenmesi
    public void initialize() {
        setLayout(null); // Absolute layout kullanımı

        // Kullanıcıdan TC numarasını isteyen etiket ve metin alanı
        JLabel usernameLabel = new JLabel("TC Number:");
        usernameLabel.setBounds(70, 100, 80, 25);
        add(usernameLabel);

        usernameField = new JTextField();
        usernameField.setBounds(170, 100, 160, 25);
        add(usernameField);

        // Kullanıcıdan şifre isteyen etiket ve şifre alanı
        JLabel passwordLabel = new JLabel("Password:");
        passwordLabel.setBounds(70, 140, 80, 25);
        add(passwordLabel);

        passwordField = new JPasswordField();
        passwordField.setBounds(170, 140, 160, 25);
        add(passwordField);

        // Giriş butonu
        JButton loginButton = new JButton("Login");
        loginButton.setBounds(170, 180, 80, 25);
        add(loginButton);

        // Giriş butonuna eylem dinleyicisi ekleniyor
        loginButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                // Kullanıcının girdiği TC numarası ve şifre alınıyor
                String TcNumber = usernameField.getText();
                String password = new String(passwordField.getPassword());

                // TC numarasına göre kullanıcı ID'si ve doktor olup olmadığı bilgisi alınıyor
                int idUser = business.getIdUserByTcNumber(Integer.valueOf(TcNumber));
                boolean doctor = business.itIsDoctor(Integer.valueOf(TcNumber));

                // Şifre doğrulama
                if (password.equals(business.getPasswordByTcNumber(Integer.valueOf(TcNumber)))) {
                    // Şifre doğruysa başarılı giriş mesajı ve ana sayfaya yönlendirme
                    JOptionPane.showMessageDialog(null, "Login Successful!");
                    dispose(); // Giriş ekranını kapat
                    new ChoosePage(business, idUser, doctor).setVisible(true); // Kullanıcıyı seçim sayfasına yönlendir
                } else {
                    // Şifre yanlışsa hata mesajı
                    JOptionPane.showMessageDialog(null, "Invalid TcNumber or Password");
                }
            }
        });

        // Pencere başlığı ve özellikleri
        setTitle("Log In"); // Pencere başlığı
        setSize(400, 300); // Pencere boyutu
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); // Kapatma davranışı
        setLocationRelativeTo(null); // Pencereyi ekranın ortasına yerleştir
    }
}
