package com.group9.view.panels;

import com.group9.controller.InputObserver;
import com.group9.model.Model;
import com.group9.model.Observer;
import com.group9.view.AnimationHandler;
import com.group9.view.renderers.*;
import com.group9.view.services.ImageLoader;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.File;
import java.io.IOException;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.List;

public class GamePanel extends JPanel implements Observer {

    private final List<EntityRenderer> entityRenderers;
    private int columnCount; // Number of columns
    private int rowCount;   // Number of rows
    private Model model;
    private AnimationHandler animationHandler;

    public GamePanel(Model model, AnimationHandler animationHandler, List<InputObserver> inputObservers) {
        this.model = model;
        this.rowCount = model.getLaneAmount();
        this.columnCount = model.getLaneSize();
        this.animationHandler = animationHandler;
        this.setBackground(Color.getHSBColor(0.33f, 1.0f, 0.2f));

        // Initialize the list of entity renderers with direct access to the model
        entityRenderers = new ArrayList<>();

        HealthBarUtils healthBarUtils = new HealthBarUtils();

        entityRenderers.add(new DefenderRenderer(healthBarUtils));
        entityRenderers.add(new AttackerRenderer(healthBarUtils));
        entityRenderers.add(new ProjectileRenderer());

        addMouseListener(createMouseHandler(inputObservers));
    }

    // Create Mouse Input Handler
    private MouseAdapter createMouseHandler(List<InputObserver> inputObservers) {
        return new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                Point point = e.getPoint();
                int cellWidth = getWidth() / columnCount;
                int cellHeight = getHeight() / rowCount;

                // Calculate clicked cell
                int column = point.x / cellWidth;
                int row = point.y / cellHeight;

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

        g2d.dispose();
    }



    private void drawGrid(Graphics2D g2d, int cellWidth, int cellHeight) {
        Image gridImage = ImageLoader.loadImage("/images/backgrounds/gridcell.png");
        Image gridImage2 = ImageLoader.loadImage("/images/backgrounds/gridcell2.png");

        for (int row = 0; row < rowCount; row++) {
            for (int col = 0; col < columnCount; col++) {
                int x = col * cellWidth;
                int y = row * cellHeight;

                // Alternate between gridImage and gridImage2
                Image currentImage = ((row + col) % 2 == 0) ? gridImage : gridImage2;

                //g2d.drawImage(currentImage, (int) (x * 0.99), (int) (y * 0.99), (int) (cellWidth * 1.4), (int) (cellHeight * 1.4), null);
                g2d.drawImage(currentImage,x, y, cellWidth, cellHeight, null);

                //g2d.drawRect(x, y, cellWidth, cellHeight);
            }
        }
    }

    @Override
    public void update() {
        repaint();
    }

    /*@Override
    public void invalidate() {
        cells.clear();
        super.invalidate();
    }*/
}
