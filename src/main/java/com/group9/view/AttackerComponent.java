package com.group9.view;

import javax.swing.*;
import java.awt.*;

public class AttackerComponent extends JPanel {
    private int health;
    private int maxHealth;

    public AttackerComponent(int health, int maxHealth) {
        this.health = health;
        this.maxHealth = maxHealth;
        this.setPreferredSize(new Dimension(50, 60)); // Adjust size as needed
    }

    public void setHealth(int health) {
        this.health = health;
        repaint();
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2d = (Graphics2D) g;

        // Draw health bar
        g2d.setColor(Color.RED);
        int healthBarWidth = (int) ((double) health / maxHealth * getWidth());
        g2d.fillRect(0, 0, healthBarWidth, 10);

        // Draw health bar border
        g2d.setColor(Color.BLACK);
        g2d.drawRect(0, 0, getWidth() - 1, 10);

        // Draw attacker square
        g2d.setColor(Color.BLUE);
        g2d.fillRect(0, 15, getWidth(), getWidth());
    }
}