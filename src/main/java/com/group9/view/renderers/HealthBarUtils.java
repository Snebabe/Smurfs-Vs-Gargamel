package com.group9.view.renderers;

import java.awt.*;

public class HealthBarUtils {

    // Instance method for drawing a health bar
    public void drawHealthBar(Graphics2D g2d, Color healthBarColor, float health, float maxHealth, int barX, int barY, int cellWidth, int cellHeight) {
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
