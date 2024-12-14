package com.Hastane.RezerveSystem.Pages.Elements;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.geom.Ellipse2D;
import java.io.File;
import java.io.IOException;

// Görüntüleri yuvarlak bir çerçeve içinde göstermek için özelleştirilmiş bir JLabel sınıfı
public class ImageLabel extends JLabel {

    private Image image; // Yüklenen görüntüyü saklayan değişken

    // Yapıcı metot: Görüntü dosyasını belirtilen yol üzerinden yükler
    public ImageLabel(String imagePath) {
        try {
            // Görüntü dosyasını belirtilen yoldan okur
            image = ImageIO.read(new File(imagePath));
            
            // Görüntüyü ölçeklendirerek boyutunu ayarlar
            Image scaledImage = image.getScaledInstance(53, 53, Image.SCALE_SMOOTH);
            
            // Görüntüyü JLabel'e simge olarak ekler
            setIcon(new ImageIcon(scaledImage));
            
            // JLabel'in konum ve boyutlarını belirler
            setBounds(14, 10, 53, 53);
        } catch (IOException e) {
            // Görüntü yüklenemezse hata detaylarını yazdırır
            e.printStackTrace();
        }
    }

    // JLabel'in görünümünü özelleştiren metot
    @Override
    protected void paintComponent(Graphics g) {
        if (image != null) { // Eğer bir görüntü yüklendiyse
            Graphics2D g2d = (Graphics2D) g.create();
            
            // Çizilecek çerçevenin çapını belirler
            int diameter = Math.min(getWidth(), getHeight());
            
            // Yuvarlak bir çerçeve oluşturur
            Ellipse2D.Double circle = new Ellipse2D.Double(0, 0, diameter, diameter);
            
            // Grafik nesnesini yuvarlak çerçeve ile sınırlar
            g2d.setClip(circle);
            
            // Görüntüyü yuvarlak çerçevenin içine çizer
            g2d.drawImage(image, 0, 0, diameter, diameter, this);
            
            // Kaynakları serbest bırakır
            g2d.dispose();
        }
    }
}
