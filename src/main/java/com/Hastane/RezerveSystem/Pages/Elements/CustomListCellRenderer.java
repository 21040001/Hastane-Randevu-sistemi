package com.Hastane.RezerveSystem.Pages.Elements;

import javax.swing.*;

import com.Hastane.RezerveSystem.BusinessLayer.BusinessLayer;
import com.Hastane.RezerveSystem.Entity.ObjectOfRezerveTable;

import java.awt.*;
import java.util.List;

public class CustomListCellRenderer implements ListCellRenderer<ObjectOfRezerveTable> {

	private BusinessLayer business; 
	public CustomListCellRenderer(BusinessLayer business) { 
		this.business = business; 
		}
	
    @Override
    public Component getListCellRendererComponent(JList<? extends ObjectOfRezerveTable> list, ObjectOfRezerveTable value, int index, boolean isSelected, boolean cellHasFocus) {
    	String time = value.getHour().toString().substring(0, 5);
    	ListPanel panel = new ListPanel(value.getId(), time, value.getName(), value.getSurname(), value.getRezerveType(), business); 
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
