package com.Hastane.RezerveSystem.Pages;

import javax.swing.*;
import com.Hastane.RezerveSystem.BusinessLayer.BusinessLayer;
import com.Hastane.RezerveSystem.Entity.ObjectOfMessagesTable;
import com.Hastane.RezerveSystem.Pages.Elements.MessagesListCellRenderer;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

// Mesajlar penceresi: Kullanıcıya gelen ve gönderilen mesajları listelemek için bir arayüz sağlar
public class MessagesWindow extends JFrame {

    private BusinessLayer business; // İş mantığını yöneten sınıf
    private DefaultListModel<ObjectOfMessagesTable> listModel; // Mesaj listesini tutan model
    private JList<ObjectOfMessagesTable> listView; // Mesajları görüntülemek için JList
    private int userId; // Kullanıcı ID
    private boolean itIsDoctor; // Kullanıcı türü (doktor olup olmadığını belirtir)

    // Constructor: Kullanıcı bilgileri, iş mantığı ve kullanıcı türü bilgilerini alır
    public MessagesWindow(int userId, BusinessLayer business, boolean itIsDoctor) {
        this.userId = userId;
        this.itIsDoctor = itIsDoctor;
        this.business = business;
        initialize(); // Arayüz elemanlarını başlat
        loadData(); // Mesaj verilerini yükle
    }

    // Arayüz bileşenlerinin oluşturulması ve düzenlenmesi
    public void initialize() {
        setLayout(null); // Absolute layout kullanımı

        // Pencere başlığı etiketi
        JLabel titleLabel = new JLabel("Messages", SwingConstants.CENTER);
        titleLabel.setFont(new Font("Arial", Font.PLAIN, 25));
        titleLabel.setBounds(143, 7, 108, 30);
        add(titleLabel);

        // Başlık altında bir ayırıcı çizgi
        JSeparator separator = new JSeparator();
        separator.setBounds(0, 45, 450, 4);
        add(separator);

        // Mesajları göstermek için liste modeli ve görünümü
        listModel = new DefaultListModel<>();
        listView = new JList<>(listModel);
        listView.setVisibleRowCount(4); // Görünecek maksimum satır sayısı
        listView.setCellRenderer(new MessagesListCellRenderer()); // Mesajları özelleştirilmiş şekilde göstermek için cell renderer

        listView.addListSelectionListener(e -> {
            if (!e.getValueIsAdjusting()) {
                ObjectOfMessagesTable selectedMessage = listView.getSelectedValue();
                if (selectedMessage != null) {
                    new DesktopMessageViewer(selectedMessage).setVisible(true);
                }
            }
        });
        
        // Mesaj listesi için kaydırılabilir panel
        JScrollPane scrollPane = new JScrollPane(listView);
        scrollPane.setBounds(30, 60, 390, 350);
        add(scrollPane);

        // Çıkış butonu: Ana seçim sayfasına geri döner
        JButton exit = new JButton("Exit");
        exit.setBounds(1, 1, 70, 25);
        add(exit);
        exit.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                dispose(); // Mevcut pencereyi kapat
                new ChoosePage(business, userId, itIsDoctor).setVisible(true); // Ana seçim sayfasına yönlendir
            }
        });

        // Pencere başlığı ve özellikleri
        setTitle("Messages"); // Pencere başlığı
        setSize(450, 430); // Pencere boyutu
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); // Pencere kapatma davranışı
        setLocationRelativeTo(null); // Pencereyi ekranın ortasına yerleştir
    }

    // Kullanıcının tüm mesajlarını liste modeline yükleyen metod
    private void loadData() {
    	List<ObjectOfMessagesTable> data;
    	if(itIsDoctor) {
    		data = business.getAllDoctorMessages(userId); // Doctor mesajlarını getir
    	}else {
    		data = business.getAllUserMessages(userId); // Kullanıcı mesajlarını getir
    	}
        

        // Mesajları liste modeline ekle
        for (ObjectOfMessagesTable item : data) {
            listModel.addElement(item);
        }
    }
}
