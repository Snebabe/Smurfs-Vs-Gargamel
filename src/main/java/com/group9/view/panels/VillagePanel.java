package com.group9.view.panels;

import com.group9.view.services.ImageLoader;
import javax.swing.*;
import java.awt.*;

public class VillagePanel extends JPanel {

    //The two different village images
    Image villageImage1;
    Image villageImage2;
    private final int rowCount;

    public VillagePanel(int rowCount) {
        // Load the two village images
        villageImage1 = ImageLoader.loadImage("/images/backgrounds/village1.png");
        villageImage2 = ImageLoader.loadImage("/images/backgrounds/village2.png");
        this.rowCount = rowCount;
        setBackground(Color.getHSBColor(0.33f, 1.0f, 0.2f)); // Set the background color of the panel
    }

    // Override paintComponent to render the village background images
    @Override
    protected void paintComponent(Graphics g) {
        int width = getWidth();
        int height = getHeight();
        int cellHeight = height / rowCount;  // Calculate the height of each row
        super.paintComponent(g); // Call superclass method to handle basic painting
        int y = 0; // Starting y-coordinate for the first image

        // Loop through the rows and alternate between the two images
        for(int current =0; current < rowCount; current++){
            // Alternate between villageImage1 and villageImage2 based on the current row
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
