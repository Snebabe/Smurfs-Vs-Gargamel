package com.group9.view.services;

import java.awt.*;

/**
 * The HealthBarUtils class provides utility methods for drawing health bars on game entities.
 */
public class HealthBarUtils {

    /**
     * Draws a health bar on the provided Graphics2D context.
     *
     * @param g2d the Graphics2D context to draw on
     * @param healthBarColor the color of the health bar
     * @param health the current health of the entity
     * @param maxHealth the maximum health of the entity
     * @param barX the x-coordinate of the start of the grid cell
     * @param barY the y-coordinate of the start of the grid cell
     * @param cellWidth the width of the cell containing the health bar
     * @param cellHeight the height of the cell containing the health bar
     */
    public static void drawHealthBar(Graphics2D g2d, Color healthBarColor, float health, float maxHealth, int barX, int barY, int cellWidth, int cellHeight) {
        barX += (cellWidth/4);
        cellWidth = cellWidth * 2 / 4;
        cellHeight = cellHeight / 10;
        double healthPercent = (double) health / maxHealth;
        int filledWidth = (int) (cellWidth * healthPercent);

        // Draw health bar background (gray)
        g2d.setColor(Color.GRAY);
        g2d.fillRect(barX, barY, cellWidth, cellHeight);

        // Draw health bar foreground (green)
        g2d.setColor(healthBarColor);
        g2d.fillRect(barX, barY, filledWidth, cellHeight);

        // Draw health bar border (black)
        g2d.setColor(Color.BLACK);
        g2d.drawRect(barX, barY, cellWidth, cellHeight);
    }
}
