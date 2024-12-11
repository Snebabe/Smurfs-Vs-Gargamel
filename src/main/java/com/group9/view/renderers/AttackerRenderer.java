package com.group9.view.renderers;

import com.group9.model.entities.attackers.AttackEntity;
import com.group9.model.Position;
import com.group9.model.Model;
import com.group9.view.AnimationHandler;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;

import java.awt.*;
import java.util.Map;

public class AttackerRenderer implements EntityRenderer {

    private final HealthBarUtils healthBarUtils;

    // Pass the HealthBarUtils instance to the renderer
    public AttackerRenderer(HealthBarUtils healthBarUtils) {
        this.healthBarUtils = healthBarUtils;
    }

    @Override
    public void draw(Graphics2D g2d, Model model, AnimationHandler animationHandler, int cellWidth, int cellHeight, int panelWidth) {
        // Loop through all attackers and their positions
        for (Map.Entry<AttackEntity, Position> entry : model.getAllAttackersPosition().entrySet()) {
            AttackEntity attacker = entry.getKey();
            Position position = entry.getValue();

            // Calculate the attacker's progress along the lane and position
            double progress = attacker.getLaneProgress();
            int x = (int) (cellWidth * model.getLaneSize() * (1 - progress)); // Horizontal position based on progress
            int y = position.getRow() * cellHeight; // Vertical position based on row

            int xOffset = (int) (cellWidth * 0.1);
            int yOffset = (int) (cellHeight * 0.1);

            // Draw defender image, filling the grid cell
            g2d.drawImage(animationHandler.getFrame(attacker.getType(), attacker.getCurrentState()),
                    x + xOffset, y + yOffset, cellWidth - xOffset, cellHeight - yOffset, null);

            // Use HealthBarUtils to draw the health bar
            healthBarUtils.drawHealthBar(g2d,Color.RED, attacker.getHealth(), attacker.getMaxHealth(),
                    x,  // Slight padding for the health bar
                    y, // Position near the bottom of the cell
                    cellWidth,   // Health bar width as a fraction of the cell
                    cellHeight);    // Health bar height as a fraction of the cell
        }

    }
}
