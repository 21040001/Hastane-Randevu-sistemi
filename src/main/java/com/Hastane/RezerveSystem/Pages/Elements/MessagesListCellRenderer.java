package com.Hastane.RezerveSystem.Pages.Elements;

import javax.swing.*;

import com.Hastane.RezerveSystem.Entity.ObjectOfMessagesTable;
import com.Hastane.RezerveSystem.Entity.ObjectOfRezerveTable;

import java.awt.*;
import java.util.List;

public class MessagesListCellRenderer implements ListCellRenderer<ObjectOfMessagesTable> {
    
	@Override
	public Component getListCellRendererComponent(JList<? extends ObjectOfMessagesTable> list, ObjectOfMessagesTable value, int index, boolean isSelected, boolean cellHasFocus) {
		MessagesListPanel panel = new MessagesListPanel(value.getFull_name(), value.getTime(), value.getBody());
		if (isSelected) { 
    		panel.setBackground(list.getSelectionBackground()); 
    		panel.setForeground(list.getSelectionForeground()); 
    		} else { 
    			panel.setBackground(list.getBackground()); 
    			panel.setForeground(list.getForeground()); 
    			} 
		return panel;
	}
}
