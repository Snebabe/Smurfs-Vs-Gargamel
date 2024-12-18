package com.group9.view.panels;

import com.group9.view.services.ImageLoader;

import javax.swing.*;
import java.awt.*;

public class VillagePanel extends JPanel {
    Image villageImage;

    public VillagePanel() {
        villageImage = ImageLoader.loadImage("/images/backgrounds/village.png");
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        if (villageImage != null) {
            g.drawImage(villageImage, 0, 0, getWidth(), getHeight(), this);
        }
    }

    public void update() {
        repaint();
    }
}
