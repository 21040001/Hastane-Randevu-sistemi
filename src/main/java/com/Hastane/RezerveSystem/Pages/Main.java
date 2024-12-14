package com.Hastane.RezerveSystem.Pages;

import com.Hastane.RezerveSystem.BusinessLayer.BusinessLayer;

public class Main {

    private BusinessLayer business;

    public Main(BusinessLayer business) {
        this.business = business;
        createAndShowGUI();
    }

    public void createAndShowGUI() {
        javax.swing.SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                new LogIn(business).setVisible(true);
            }
        });
    }
}
