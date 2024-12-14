package com.Hastane.RezerveSystem.Pages;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.List;

import javax.swing.*;

import com.Hastane.RezerveSystem.BusinessLayer.BusinessLayer;
import com.Hastane.RezerveSystem.Entity.ObjectOfRezerveTable;
import com.Hastane.RezerveSystem.Pages.Elements.CustomListCellRenderer;
import com.Hastane.RezerveSystem.Pages.Elements.ListPanel;

// Doktor Hesap Sayfası
// Doktorun kişisel bilgilerini, randevularını ve diğer işlemlerini görüntülemek için bir arayüz sağlar.
public class DoctorAccountPage extends JFrame {
    private BusinessLayer business; // İş mantığı için BusinessLayer nesnesi
    private int id; // Doktor ID
    private boolean itIsDoctor; // Kullanıcı tipi (doktor olup olmadığını belirtir)
    private DefaultListModel<ObjectOfRezerveTable> listModel; // Randevuları tutan liste modeli
    private JList<ObjectOfRezerveTable> listView; // Randevuları görüntülemek için JList

    // Constructor: Doktorun bilgilerini ve iş mantığını alır, arayüzü başlatır
    public DoctorAccountPage(BusinessLayer business, int id, boolean itIsDoctor) {
        this.business = business;
        this.itIsDoctor = itIsDoctor;
        this.id = id;
        initialize(); // Arayüz elemanlarını kur
        loadData(); // Randevu verilerini yükle
    }

    // Arayüz elemanlarının oluşturulması ve konumlandırılması
    public void initialize() {
        setLayout(null); // Layout olarak absolute layout kullanılıyor

        // Doktorun adını ve soyadını gösteren etiket
        JLabel doctorLabel = new JLabel(business.getDoctorByID(id).getName() + " " + business.getDoctorByID(id).getSurname());
        doctorLabel.setFont(new Font("Arial", Font.BOLD, 19));
        doctorLabel.setBounds(22, 71, 336, 28);
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
                // Randevular butonu için eylemler tanımlanabilir
            }
        });

        // Randevuların listesi için liste modeli ve görünüm oluşturuluyor
        listModel = new DefaultListModel<>();
        listView = new JList<>(listModel);
        listView.setVisibleRowCount(5); // Görünecek maksimum satır sayısı
        listView.setCellRenderer(new CustomListCellRenderer(business)); // Özel hücre tasarımı

        // Listeye fare tıklamalarını dinleyen bir mouse listener ekleniyor
        listView.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                int index = listView.locationToIndex(e.getPoint()); // Tıklanan öğeyi bul
                if (index >= 0) {
                    ObjectOfRezerveTable item = listModel.get(index); // İlgili öğeyi al
                    ListPanel panel = (ListPanel) listView.getCellRenderer()
                            .getListCellRendererComponent(listView, item, index, listView.isSelectedIndex(index), true);

                    // Panel üzerindeki butonlar tıklanmışsa eylemleri tetikle
                    for (Component component : panel.getComponents()) {
                        if (component instanceof JButton) {
                            Rectangle bounds = component.getBounds();
                            if (bounds.contains(e.getPoint())) {
                                ((JButton) component).doClick(); // Butonun click olayını çağır
                            }
                        }
                    }
                }
            }
        });

        // Randevular listesini kaydırılabilir hale getiren bir ScrollPane
        JScrollPane scrollPane = new JScrollPane(listView);
        scrollPane.setBounds(17, 225, 410, 148);
        add(scrollPane);

        // Doktorun adres bilgilerini gösteren etiketler
        JLabel addressLabel = new JLabel("Adres :");
        addressLabel.setFont(new Font("Arial", Font.PLAIN, 17));
        addressLabel.setBounds(22, 184, 71, 27);
        add(addressLabel);

        JLabel addressDetailLabel = new JLabel(business.getDoctorByID(id).getAdress());
        addressDetailLabel.setBounds(114, 188, 222, 20);
        add(addressDetailLabel);

        // Doktorun uzmanlık alanını gösteren etiket
        JLabel specialtyLabel = new JLabel(business.getDoctorByID(id).getProfesion());
        specialtyLabel.setFont(new Font("Calibri", Font.PLAIN, 17));
        specialtyLabel.setBounds(80, 100, 71, 20);
        add(specialtyLabel);

        // Konum bilgisi (örnek olarak Ankara verilmiş)
        JLabel locationLabel = new JLabel("Ankara");
        locationLabel.setFont(new Font("Calibri", Font.PLAIN, 17));
        locationLabel.setBounds(22, 100, 50, 20);
        add(locationLabel);

        // Deneyim ve dil bilgileri için etiketler
        JLabel experienceLabel = new JLabel("Deneyim :");
        experienceLabel.setFont(new Font("Arial", Font.PLAIN, 17));
        experienceLabel.setBounds(22, 132, 95, 26);
        add(experienceLabel);

        JLabel languageLabel = new JLabel("Dil :");
        languageLabel.setFont(new Font("Arial", Font.PLAIN, 17));
        languageLabel.setBounds(22, 158, 92, 26);
        add(languageLabel);

        JLabel experienceDetailLabel = new JLabel(business.getDoctorByID(id).getExperience());
        experienceDetailLabel.setBounds(117, 135, 49, 20);
        add(experienceDetailLabel);

        JLabel languageDetailLabel = new JLabel(business.getDoctorByID(id).getLanguages());
        languageDetailLabel.setBounds(117, 161, 118, 20);
        add(languageDetailLabel);

        // Çıkış butonu
        JButton exit = new JButton("Exit");
        exit.setBounds(1, 1, 70, 25);
        add(exit);
        exit.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                dispose(); // Mevcut pencereyi kapat
                new ChoosePage(business, id, itIsDoctor).setVisible(true); // Seçim sayfasına dön
            }
        });

        setTitle("Hesabım"); // Pencere başlığı
        setSize(480, 500); // Pencere boyutu
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); // Pencere kapatma davranışı
        setLocationRelativeTo(null); // Pencereyi ekranın ortasına yerleştir
    }

    // Doktorun randevu verilerini yükleme
    private void loadData() {
        String name = business.getDoctorByID(id).getName(); // Doktor adı alınıyor
        List<ObjectOfRezerveTable> data = business.getDoctorsRezervesByDoctorName(name); // Randevular getiriliyor

        // Randevuları liste modeline ekleme
        for (ObjectOfRezerveTable item : data) {
            listModel.addElement(item);
        }
    }
}
