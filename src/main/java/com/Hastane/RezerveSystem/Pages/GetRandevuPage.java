package com.Hastane.RezerveSystem.Pages;

import java.awt.Font;
import java.sql.Time;
import java.util.List;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.SwingConstants;

import com.Hastane.RezerveSystem.BusinessLayer.BusinessLayer;
import com.Hastane.RezerveSystem.Entity.ObjectOfRezerveTable;

// Kullanıcı için randevu alma sayfası
public class GetRandevuPage extends JFrame {

    private BusinessLayer business; // İş mantığını yöneten sınıf
    private int idUser; // Kullanıcı ID
    private boolean itIsDoctor; // Kullanıcı türü (doktor olup olmadığını belirtir)

    // Randevu türleri, günler ve saatler için ön tanımlı seçenekler
    String[] reserveTypes = {"Online Randevu", "Yüz Yüze Randevu"};
    String[] days = {"Monday", "Tuesday", "Wednesday", "Thursday", "Friday", "Saturday", "Sunday"};
    String[] hour = {"09:00", "10:00", "11:00", "12:00", "13:00", "14:00", "15:00", "16:00", "17:00"};

    // Constructor: İş mantığı, kullanıcı ID'si ve kullanıcı türü bilgilerini alır
    public GetRandevuPage(BusinessLayer business, int idUser, boolean itIsDoctor) {
        this.business = business;
        this.itIsDoctor = itIsDoctor;
        this.idUser = idUser;
        initialize(); // Arayüz elemanlarını başlat
    }

    // Arayüz bileşenlerinin oluşturulması ve düzenlenmesi
    public void initialize() {
        setLayout(null); // Absolute layout kullanımı

        // Başlık etiketi
        JLabel titleLabel = new JLabel("Randevu Alma");
        titleLabel.setFont(new Font("System Bold", Font.BOLD, 19));
        titleLabel.setHorizontalAlignment(SwingConstants.CENTER);
        titleLabel.setBounds(111, 23, 158, 28);
        add(titleLabel);

        // Bilgi etiketleri
        JLabel nameLabel = new JLabel("Isim :");
        nameLabel.setFont(new Font("Arial", Font.PLAIN, 17));
        nameLabel.setBounds(44, 88, 40, 20);
        add(nameLabel);

        JLabel surnameLabel = new JLabel("Surname :");
        surnameLabel.setFont(new Font("Arial", Font.PLAIN, 17));
        surnameLabel.setBounds(44, 127, 90, 26);
        add(surnameLabel);

        JLabel reserveTypeLabel = new JLabel("Randevu turu :");
        reserveTypeLabel.setFont(new Font("Arial", Font.PLAIN, 17));
        reserveTypeLabel.setBounds(44, 166, 114, 26);
        add(reserveTypeLabel);

        JLabel doctorLabel = new JLabel("Doctor :");
        doctorLabel.setFont(new Font("Arial", Font.PLAIN, 17));
        doctorLabel.setBounds(44, 201, 71, 26);
        add(doctorLabel);

        JLabel dayLabel = new JLabel("Day :");
        dayLabel.setFont(new Font("Arial", Font.PLAIN, 17));
        dayLabel.setBounds(44, 245, 40, 20);
        add(dayLabel);

        JLabel hourLabel = new JLabel("Hour :");
        hourLabel.setFont(new Font("Arial", Font.PLAIN, 17));
        hourLabel.setBounds(44, 291, 53, 26);
        add(hourLabel);

        // Kullanıcı bilgisi için metin alanları
        JTextField nameField = new JTextField();
        nameField.setBounds(159, 89, 169, 20);
        add(nameField);

        JTextField surnameField = new JTextField();
        surnameField.setBounds(159, 129, 169, 20);
        add(surnameField);

        // Randevu bilgileri için ComboBox'lar
        JComboBox<String> reserveTypeCombo = new JComboBox<>(reserveTypes);
        reserveTypeCombo.setBounds(158, 169, 169, 30);
        add(reserveTypeCombo);

        JComboBox<String> doctorCombo = new JComboBox<>(getDoctorsArray()); // Doktorların listesi
        doctorCombo.setBounds(158, 208, 169, 30);
        add(doctorCombo);

        JComboBox<String> dayCombo = new JComboBox<>(days); // Gün seçenekleri
        dayCombo.setBounds(158, 248, 169, 30);
        add(dayCombo);

        JComboBox<String> hourCombo = new JComboBox<>(hour); // Saat seçenekleri
        hourCombo.setBounds(158, 288, 169, 30);
        add(hourCombo);

        // Randevu gönderme butonu
        JButton submitButton = new JButton("Gonder");
        submitButton.setBounds(159, 368, 90, 25);
        add(submitButton);

        // "Gonder" butonu için eylem dinleyicisi
        submitButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                // Kullanıcıdan alınan bilgileri al ve bir randevu nesnesine ata
                String name = nameField.getText();
                String surname = surnameField.getText();
                String reserveType = (String) reserveTypeCombo.getSelectedItem();
                String doctor = (String) doctorCombo.getSelectedItem();
                String day = (String) dayCombo.getSelectedItem();
                String hourStr = (String) hourCombo.getSelectedItem();

                Time hour = Time.valueOf(hourStr + ":00"); // String saat bilgisini Time formatına çevir

                // Randevu bilgilerini doldur
                ObjectOfRezerveTable rezerve = new ObjectOfRezerveTable();
                rezerve.setName(name);
                rezerve.setSurname(surname);
                rezerve.setDay(day);
                rezerve.setHour(hour);
                rezerve.setDoctor(doctor);
                rezerve.setRezerveType(reserveType);
                rezerve.setIdUser(idUser);

                // Randevuyu iş mantığına ekle
                business.addRandevu(rezerve);

                // Başarı mesajı göster
                JOptionPane.showMessageDialog(null, "Randevu istegi gonderildi");
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

        setTitle("Get Randevu"); // Pencere başlığı
        setSize(500, 500); // Pencere boyutu
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); // Pencere kapatma davranışı
        setLocationRelativeTo(null); // Pencereyi ekranın ortasına yerleştir
    }

    // Doktorların listesini String[] formatında döndüren yardımcı metod
    private String[] getDoctorsArray() {
        List<String> doctorsList = business.getAllDoctors(); // Doktor listesini al
        return doctorsList.toArray(new String[0]); // Listeyi diziye çevir
    }
}
