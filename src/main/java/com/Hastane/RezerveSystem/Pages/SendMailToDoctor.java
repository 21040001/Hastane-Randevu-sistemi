package com.Hastane.RezerveSystem.Pages;
import javax.swing.*;
import javax.xml.crypto.Data;

import com.Hastane.RezerveSystem.BusinessLayer.BusinessLayer;
import com.Hastane.RezerveSystem.Entity.ObjectOfMessagesTable;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Date;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

public class SendMailToDoctor extends JFrame {
	
	private BusinessLayer business;
	private int idUser;
	private boolean itIsDoctor;
	
	public SendMailToDoctor(BusinessLayer business, int idUser, boolean itIsDoctor) {
		this.idUser=idUser;
		this.itIsDoctor=itIsDoctor;
		this.business=business;
		initialize();
	}
	
    public void initialize() {
    	setLayout(null);

        JLabel titleLabel = new JLabel("Send Mail to Doctor", SwingConstants.CENTER);
        titleLabel.setFont(new Font("Arial", Font.BOLD, 19));
        titleLabel.setBounds(199, 22, 202, 28);
        add(titleLabel);

        JLabel toLabel = new JLabel("Whom ?");
        toLabel.setFont(new Font("Arial", Font.BOLD, 13));
        toLabel.setBounds(40, 70, 58, 20);
        add(toLabel);

        JComboBox<String> doctorCombo = new JComboBox<>(getDoctorsArray());
        doctorCombo.setBounds(108, 65, 280, 30);
        add(doctorCombo);

        JLabel subjectLabel = new JLabel("Subject ");
        subjectLabel.setFont(new Font("Arial", Font.BOLD, 13));
        subjectLabel.setBounds(40, 119, 58, 20);
        add(subjectLabel);

        JTextField subjectField = new JTextField();
        subjectField.setBounds(108, 114, 280, 30);
        add(subjectField);

        JTextArea messageArea = new JTextArea();
        messageArea.setBounds(40, 174, 496, 200);
        add(messageArea);
        messageArea.setBorder(BorderFactory.createEmptyBorder(12, 12, 12, 12));
        
        JButton sendButton = new JButton("Send"); 
        sendButton.setBounds(260, 400, 80, 30); 
        add(sendButton);
        
        sendButton.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				LocalDateTime currentDateTime = LocalDateTime.now();
				DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
				int sender = idUser ;
				int receiver = business.getDoctorIdByName((String) doctorCombo.getSelectedItem());
				String body = messageArea.getText();
				String time = currentDateTime.format(formatter);
				
				Date sqlDate = Date.valueOf(time);
				String fullName = business.getUserById(idUser).getName() +" "+ business.getUserById(idUser).getSurname();
				
				ObjectOfMessagesTable object = new ObjectOfMessagesTable();
				object.setSender(sender);
				object.setReceiver(receiver);
				object.setFull_name(fullName);
				object.setBody(body);
				object.setTime(sqlDate);
				
				business.sendToMail(object);
				
				JOptionPane.showMessageDialog(null, "Randevu mesaji gonderildi");
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
        
        setTitle("Send Message to Doctor");
        setSize(600, 500);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
    }
    private String[] getDoctorsArray() { 
		List<String> doctorsList = business.getAllDoctors(); 
		return doctorsList.toArray(new String[0]); 
		}
}
