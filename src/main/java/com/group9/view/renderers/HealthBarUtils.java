package com.group9.view.renderers;

import java.awt.*;

public class HealthBarUtils {

    // Instance method for drawing a health bar
    public void drawHealthBar(Graphics2D g2d, int health, int maxHealth, int barX, int barY, int barWidth, int barHeight) {
        double healthPercent = (double) health / maxHealth;
        int filledWidth = (int) (barWidth * healthPercent);

        // Draw health bar background (gray)
        g2d.setColor(Color.GRAY);
        g2d.fillRect(barX, barY, barWidth, barHeight);

        // Draw health bar foreground (green)
        g2d.setColor(Color.GREEN);
        g2d.fillRect(barX, barY, filledWidth, barHeight);
    }
}
