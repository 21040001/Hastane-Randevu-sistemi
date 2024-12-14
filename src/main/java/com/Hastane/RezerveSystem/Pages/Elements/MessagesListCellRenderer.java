package com.Hastane.RezerveSystem.Pages.Elements;

import javax.swing.*;
import com.Hastane.RezerveSystem.Entity.ObjectOfMessagesTable;
import com.Hastane.RezerveSystem.Entity.ObjectOfRezerveTable;

import java.awt.*;
import java.util.List;

// Mesajları göstermek için özelleştirilmiş bir hücre oluşturucu (renderer) sınıfı
public class MessagesListCellRenderer implements ListCellRenderer<ObjectOfMessagesTable> {
    
    @Override
    public Component getListCellRendererComponent(JList<? extends ObjectOfMessagesTable> list, ObjectOfMessagesTable value, int index, boolean isSelected, boolean cellHasFocus) {
        
        // Her bir liste öğesi için özel bir panel oluşturulur
        MessagesListPanel panel = new MessagesListPanel(value.getFull_name(), value.getTime(), value.getBody());
        
        // Eğer hücre seçiliyse, arka plan ve metin rengi seçim rengine göre ayarlanır
        if (isSelected) { 
            panel.setBackground(list.getSelectionBackground()); 
            panel.setForeground(list.getSelectionForeground()); 
        } else { 
            // Eğer hücre seçili değilse, arka plan ve metin rengi varsayılan liste renklerine ayarlanır
            panel.setBackground(list.getBackground()); 
            panel.setForeground(list.getForeground()); 
        }
        
        // Hücreyi temsil eden panel geri döndürülür
        return panel;
    }
}
