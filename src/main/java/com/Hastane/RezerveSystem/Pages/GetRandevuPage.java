package com.Hastane.RezerveSystem.Pages;

import java.awt.Font;

import java.sql.Time;
import java.util.List;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.SwingConstants;

import com.Hastane.RezerveSystem.BusinessLayer.BusinessLayer;
import com.Hastane.RezerveSystem.Entity.ObjectOfRezerveTable;

public class GetRandevuPage extends JFrame{
	
	private BusinessLayer business;
	private int idUser;
	private boolean itIsDoctor;
	
	String[] reserveTypes = {"Online Randevu", "Yüz Yüze Randevu"};
	String[] days = {"Monday", "Tuesday", "Wednesday", "Thursday", "Friday", "Saturday", "Sunday"};
	String[] hour = {"09:00", "10:00", "11:00", "12:00", "13:00", "14:00", "15:00", "16:00", "17:00"};
	
	public GetRandevuPage(BusinessLayer business, int idUser, boolean itIsDoctor) { 
		this.business = business;
		this.itIsDoctor = itIsDoctor;
		this.idUser = idUser;
		initialize(); 
		}
	
	public void initialize() {
		setLayout(null); // Absolute layout kullanımı 
		// Etiketler 
		JLabel titleLabel = new JLabel("Randevu Alma"); 
		titleLabel.setFont(new Font("System Bold", Font.BOLD, 19)); 
		titleLabel.setHorizontalAlignment(SwingConstants.CENTER); 
		titleLabel.setBounds(111, 23, 158, 28); 
		add(titleLabel); 
		
		JLabel nameLabel = new JLabel("Isim :"); 
		nameLabel.setFont(new Font("Arial", Font.PLAIN, 17)); 
		nameLabel.setBounds(44, 88, 40, 20); 
		add(nameLabel); 
		
		JLabel surnameLabel = new JLabel("Surname :"); 
		surnameLabel.setFont(new Font("Arial", Font.PLAIN, 17)); 
		surnameLabel.setBounds(44, 127, 90, 26); 
		add(surnameLabel); 
		
		JLabel reserveTypeLabel = new JLabel("Randevu turu :"); 
		reserveTypeLabel.setFont(new Font("Arial", Font.PLAIN, 17)); 
		reserveTypeLabel.setBounds(44, 166, 114, 26); 
		add(reserveTypeLabel); 
		
		JLabel doctorLabel = new JLabel("Doctor :"); 
		doctorLabel.setFont(new Font("Arial", Font.PLAIN, 17)); 
		doctorLabel.setBounds(44, 201, 71, 26); 
		add(doctorLabel); 
		
		JLabel dayLabel = new JLabel("Day :"); 
		dayLabel.setFont(new Font("Arial", Font.PLAIN, 17)); 
		dayLabel.setBounds(44, 245, 40, 20); 
		add(dayLabel); 
		
		JLabel hourLabel = new JLabel("Hour :"); 
		hourLabel.setFont(new Font("Arial", Font.PLAIN, 17)); 
		hourLabel.setBounds(44, 291, 53, 26); 
		add(hourLabel); 
		
		// Metin Alanları 
		JTextField nameField = new JTextField(); 
		nameField.setBounds(159, 89, 169, 20); 
		add(nameField); 
		
		JTextField surnameField = new JTextField(); 
		surnameField.setBounds(159, 129, 169, 20);
		add(surnameField); 
		
		// ComboBox'lar 
		JComboBox<String> reserveTypeCombo = new JComboBox<>(reserveTypes); 
		reserveTypeCombo.setBounds(158, 169, 169, 30); 
		add(reserveTypeCombo); 
		
		JComboBox<String> doctorCombo = new JComboBox<>(getDoctorsArray()); 
		doctorCombo.setBounds(158, 208, 169, 30); 
		add(doctorCombo); 
		
		JComboBox<String> dayCombo = new JComboBox<>(days); 
		dayCombo.setBounds(158, 248, 169, 30); 
		add(dayCombo); 
		
		JComboBox<String> hourCombo = new JComboBox<>(hour); 
		hourCombo.setBounds(158, 288, 169, 30); 
		add(hourCombo); 
		
		// Gönder butonu 
		JButton submitButton = new JButton("Gonder"); 
		submitButton.setBounds(159, 368, 90, 25); 
		add(submitButton);
		submitButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
            	String name = nameField.getText(); 
            	String surname = surnameField.getText(); 
            	String reserveType = (String) reserveTypeCombo.getSelectedItem(); 
            	String doctor = (String) doctorCombo.getSelectedItem(); 
            	String day = (String) dayCombo.getSelectedItem(); 
            	String hourStr = (String) hourCombo.getSelectedItem();
            	
            	Time hour = Time.valueOf(hourStr + ":00");
            	
            	ObjectOfRezerveTable rezerve = new ObjectOfRezerveTable(); 
            	rezerve.setName(name); 
            	rezerve.setSurname(surname); 
            	rezerve.setDay(day); 
            	rezerve.setHour(hour); 
            	rezerve.setDoctor(doctor); 
            	rezerve.setRezerveType(reserveType); 
            	rezerve.setIdUser(idUser);
            	
            	business.addRandevu(rezerve);
            	JOptionPane.showMessageDialog(null, "Randevu istegi gonderildi");
            }
        });
		
		JButton exit = new JButton("Exit"); 
		exit.setBounds(1, 1, 70, 25); 
		add(exit); 
		exit.addActionListener(new ActionListener() {                
            public void actionPerformed(ActionEvent e) {
            	dispose();
                new ChoosePage(business, idUser, itIsDoctor).setVisible(true);
            }
        });
		
		setTitle("Get Randevu");
        setSize(500, 500);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
	}
	private String[] getDoctorsArray() { 
		List<String> doctorsList = business.getAllDoctors(); 
		return doctorsList.toArray(new String[0]); 
		}
	
}
