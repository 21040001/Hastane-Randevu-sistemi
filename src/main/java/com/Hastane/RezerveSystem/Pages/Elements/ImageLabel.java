package com.Hastane.RezerveSystem.Pages.Elements;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.geom.Ellipse2D;
import java.io.File;
import java.io.IOException;

public class ImageLabel extends JLabel {

    private Image image;

    public ImageLabel(String imagePath) {
        try {
            image = ImageIO.read(new File(imagePath));
            Image scaledImage = image.getScaledInstance(53, 53, Image.SCALE_SMOOTH);
            setIcon(new ImageIcon(scaledImage));
            setBounds(14, 10, 53, 53);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    protected void paintComponent(Graphics g) {
        if (image != null) {
            Graphics2D g2d = (Graphics2D) g.create();
            int diameter = Math.min(getWidth(), getHeight());
            Ellipse2D.Double circle = new Ellipse2D.Double(0, 0, diameter, diameter);
            g2d.setClip(circle);
            g2d.drawImage(image, 0, 0, diameter, diameter, this);
            g2d.dispose();
        }
    }
}
