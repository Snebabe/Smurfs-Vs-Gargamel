package com.group9.view;

import com.group9.controller.Controller;
import com.group9.controller.GameController;
import com.group9.controller.Observer;
import com.group9.model.Entity;
import com.group9.model.Model;
import com.group9.model.Position;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.Map;

public class GamePanel extends JPanel implements Observer/*implements Runnable*/ {

    Thread renderThread;

    private JPanel[][] cells;

    private Model model;

    public GamePanel(Model model, GameController controller, int WIDTH, int HEIGHT) {
        this.model = model;
        this.setPreferredSize(new Dimension(800, 600));
        this.setDoubleBuffered(true);

        int rows = 5, cols = 9;
        JPanel gridPanel = new JPanel(new GridLayout(rows, cols));
        gridPanel.setBorder(BorderFactory.createTitledBorder("Game Grid"));

        cells = new JPanel[rows][cols];

        for (int row = 0; row < rows; row++) {
            for (int col = 0; col < cols; col++) {
                JPanel cell = new JPanel();
                cell.setPreferredSize(new Dimension(50, 50));
                cell.setBorder(BorderFactory.createLineBorder(Color.BLACK));
                // add mouse listener
                // should be MVC OK
                // as long interaction is delegated to controller

                int currentRow = row;
                int currentCol = col;
                cell.addMouseListener(new MouseAdapter() {
                    @Override
                    public void mouseClicked(MouseEvent e) {
                        controller.handleCellClick(currentRow, currentCol);
                    }
                });

                cells[row][col] = cell;
                gridPanel.add(cell);
            }
        }
        this.add(gridPanel);
    }

    private void addEntityToCell(int row, int col, String imagePath) {
        JLabel entityLabel = new JLabel("Shroom");
        cells[row][col].add(entityLabel); // Add entity to the specific cell
        cells[row][col].revalidate();    // Re-layout the cell
        cells[row][col].repaint();
    }

    /*public void startRendering() {
        renderThread = new Thread(this);
        renderThread.start();
    }

    @Override
    public void run() {
        while (renderThread != null) {
            System.out.println("Running!");

            // UPDATE (in our case fetch player position
            update();

            // DRAW updated information
            repaint();

        }
    }*/

    @Override
    public void update() {
        for (int row = 0; row < cells.length; row++) {
            for (int col = 0; col < cells[row].length; col++) {
                cells[row][col].removeAll(); // Remove all components from the cell
            }
        }

        for (Map.Entry<String, Position> entry: model.getAllDefendersPosition().entrySet()) {
            String s = entry.getKey();
            Position p = entry.getValue();
            addEntityToCell(p.getX(), p.getY(), s);
        }
        repaint();
    }

    public void paintComponent(Graphics g) {
        super.paintComponent(g);

        Graphics2D g2d = (Graphics2D) g;

        // Render attackers based on their positions
        for (Map.Entry<String, Position> entry : model.getAllAttackersPosition().entrySet()) {
            String s = entry.getKey();
            Position p = entry.getValue();

            // Convert model coordinates (x, y) to pixel-based coordinates
            int pixelX = p.getX();
            int pixelY = p.getY();
            //System.out.println("Attacker at: " + pixelX + ", " + pixelY);

            // Render attacker: Example using red color and a circle
            g2d.setColor(Color.RED);
            g2d.fillOval(pixelX - 5, pixelY - 5, 10, 10); // Small circle for attackers
            g2d.drawString(s, pixelX, pixelY); // Render entity name at (x, y)
        }
    }
}
