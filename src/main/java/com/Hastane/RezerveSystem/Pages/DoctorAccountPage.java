package com.Hastane.RezerveSystem.Pages;


import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.List;

import javax.swing.*;

import com.Hastane.RezerveSystem.BusinessLayer.BusinessLayer;
import com.Hastane.RezerveSystem.Entity.ObjectOfRezerveTable;
import com.Hastane.RezerveSystem.Pages.Elements.CustomListCellRenderer;
import com.Hastane.RezerveSystem.Pages.Elements.ListPanel;

public class DoctorAccountPage extends JFrame {
    private BusinessLayer business;
    private int id;
    private boolean itIsDoctor;
    private DefaultListModel<ObjectOfRezerveTable> listModel; 
    private JList<ObjectOfRezerveTable> listView;

    public DoctorAccountPage(BusinessLayer business, int id, boolean itIsDoctor) {
        this.business = business;
        this.itIsDoctor = itIsDoctor;
        this.id = id;
        initialize();
        loadData(); // Veri yükleme metodunu çağırın
    }

    public void initialize() {
        setLayout(null); // Absolute layout kullanımı

        // Etiketler ve metin alanları
        JLabel doctorLabel = new JLabel(business.getDoctorByID(id).getName() + " " + business.getDoctorByID(id).getSurname());
        doctorLabel.setFont(new Font("Arial", Font.BOLD, 19));
        doctorLabel.setBounds(22, 71, 336, 28);
        add(doctorLabel);

        JButton messagesButton = new JButton("Mesajlar");
        messagesButton.setBounds(302, 21, 90, 25);
        add(messagesButton);
        messagesButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
            	dispose();
                new MessagesWindow(id, business, itIsDoctor).setVisible(true);
            }
        });

        JButton appointmentsButton = new JButton("Randevular");
        appointmentsButton.setBounds(209, 21, 90, 25);
        add(appointmentsButton);
        appointmentsButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                // Randevular butonu için eylemler
            }
        });

        listModel = new DefaultListModel<>();
        listView = new JList<>(listModel);
        listView.setVisibleRowCount(5);
        listView.setCellRenderer(new CustomListCellRenderer(business));
        
        listView.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                int index = listView.locationToIndex(e.getPoint());
                if (index >= 0) {
                    ObjectOfRezerveTable item = listModel.get(index);
                    ListPanel panel = (ListPanel) listView.getCellRenderer()
                            .getListCellRendererComponent(listView, item, index, listView.isSelectedIndex(index), true);

                    for (Component component : panel.getComponents()) {
                        if (component instanceof JButton) {
                            Rectangle bounds = component.getBounds();
                            if (bounds.contains(e.getPoint())) {
                                ((JButton) component).doClick();
                            }
                        }
                    }
                }
            }
        });
        
        JScrollPane scrollPane = new JScrollPane(listView);
        scrollPane.setBounds(17, 225, 410, 148);
        add(scrollPane);

        JLabel addressLabel = new JLabel("Adres :");
        addressLabel.setFont(new Font("Arial", Font.PLAIN, 17));
        addressLabel.setBounds(22, 184, 71, 27);
        add(addressLabel);

        JLabel addressDetailLabel = new JLabel(business.getDoctorByID(id).getAdress());
        addressDetailLabel.setBounds(114, 188, 222, 20);
        add(addressDetailLabel);

        JLabel specialtyLabel = new JLabel(business.getDoctorByID(id).getProfesion());
        specialtyLabel.setFont(new Font("Calibri", Font.PLAIN, 17));
        specialtyLabel.setBounds(80, 100, 71, 20);
        add(specialtyLabel);

        JLabel locationLabel = new JLabel("Ankara");
        locationLabel.setFont(new Font("Calibri", Font.PLAIN, 17));
        locationLabel.setBounds(22, 100, 50, 20);
        add(locationLabel);

        JLabel experienceLabel = new JLabel("Deneyim :");
        experienceLabel.setFont(new Font("Arial", Font.PLAIN, 17));
        experienceLabel.setBounds(22, 132, 95, 26);
        add(experienceLabel);

        JLabel languageLabel = new JLabel("Dil :");
        languageLabel.setFont(new Font("Arial", Font.PLAIN, 17));
        languageLabel.setBounds(22, 158, 92, 26);
        add(languageLabel);

        JLabel experienceDetailLabel = new JLabel(business.getDoctorByID(id).getExperience());
        experienceDetailLabel.setBounds(117, 135, 49, 20);
        add(experienceDetailLabel);

        JLabel languageDetailLabel = new JLabel(business.getDoctorByID(id).getLanguages());
        languageDetailLabel.setBounds(117, 161, 118, 20);
        add(languageDetailLabel);
        
        JButton exit = new JButton("Exit"); 
		exit.setBounds(1, 1, 70, 25); 
		add(exit); 
		exit.addActionListener(new ActionListener() {                
            public void actionPerformed(ActionEvent e) {
            	dispose();
                new ChoosePage(business, id, itIsDoctor).setVisible(true);
            }
        });

        setTitle("Hesabım");
        setSize(480, 500);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
    }

    private void loadData() {
        String name = business.getDoctorByID(id).getName();
        List<ObjectOfRezerveTable> data = business.getDoctorsRezervesByDoctorName(name);

        // Verileri liste modeline ekleyin
        for (ObjectOfRezerveTable item : data) {
            listModel.addElement(item);
        }
    }
    
}
