package com.Hastane.RezerveSystem.Pages;

import javax.swing.*;
import com.Hastane.RezerveSystem.Entity.ObjectOfMessagesTable;

import java.awt.*;

public class DesktopMessageViewer extends JFrame {

    private ObjectOfMessagesTable message; // Gösterilecek mesajın verisi
    private JLabel subjectLabel; // Mesaj konusu
    private JLabel fromLabel; // Mesaj gönderen bilgisi
    private JTextArea messageBody; // Mesajın ana içeriği

    // Constructor: Mesaj verisi alınır ve arayüz başlatılır
    public DesktopMessageViewer(ObjectOfMessagesTable message) {
        this.message = message;
        initialize(); // Arayüz elemanlarını başlat
    }

    // Arayüz bileşenlerinin oluşturulması ve düzenlenmesi
    private void initialize() {
        // Pencere ayarları
        setTitle("Mesaj Detayları");
        setSize(600, 400);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE); // Pencereyi kapatıldığında ana uygulama devam eder
        setLocationRelativeTo(null);
        setLayout(new BorderLayout(10, 10));

        // Üst panel: Konu ve Kimden bilgisi
        JPanel topPanel = new JPanel(new GridLayout(2, 1, 5, 5));
        subjectLabel = new JLabel("Konu: " + message.getSubject());
        subjectLabel.setFont(new Font("Arial", Font.BOLD, 16));
        fromLabel = new JLabel("Kimden: " + message.getFull_name());
        fromLabel.setFont(new Font("Arial", Font.PLAIN, 14));
        topPanel.add(subjectLabel);
        topPanel.add(fromLabel);

        // Ana panel: Mesaj içeriği
        messageBody = new JTextArea(message.getBody());
        messageBody.setEditable(false);
        messageBody.setLineWrap(true);
        messageBody.setWrapStyleWord(true);
        messageBody.setFont(new Font("Arial", Font.PLAIN, 14));
        JScrollPane messageScrollPane = new JScrollPane(messageBody);

        // Alt panel: Kapat butonu
        JPanel bottomPanel = new JPanel();
        JButton closeButton = new JButton("Kapat");
        closeButton.addActionListener(e -> dispose());
        bottomPanel.add(closeButton);

        // Panelleri pencereye ekle
        add(topPanel, BorderLayout.NORTH);
        add(messageScrollPane, BorderLayout.CENTER);
        add(bottomPanel, BorderLayout.SOUTH);
    }
}
