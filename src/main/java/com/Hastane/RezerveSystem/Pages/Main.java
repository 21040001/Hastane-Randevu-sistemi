package com.Hastane.RezerveSystem.Pages;

import com.Hastane.RezerveSystem.BusinessLayer.BusinessLayer;

// Programın giriş noktası olan ana sınıf
public class Main {

    private BusinessLayer business; // İş mantığını yöneten sınıf

    // Constructor: İş mantığını alır ve GUI'yi başlatır
    public Main(BusinessLayer business) {
        this.business = business;
        createAndShowGUI(); // Grafik arayüzü oluştur ve göster
    }

    // Grafik arayüzünün oluşturulması ve başlatılması
    public void createAndShowGUI() {
        // Swing işlemlerini Event Dispatch Thread (EDT) üzerinde güvenli bir şekilde başlatır
        javax.swing.SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                // LogIn penceresini görünür hale getirir
                new LogIn(business).setVisible(true);
            }
        });
    }
}
