package com.group9.view.panels;

import com.group9.controller.InputObserver;
import com.group9.model.Model;
import com.group9.model.Observer;
import com.group9.model.services.PositionConverter;
import com.group9.view.AnimationHandler;
import com.group9.view.renderers.*;
import com.group9.view.services.ImageLoader;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.List;

public class GamePanel extends JPanel implements Observer {

    private final List<EntityRenderer> entityRenderers; // List of renderers for different entities (attackers, defenders, etc.)
    private final int columnCount; // Number of columns
    private final int rowCount;   // Number of rows
    private final Model model;
    private final AnimationHandler animationHandler;

    public GamePanel(Model model, AnimationHandler animationHandler, List<InputObserver> inputObservers) {
        this.model = model;
        rowCount = model.getLaneAmount();
        columnCount = model.getLaneSize();
        this.animationHandler = animationHandler;
        setBackground(Color.getHSBColor(0.33f, 1.0f, 0.2f)); // Set background color for the panel

        // Initialize the list of entity renderers with direct access to the model
        entityRenderers = new ArrayList<>();

        // Initialize health bar utilities for renderers
        HealthBarUtils healthBarUtils = new HealthBarUtils();

        // Add renderers to the list
        entityRenderers.add(new AttackerRenderer(healthBarUtils));
        entityRenderers.add(new DefenderRenderer(healthBarUtils));
        entityRenderers.add(new ProjectileRenderer());

        // Add mouse listener for handling grid cell clicks
        addMouseListener(createMouseHandler(inputObservers));
    }

    // Create Mouse Input Handler for grid cell clicks
    private MouseAdapter createMouseHandler(List<InputObserver> inputObservers) {
        return new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                // Map mouse click coordinates to the grid cell
                Point cell = PositionConverter.mapToGrid(e.getPoint(), getWidth(), getHeight(), rowCount, columnCount);

                int row = cell.x;
                int column = cell.y;

                // Check if the clicked cell is within bounds
                if (row >= 0 && row < rowCount && column >= 0 && column < columnCount) {
                    // Notify observers
                    for (InputObserver observer : inputObservers) {
                        observer.onGridCellClicked(row, column);
                    }

                    repaint(); // Update the grid with the selected cell
                }
            }
        };
    }

    // Paint the panel by drawing the grid and entities
    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2d = (Graphics2D) g.create();

        // Set up shared dimensions
        int width = getWidth();
        int height = getHeight();
        int cellWidth = width / columnCount;
        int cellHeight = height / rowCount;

        // Initialize grid cells if empty
        drawGrid(g2d, cellWidth, cellHeight);

        // Call draw() for each renderer
        for (EntityRenderer renderer : entityRenderers) {
            renderer.draw(g2d, model, animationHandler, cellWidth, cellHeight, width);
        }

        g2d.dispose();// Dispose of the Graphics2D object to free up resources
    }


    // Draw the grid with alternating images for each cell
    private void drawGrid(Graphics2D g2d, int cellWidth, int cellHeight) {
        // Load the alternating cell images
        Image gridImage = ImageLoader.loadImage("/images/backgrounds/gridcell2.png");
        Image gridImage2 = ImageLoader.loadImage("/images/backgrounds/gridcell.png");

        // Loop through all the rows and columns to draw each grid cell
        for (int row = 0; row < rowCount; row++) {
            for (int col = 0; col < columnCount; col++) {
                int x = col * cellWidth;
                int y = row * cellHeight;

                // Alternate between gridImage and gridImage2
                Image currentImage = ((row + col) % 2 == 0) ? gridImage : gridImage2;

                g2d.drawImage(currentImage,x, y, cellWidth, cellHeight, null);
            }
        }
    }

    // Observer method to handle updates and repaint the panel
    @Override
    public void update() {
        repaint();
    } // Repaint the panel to reflect updates

}
