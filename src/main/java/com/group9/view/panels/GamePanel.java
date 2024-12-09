package com.group9.view.panels;

import com.group9.controller.InputObserver;
import com.group9.model.Model;
import com.group9.model.Observer;
import com.group9.view.renderers.*;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.List;

public class GamePanel extends JPanel implements Observer {

    private final List<EntityRenderer> entityRenderers;
    private int columnCount; // Number of columns
    private int rowCount;   // Number of rows
    private Model model;

    public GamePanel(Model model, List<InputObserver> inputObservers) {
        this.model = model;
        this.rowCount = model.getLaneAmount();
        this.columnCount = model.getLaneSize();
        this.setBackground(Color.WHITE);

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
            renderer.draw(g2d, model, cellWidth, cellHeight, width);
        }

        g2d.dispose();
    }

    private void initializeGridCells(int cellWidth, int cellHeight) {
        for (int row = 0; row < rowCount; row++) {
            for (int col = 0; col < columnCount; col++) {
                Rectangle cell = new Rectangle(
                        col * cellWidth,
                        row * cellHeight,
                        cellWidth,
                        cellHeight);
                cells.add(cell);
            }
        }
    }

    private void drawGrid(Graphics2D g2d) {
        g2d.setColor(Color.GRAY);
        for (Rectangle cell : cells) {
            g2d.draw(cell);
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
