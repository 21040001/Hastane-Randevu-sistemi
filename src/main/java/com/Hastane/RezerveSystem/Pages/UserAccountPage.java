package com.Hastane.RezerveSystem.Pages;

import java.awt.CardLayout;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

import javax.swing.*;

import com.Hastane.RezerveSystem.BusinessLayer.BusinessLayer;
import com.Hastane.RezerveSystem.Entity.ObjectOfRezerveTable;
import com.Hastane.RezerveSystem.Pages.Elements.CustomListCellRenderer;

public class UserAccountPage extends JFrame{
	private BusinessLayer business;
	private DefaultListModel<ObjectOfRezerveTable> listModel; 
	private JList<ObjectOfRezerveTable> listView;
	private int id;
	private boolean itIsDoctor;
	   
	public UserAccountPage(BusinessLayer business, int id,boolean itIsDoctor) { 
		this.id = id;
		this.itIsDoctor=itIsDoctor;
		this.business = business;
		initialize(); 
		loadData();
		}
	
	public void initialize() {
		setLayout(null); // Absolute layout kullanımı 
		// Etiketler ve metin alanları 
		JLabel doctorLabel = new JLabel(capitalizeFirstLetter(business.getUserById(id).getName())+ " "+capitalizeFirstLetter(business.getUserById(id).getSurname())); 
		doctorLabel.setFont(new Font("Arial", Font.BOLD, 19)); 
		doctorLabel.setBounds(23, 85, 336, 28); 
		add(doctorLabel); 
		
		JButton messagesButton = new JButton("Mesajlar"); 
		messagesButton.setBounds(302, 21, 90, 25); 
		add(messagesButton); 
		messagesButton.addActionListener(new ActionListener() {                
            public void actionPerformed(ActionEvent e) {
            	dispose();
                new MessagesWindow(id, business,itIsDoctor).setVisible(true);
            }
        });
		JButton appointmentsButton = new JButton("Randevular"); 
		appointmentsButton.setBounds(209, 21, 90, 25); 
		add(appointmentsButton); 
		appointmentsButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
            	
            }
        });
		
		JLabel addressLabel = new JLabel("Adress :"); 
		addressLabel.setFont(new Font("Arial", Font.PLAIN, 17)); 
		addressLabel.setBounds(23, 126, 71, 27);
		add(addressLabel); 
		
		JLabel addressDetailLabel = new JLabel(business.getUserById(id).getAdress()); 
		addressDetailLabel.setBounds(124, 130, 222, 20); 
		add(addressDetailLabel); 
		
		JLabel appointmentsLabel = new JLabel("Randevular : "); 
		appointmentsLabel.setFont(new Font("Arial", Font.PLAIN, 17)); 
		appointmentsLabel.setBounds(23, 169, 102, 27); 
		add(appointmentsLabel); 
		
		listModel = new DefaultListModel<>();
        listView = new JList<>(listModel);
        listView.setCellRenderer(new CustomListCellRenderer(business));
        
		JScrollPane scrollPane = new JScrollPane(listView); 
		scrollPane.setBounds(23, 201, 410, 262); 
		add(scrollPane);
		
		JButton exit = new JButton("Exit"); 
		exit.setBounds(1, 1, 70, 25); 
		add(exit); 
		exit.addActionListener(new ActionListener() {                
            public void actionPerformed(ActionEvent e) {
            	dispose();
                new ChoosePage(business, id, itIsDoctor).setVisible(true);
            }
        });
		
		setTitle("My Account");
        setSize(455, 505);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
	}
	private void loadData() {
        List<ObjectOfRezerveTable> data = business.getUserRezervesByUserId(id);

        // Verileri liste modeline ekleyin
        for (ObjectOfRezerveTable item : data) {
            listModel.addElement(item);
        }
    }
	public static String capitalizeFirstLetter(String str) { 
		if (str == null || str.isEmpty()) { 
			return str; 
			} 
		return str.substring(0, 1).toUpperCase() + str.substring(1); 
		}
	}

