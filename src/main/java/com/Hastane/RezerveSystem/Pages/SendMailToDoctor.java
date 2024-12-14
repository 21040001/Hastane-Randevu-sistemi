package com.Hastane.RezerveSystem.Pages;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Date;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

import com.Hastane.RezerveSystem.BusinessLayer.BusinessLayer;
import com.Hastane.RezerveSystem.Entity.ObjectOfMessagesTable;

// Doktora mesaj gönderme ekranı
public class SendMailToDoctor extends JFrame {

    private BusinessLayer business; // İş mantığını yöneten sınıf
    private int idUser; // Kullanıcı ID'si
    private boolean itIsDoctor; // Kullanıcı türü (doktor olup olmadığını belirtir)

    // Constructor: İş mantığı, kullanıcı ID'si ve kullanıcı türü bilgilerini alır
    public SendMailToDoctor(BusinessLayer business, int idUser, boolean itIsDoctor) {
        this.idUser = idUser;
        this.itIsDoctor = itIsDoctor;
        this.business = business;
        initialize(); // Arayüz elemanlarını başlat
    }

    // Arayüz bileşenlerinin oluşturulması ve düzenlenmesi
    public void initialize() {
        setLayout(null); // Absolute layout kullanılıyor

        // Pencerenin başlığı
        JLabel titleLabel = new JLabel("Send Mail to Doctor", SwingConstants.CENTER);
        titleLabel.setFont(new Font("Arial", Font.BOLD, 19));
        titleLabel.setBounds(199, 22, 202, 28);
        add(titleLabel);

        // Doktor seçimi için etiket
        JLabel toLabel = new JLabel("Whom ?");
        toLabel.setFont(new Font("Arial", Font.BOLD, 13));
        toLabel.setBounds(40, 70, 58, 20);
        add(toLabel);

        // Doktor listesini içeren bir açılır menü
        JComboBox<String> doctorCombo = new JComboBox<>(getDoctorsArray());
        doctorCombo.setBounds(108, 65, 280, 30);
        add(doctorCombo);

        // Konu için etiket
        JLabel subjectLabel = new JLabel("Subject ");
        subjectLabel.setFont(new Font("Arial", Font.BOLD, 13));
        subjectLabel.setBounds(40, 119, 58, 20);
        add(subjectLabel);

        // Konu başlığı için metin alanı
        JTextField subjectField = new JTextField();
        subjectField.setBounds(108, 114, 280, 30);
        add(subjectField);

        // Mesaj metni için metin alanı
        JTextArea messageArea = new JTextArea();
        messageArea.setBounds(40, 174, 496, 200);
        add(messageArea);
        messageArea.setBorder(BorderFactory.createEmptyBorder(12, 12, 12, 12)); // Kenar boşlukları ekleniyor

        // Mesaj gönderme butonu
        JButton sendButton = new JButton("Send");
        sendButton.setBounds(260, 400, 80, 30);
        add(sendButton);

        // "Send" butonu için eylem dinleyicisi
        sendButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                LocalDateTime currentDateTime = LocalDateTime.now(); // Şimdiki zamanı al
                DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd"); // Tarih formatı
                int sender = idUser; // Mesaj gönderen kullanıcı ID
                int receiver = business.getDoctorIdByName((String) doctorCombo.getSelectedItem()); // Seçilen doktorun ID'si
                String body = messageArea.getText(); // Mesaj metni
                String time = currentDateTime.format(formatter); // Tarihi string olarak biçimlendir

                // Mesaj tarihi SQL formatına çevrilir
                Date sqlDate = Date.valueOf(time);

                // Kullanıcının tam adı
                String fullName = business.getUserById(idUser).getName() + " " + business.getUserById(idUser).getSurname();

                // Mesaj objesi oluşturuluyor ve dolduruluyor
                ObjectOfMessagesTable object = new ObjectOfMessagesTable();
                object.setSender(sender);
                object.setReceiver(receiver);
                object.setFull_name(fullName);
                object.setBody(body);
                object.setTime(sqlDate);

                // İş mantığı üzerinden mesaj gönderimi
                business.sendToMail(object);

                // Kullanıcıya bilgilendirme mesajı
                JOptionPane.showMessageDialog(null, "Randevu mesaji gönderildi");
            }
        });

        // Çıkış butonu
        JButton exit = new JButton("Exit");
        exit.setBounds(1, 1, 70, 25);
        add(exit);
        exit.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                dispose(); // Mevcut pencereyi kapat
                new ChoosePage(business, idUser, itIsDoctor).setVisible(true); // Ana sayfaya dön
            }
        });

        setTitle("Send Message to Doctor"); // Pencere başlığı
        setSize(600, 500); // Pencere boyutu
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); // Pencere kapatma davranışı
        setLocationRelativeTo(null); // Pencereyi ekranın ortasına yerleştir
    }

    // Doktorların listesini String[] formatında döndüren yardımcı metod
    private String[] getDoctorsArray() {
        List<String> doctorsList = business.getAllDoctors(); // Tüm doktorları liste olarak al
        return doctorsList.toArray(new String[0]); // Listeyi diziye çevir
    }
}
