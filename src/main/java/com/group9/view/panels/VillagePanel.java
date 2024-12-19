package com.group9.view.panels;

import com.group9.view.services.ImageLoader;

import javax.swing.*;
import java.awt.*;

public class VillagePanel extends JPanel {
    Image villageImage1;
    Image villageImage2;
    private int rowCount;

    public VillagePanel(int rowCount) {
        villageImage1 = ImageLoader.loadImage("/images/backgrounds/village1.png");
        villageImage2 = ImageLoader.loadImage("/images/backgrounds/village2.png");
        this.rowCount = rowCount;
        this.setBackground(Color.getHSBColor(0.33f, 1.0f, 0.2f));
    }

    @Override
    protected void paintComponent(Graphics g) {
        int width = getWidth();
        int height = getHeight();
        int cellHeight = height / rowCount;
        super.paintComponent(g);
        int y = 0;
        for(int current =0; current < rowCount; current++){
            Image currentImage = (current % 2 == 0) ? villageImage1 : villageImage2;

            g.drawImage(currentImage, 0, y, width, cellHeight, this);

            y += getHeight()/ rowCount;
        }
    }

    public void update() {
        repaint();
    }
}
