package com.Hastane.RezerveSystem.Pages.Elements;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import com.Hastane.RezerveSystem.BusinessLayer.BusinessLayer;

// Liste içerisindeki randevu bilgilerini göstermek için bir panel sınıfı
public class ListPanel extends JPanel {

    private BusinessLayer business; // İş mantığı sınıfı (BusinessLayer) ile etkileşim için referans

    // ListPanel'in yapıcı metodu
    public ListPanel(int randevuId, String time, String surname, String name, String randevuType, BusinessLayer business) {
        this.business = business; // İş mantığı referansı atanır
        setLayout(null); // Bileşenlerin konumu manuel olarak ayarlanır

        // Randevu saatini göstermek için bir etiket
        JLabel timeLabel = new JLabel(time);
        timeLabel.setBounds(14, 18, 37, 20); // Saat etiketi konumlandırılır
        add(timeLabel);

        // Görsel düzen için dikey bir ayraç çizgisi
        JSeparator line = new JSeparator(SwingConstants.VERTICAL);
        line.setBounds(67, 14, 2, 41); // Çizginin konumu ve boyutu ayarlanır
        add(line);

        // Hastanın adı ve soyadı için bir etiket
        JLabel nameLabel = new JLabel(name + " " + surname);
        nameLabel.setFont(new Font("System", Font.BOLD, 13)); // Kalın bir yazı tipi ile yazılır
        nameLabel.setBounds(79, 8, 171, 20); // Etiketin konumu ayarlanır
        add(nameLabel);

        // Randevu durumu veya türünü göstermek için bir etiket
        JLabel statusLabel = new JLabel(randevuType);
        statusLabel.setBounds(79, 31, 200, 20); // Etiketin konumu ayarlanır
        add(statusLabel);

        // Randevuyu silmek için bir "Red et" butonu
        JButton rejectButton = new JButton("Red et");
        rejectButton.setBounds(320, 13, 70, 25); // Butonun konumu ve boyutu ayarlanır
        rejectButton.setFocusable(false); // Klavye odaklanması kaldırılır

        // "Red et" butonuna tıklama olayı dinleyicisi
        rejectButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.out.println("Butona basıldı!"); // Konsola bilgi yazılır
                business.deleteRandevu(randevuId); // Randevu silinir
                JOptionPane.showMessageDialog(null, "Kayıt başarıyla silindi."); // Kullanıcıya bilgi mesajı gösterilir
            }
        });
        add(rejectButton);

        // Panelin boyutu randevu bilgilerini kapsayacak şekilde ayarlanır
        setPreferredSize(new Dimension(400, 55));
    }
}
