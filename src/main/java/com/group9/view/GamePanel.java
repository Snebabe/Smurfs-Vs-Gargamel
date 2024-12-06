package com.group9.view;

import com.group9.controller.GameController;
import com.group9.controller.Observer;
import com.group9.model.Projectile;
import com.group9.model.entities.attackers.AttackEntity;
import com.group9.model.entities.defenders.DefenceEntity;
import com.group9.model.Model;
import com.group9.model.Position;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class GamePanel extends JPanel implements Observer {

    private int columnCount = 8; // Number of columns
    private int rowCount = 5;   // Number of rows
    private List<Rectangle> cells;
    private Model model;
    private GameController controller;

    public GamePanel(Model model, GameController controller, int WIDTH, int HEIGHT) {
        this.model = model;
        this.rowCount = model.getLaneAmount();
        this.columnCount = model.getLaneSize();
        this.controller = controller;
        this.setPreferredSize(new Dimension(WIDTH, HEIGHT));
        this.setBackground(Color.WHITE);

        cells = new ArrayList<>(columnCount * rowCount);

        MouseAdapter mouseHandler = new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                Point point = e.getPoint();
                int width = getWidth();
                int height = getHeight();
                int cellWidth = width / columnCount;
                int cellHeight = height / rowCount;

                int xOffset = (width - (columnCount * cellWidth)) / 2;
                int yOffset = (height - (rowCount * cellHeight)) / 2;

                if (e.getX() >= xOffset && e.getY() >= yOffset) {
                    int column = (e.getX() - xOffset) / cellWidth;
                    int row = (e.getY() - yOffset) / cellHeight;

                    if (column >= 0 && row >= 0 && column < columnCount && row < rowCount) {

                        // Notify the controller to handle placement
                        controller.handleCellClick(row, column);

                        repaint(); // Update the grid with the selected cell
                    }
                }
            }
        };

        addMouseListener(mouseHandler);
    }

    @Override
    public void invalidate() {
        cells.clear();
        super.invalidate();
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2d = (Graphics2D) g.create();

        int width = getWidth();
        int height = getHeight();
        int cellWidth = width / columnCount;
        int cellHeight = height / rowCount;

        int xOffset = (width - (columnCount * cellWidth)) / 2;
        int yOffset = (height - (rowCount * cellHeight)) / 2;

        if (cells.isEmpty()) {
            for (int row = 0; row < rowCount; row++) {
                for (int col = 0; col < columnCount; col++) {
                    Rectangle cell = new Rectangle(
                            xOffset + (col * cellWidth),
                            yOffset + (row * cellHeight),
                            cellWidth,
                            cellHeight);
                    cells.add(cell);
                }
            }
        }

        // Draw defenders from the model
        for (Map.Entry<DefenceEntity, Position> entry : model.getAllDefendersPosition().entrySet()) {
            DefenceEntity defender = entry.getKey();
            Position position = entry.getValue();

            int x = position.getCol() * cellWidth + xOffset;
            int y = position.getRow() * cellHeight + yOffset;


            g2d.setColor(Color.BLUE);
            g2d.fillRect(x + cellWidth / 4, y + cellHeight / 3, cellWidth / 2, cellHeight / 2);

            // Render health bar above the attacker
            int barWidth = cellWidth / 2;
            int barHeight = cellHeight / 10;
            int barX = x + cellWidth / 4;
            int barY = y + cellHeight / 7; // Position above the attacker

            // Calculate health percentage
            double healthPercent = (double) defender.getHealth() / defender.getMaxHealth();
            int filledWidth = (int) (barWidth * healthPercent);

            // Draw health bar background
            g2d.setColor(Color.GRAY);
            g2d.fillRect(barX, barY, barWidth, barHeight);

            // Draw health bar foreground
            g2d.setColor(Color.GREEN);
            g2d.fillRect(barX, barY, filledWidth, barHeight);
        }


        // Draw grid lines
        g2d.setColor(Color.GRAY);
        for (Rectangle cell : cells) {
            g2d.draw(cell);
        }

        // Render attackers based on their positions and progress
        for (Map.Entry<AttackEntity, Position> entry : model.getAllAttackersPosition().entrySet()) {
            AttackEntity attacker = entry.getKey();
            Position position = entry.getValue();

            double progress = attacker.getLaneProgress(); // progress is between 0 and 1

            // Calculate pixel-based coordinates
            int pixelX = (int) (width * (1 - progress));
            int pixelY = position.getRow() * cellHeight + yOffset;

            // Render attacker: Example using red color and a circle
            /*System.out.print(pixelX);
            System.out.print(" , ");
            System.out.print(position.getRow());
            System.out.println("\n");*/

            // Render attacker as a red circle
            g2d.setColor(Color.RED);
            g2d.fillOval(pixelX + cellWidth / 3, pixelY + cellHeight / 3, cellWidth / 2, cellHeight / 2); // Small circle for attackers

            // Render health bar above the attacker
            int barWidth = cellWidth / 2;
            int barHeight = cellHeight / 10;
            int barX = pixelX + cellWidth / 3;
            int barY = pixelY + cellHeight / 6; // Position above the attacker

            // Calculate health percentage
            double healthPercent = (double) attacker.getHealth() / attacker.getMaxHealth();
            int filledWidth = (int) (barWidth * healthPercent);

            // Draw health bar background
            g2d.setColor(Color.GRAY);
            g2d.fillRect(barX, barY, barWidth, barHeight);

            // Draw health bar foreground
            g2d.setColor(Color.GREEN);
            g2d.fillRect(barX, barY, filledWidth, barHeight);


        }

        for (Map.Entry<Projectile, Position> entry1 : model.getAllProjectilesPosition().entrySet()) {
            Projectile projectile = entry1.getKey();
            Position position1 = entry1.getValue();

            double progress = projectile.getLaneProgress();

            int projectileX = (int) (width * progress);
            int projectileY = position1.getRow() * cellHeight + yOffset;

            g2d.setColor(Color.BLACK);
            g2d.fillOval(projectileX + cellWidth / 3, projectileY + cellHeight / 3, 5, 5); // Draw a small circle
            g2d.dispose();
        }
    }
    @Override
    public void update() {
        repaint();
    }
}
