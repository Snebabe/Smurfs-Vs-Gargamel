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

/**
 * The GamePanel class represents the main game area where the game entities are rendered.
 * It handles the rendering of the game grid and entities.
 */
public class GamePanel extends JPanel implements ClockObserver {

    private final List<EntityRenderer> entityRenderers;
    private final int columnCount;
    private final int rowCount;
    private final Model model;
    private final AnimationHandler animationHandler;

    /**
     * Constructs a GamePanel with the specified model, animation handler, and input observers.
     *
     * @param model the game model
     * @param animationHandler the handler for animations
     * @param inputObservers the list of input observers
     */

    public GamePanel(Model model, AnimationHandler animationHandler, List<InputObserver> inputObservers) {
        this.model = model;
        rowCount = model.getLaneAmount();
        columnCount = model.getLaneSize();
        this.animationHandler = animationHandler;
        setBackground(Color.getHSBColor(0.33f, 1.0f, 0.2f)); // Set background color to dark green

        entityRenderers = new ArrayList<>();

        entityRenderers.add(new AttackerRenderer());
        entityRenderers.add(new DefenderRenderer());
        entityRenderers.add(new ProjectileRenderer());

        addMouseListener(createMouseHandler(inputObservers));
    }

    /**
     * Creates a MouseAdapter to see mouse position in the grid
     *
     * @param inputObservers the list of input observers
     * @return the MouseAdapter for handling grid cell clicks
     */
    private MouseAdapter createMouseHandler(List<InputObserver> inputObservers) {
        return new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                // Calculate clicked cell
                int cellWidth = getWidth() / columnCount;
                int cellHeight = getHeight() / rowCount;

                int column = e.getPoint().x / cellWidth;
                int row = e.getPoint().y / cellHeight;


                // Check if the clicked cell is within bounds
                if (row >= 0 && row < rowCount && column >= 0 && column < columnCount) {
                    for (InputObserver observer : inputObservers) {
                        observer.onGridCellClicked(row, column);
                    }

                    repaint();
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

        drawGrid(g2d, cellWidth, cellHeight);

        for (EntityRenderer renderer : entityRenderers) {
            renderer.draw(g2d, model, animationHandler, cellWidth, cellHeight, width);
        }

        g2d.dispose();
    }

    private void drawGrid(Graphics2D g2d, int cellWidth, int cellHeight) {
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
