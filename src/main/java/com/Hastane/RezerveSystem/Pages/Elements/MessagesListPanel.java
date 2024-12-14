package com.Hastane.RezerveSystem.Pages.Elements;

import javax.swing.*;
import java.awt.*;
import java.sql.Date;

public class MessagesListPanel extends JPanel {

    // Kullanıcının mesajlarını ve bilgilerini göstermek için bir panel oluşturur
    public MessagesListPanel(String fullName, Date time, String message) {
        setLayout(null); // Bileşenlerin konumunu elle ayarlamak için null layout kullanılıyor

        // Kullanıcı veya mesaj için bir görsel ekleniyor
        ImageLabel imageLabel = new ImageLabel("C:\\Users\\abdur\\OneDrive\\Resimler\\Ekran Görüntüleri\\Screenshot 2024-12-02 154512.png");
        add(imageLabel);

        // Kullanıcının tam adı burada görüntüleniyor
        JLabel firstNameLabel = new JLabel(fullName);
        firstNameLabel.setFont(new Font("Arial", Font.BOLD, 16)); // Daha dikkat çekici olması için kalın yazı tipi kullanıldı
        firstNameLabel.setBounds(80, 10, 200, 20); // Görselle çakışmayı önlemek için pozisyon ayarlandı
        add(firstNameLabel);

        // Mesajın gönderildiği zaman burada gösteriliyor
        JLabel timeLabel = new JLabel(time.toString());
        timeLabel.setBounds(320, 10, 60, 20); // Ad etiketiyle hizalı bir şekilde sağ tarafa yerleştirildi
        add(timeLabel);

        // Mesaj içeriği burada görüntüleniyor
        JLabel messageLabel = new JLabel(message);
        messageLabel.setBounds(83, 40, 270, 20); // İsim ve zaman bilgilerinin altına yerleştirildi
        add(messageLabel);

        // Panelin boyutları tüm bileşenleri sığdıracak şekilde ayarlandı
        setPreferredSize(new Dimension(380, 80));
    }
}
