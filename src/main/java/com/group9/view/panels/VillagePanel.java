package com.group9.view.panels;

import com.group9.view.services.ImageLoader;
import javax.swing.*;
import java.awt.*;

/**
 * The VillagePanel class represents a panel that displays the village to protect.
 */
public class VillagePanel extends JPanel {
    Image villageImage1;
    Image villageImage2;
    private final int rowCount;

    /**
     * Constructs a VillagePanel based on the number of rows.
     *
     * @param rowCount the number of rows to display
     */
    public VillagePanel(int rowCount) {
        // Load the two village images
        villageImage1 = ImageLoader.loadImage("/images/backgrounds/village1.png");
        villageImage2 = ImageLoader.loadImage("/images/backgrounds/village2.png");
        this.rowCount = rowCount;
        setBackground(Color.getHSBColor(0.33f, 1.0f, 0.2f)); // Set the background color as dark green
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);

        int width = getWidth();
        int height = getHeight();
        int cellHeight = height / rowCount;
        int y = 0; // Starting y-coordinate for the first image

        // Loop through the rows and alternate between the two images
        for(int current =0; current < rowCount; current++){
            Image currentImage = (current % 2 == 0) ? villageImage1 : villageImage2;

            // Draw the current image for the row
            g.drawImage(currentImage, 0, y, width, cellHeight, this);

            // Move to the next row
            y += getHeight()/ rowCount;
        }
    }

    public void update() {
        repaint();
    }
}
