package com.group9.view.renderers;

import com.group9.model.entities.defenders.DefenceEntity;
import com.group9.model.Position;
import com.group9.model.Model;
import com.group9.view.AnimationHandler;

import java.awt.*;
import java.util.Map;

public class DefenderRenderer implements EntityRenderer {

    private final HealthBarUtils healthBarUtils;

    // Pass the HealthBarUtils instance to the renderer
    public DefenderRenderer(HealthBarUtils healthBarUtils) {
        this.healthBarUtils = healthBarUtils;
    }

    @Override
    public void draw(Graphics2D g2d, Model model, AnimationHandler animationHandler, int cellWidth, int cellHeight, int panelWidth) {
        // Loop through all defenders and their positions
        for (Map.Entry<DefenceEntity, Position> entry : model.getAllDefendersPosition().entrySet()) {
            DefenceEntity defender = entry.getKey();
            Position position = entry.getValue();

            // Calculate the top-left corner of the grid cell
            int x = position.getCol() * cellWidth;
            int y = position.getRow() * cellHeight;

            int xOffset = (int) (cellWidth * 0.1);
            int yOffset = (int) (cellHeight * 0.1);

            // Draw defender image, filling the grid cell
            g2d.drawImage(animationHandler.getFrame(defender.getType(), defender.getCurrentState()),
                    x + xOffset, y + yOffset, cellWidth - xOffset, cellHeight - yOffset, null);

            // Use HealthBarUtils to draw the health bar
            healthBarUtils.drawHealthBar(g2d, Color.GREEN, defender.getHealth(), defender.getMaxHealth(),
                    x,  // Slight padding for the health bar
                    y, // Position near the bottom of the cell
                    cellWidth,   // Health bar width as a fraction of the cell
                    cellHeight);    // Health bar height as a fraction of the cell
        }
    }

}
