package com.Hastane.RezerveSystem.Pages;

import javax.swing.*;

import com.Hastane.RezerveSystem.BusinessLayer.BusinessLayer;
import com.Hastane.RezerveSystem.Entity.ObjectOfMessagesTable;
import com.Hastane.RezerveSystem.Pages.Elements.MessagesListCellRenderer;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

public class MessagesWindow extends JFrame {

	private BusinessLayer business;
	private DefaultListModel<ObjectOfMessagesTable> listModel; 
	private JList<ObjectOfMessagesTable> listView;
	private int userId;
	private boolean itIsDoctor;
	
	public MessagesWindow(int userId, BusinessLayer business, boolean itIsDoctor) {
		this.userId = userId;
		this.itIsDoctor = itIsDoctor;
		this.business = business;
		initialize();
		loadData();
	}
	
    public void initialize() {
    	setLayout(null);

        JLabel titleLabel = new JLabel("Messages", SwingConstants.CENTER);
        titleLabel.setFont(new Font("Arial", Font.PLAIN, 25));
        titleLabel.setBounds(143, 7, 108, 30);
        add(titleLabel);

        JSeparator separator = new JSeparator();
        separator.setBounds(0, 45, 450, 4);
        add(separator);

        listModel = new DefaultListModel<>();
        listView = new JList<>(listModel);
        listView.setCellRenderer(new MessagesListCellRenderer());
        
        JScrollPane scrollPane = new JScrollPane(listView);
        scrollPane.setBounds(30, 60, 390, 350);
        add(scrollPane);
        
        JButton exit = new JButton("Exit"); 
		exit.setBounds(1, 1, 70, 25); 
		add(exit); 
		exit.addActionListener(new ActionListener() {                
            public void actionPerformed(ActionEvent e) {
            	dispose();
                new ChoosePage(business, userId, itIsDoctor).setVisible(true);
            }
        });

        setTitle("Messages");
        setSize(450, 480);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
    }
    
    private void loadData() {
        List<ObjectOfMessagesTable> data = business.getAllMessages(userId);

        // Verileri liste modeline ekleyin
        for (ObjectOfMessagesTable item : data) {
            listModel.addElement(item);
        }
    }
}
