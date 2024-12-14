package com.Hastane.RezerveSystem.Pages.Elements;

import javax.swing.*;

import com.Hastane.RezerveSystem.BusinessLayer.BusinessLayer;
import com.Hastane.RezerveSystem.Entity.ObjectOfRezerveTable;

import java.awt.*;
import java.util.List;

// Özelleştirilmiş bir liste hücre oluşturucu (renderer) sınıfı
// Her hücreyi bir ListPanel bileşeniyle temsil eder
public class CustomListCellRenderer implements ListCellRenderer<ObjectOfRezerveTable> {

    private BusinessLayer business; // İş mantığı (BusinessLayer) sınıfına referans

    // Yapıcı metot: İş mantığı referansını alır
    public CustomListCellRenderer(BusinessLayer business) { 
        this.business = business; 
    }
    
    // Her bir liste hücresi için özelleştirilmiş bir bileşen döner
    @Override
    public Component getListCellRendererComponent(JList<? extends ObjectOfRezerveTable> list, ObjectOfRezerveTable value, int index, boolean isSelected, boolean cellHasFocus) {
        // Saat bilgisini sadece saat ve dakika olarak kısaltır
        String time = value.getHour().toString().substring(0, 5);
        
        // Randevu bilgilerini göstermek için ListPanel oluşturulur
        ListPanel panel = new ListPanel(value.getId(), time, value.getName(), value.getSurname(), value.getRezerveType(), business);
        
        // Eğer hücre seçiliyse, arka plan ve metin rengi seçim rengine ayarlanır
        if (isSelected) { 
            panel.setBackground(list.getSelectionBackground()); 
            panel.setForeground(list.getSelectionForeground()); 
        } else { 
            // Seçili değilse varsayılan liste renkleri kullanılır
            panel.setBackground(list.getBackground()); 
            panel.setForeground(list.getForeground()); 
        }
        
        // Hücreyi temsil eden panel geri döndürülür
        return panel;
    }
}
