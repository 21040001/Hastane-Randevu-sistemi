package com.Hastane.RezerveSystem.Pages;

import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

import javax.swing.*;

import com.Hastane.RezerveSystem.BusinessLayer.BusinessLayer;
import com.Hastane.RezerveSystem.Entity.ObjectOfRezerveTable;
import com.Hastane.RezerveSystem.Pages.Elements.CustomListCellRenderer;

// Kullanıcı Hesap Sayfası: Kullanıcının bilgilerini ve randevularını görüntüler
public class UserAccountPage extends JFrame {

    private BusinessLayer business; // İş mantığını yöneten sınıf
    private DefaultListModel<ObjectOfRezerveTable> listModel; // Randevu listesi modeli
    private JList<ObjectOfRezerveTable> listView; // Randevuları göstermek için liste
    private int id; // Kullanıcı ID
    private boolean itIsDoctor; // Kullanıcı türü (doktor olup olmadığını belirtir)

    // Constructor: İş mantığı, kullanıcı ID'si ve kullanıcı türü bilgilerini alır
    public UserAccountPage(BusinessLayer business, int id, boolean itIsDoctor) {
        this.id = id;
        this.itIsDoctor = itIsDoctor;
        this.business = business;
        initialize(); // Arayüz elemanlarını başlat
        loadData(); // Kullanıcı randevularını yükle
    }

    // Arayüz bileşenlerinin oluşturulması ve düzenlenmesi
    public void initialize() {
        setLayout(null); // Absolute layout kullanımı

        // Kullanıcının adı ve soyadını gösteren etiket
        JLabel doctorLabel = new JLabel(capitalizeFirstLetter(business.getUserById(id).getName()) + " " +
                capitalizeFirstLetter(business.getUserById(id).getSurname()));
        doctorLabel.setFont(new Font("Arial", Font.BOLD, 19));
        doctorLabel.setBounds(23, 85, 336, 28);
        add(doctorLabel);

        // Mesajlar butonu
        JButton messagesButton = new JButton("Mesajlar");
        messagesButton.setBounds(302, 21, 90, 25);
        add(messagesButton);
        messagesButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                dispose(); // Mevcut pencereyi kapat
                new MessagesWindow(id, business, itIsDoctor).setVisible(true); // Mesajlar penceresini aç
            }
        });

        // Randevular butonu
        JButton appointmentsButton = new JButton("Randevular");
        appointmentsButton.setBounds(209, 21, 90, 25);
        add(appointmentsButton);
        appointmentsButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                // Randevular butonu için eylemler buraya eklenebilir
            }
        });

        // Kullanıcı adres bilgilerini gösteren etiket
        JLabel addressLabel = new JLabel("Adress :");
        addressLabel.setFont(new Font("Arial", Font.PLAIN, 17));
        addressLabel.setBounds(23, 126, 71, 27);
        add(addressLabel);

        JLabel addressDetailLabel = new JLabel(business.getUserById(id).getAdress());
        addressDetailLabel.setBounds(124, 130, 222, 20);
        add(addressDetailLabel);

        // Randevular başlığı
        JLabel appointmentsLabel = new JLabel("Randevular : ");
        appointmentsLabel.setFont(new Font("Arial", Font.PLAIN, 17));
        appointmentsLabel.setBounds(23, 169, 102, 27);
        add(appointmentsLabel);

        // Randevular listesi için liste ve kaydırma çubuğu
        listModel = new DefaultListModel<>();
        listView = new JList<>(listModel);
        listView.setCellRenderer(new CustomListCellRenderer(business)); // Özel tasarımlı liste hücreleri

        JScrollPane scrollPane = new JScrollPane(listView);
        scrollPane.setBounds(23, 201, 410, 262);
        add(scrollPane);

        // Çıkış butonu
        JButton exit = new JButton("Exit");
        exit.setBounds(1, 1, 70, 25);
        add(exit);
        exit.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                dispose(); // Mevcut pencereyi kapat
                new ChoosePage(business, id, itIsDoctor).setVisible(true); // Ana sayfaya dön
            }
        });

        setTitle("My Account"); // Pencere başlığı
        setSize(455, 505); // Pencere boyutu
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); // Pencere kapatma davranışı
        setLocationRelativeTo(null); // Pencereyi ekranın ortasına yerleştir
    }

    // Kullanıcının randevu verilerini liste modeline yükleyen metod
    private void loadData() {
        List<ObjectOfRezerveTable> data = business.getUserRezervesByUserId(id); // Kullanıcının randevularını al

        // Randevuları liste modeline ekle
        for (ObjectOfRezerveTable item : data) {
            listModel.addElement(item);
        }
    }

    // Metin baş harflerini büyük harfe dönüştüren yardımcı metod
    public static String capitalizeFirstLetter(String str) {
        if (str == null || str.isEmpty()) {
            return str; // Eğer metin boşsa orijinal metni döndür
        }
        return str.substring(0, 1).toUpperCase() + str.substring(1); // İlk harfi büyük yap, geri kalanını olduğu gibi ekle
    }
}
