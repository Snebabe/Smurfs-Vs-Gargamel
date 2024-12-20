package com.group9.view.renderers;

import com.group9.model.entities.characters.defenders.DefenceEntity;
import com.group9.model.Position;
import com.group9.model.Model;
import com.group9.view.AnimationHandler;
import com.group9.view.services.HealthBarUtils;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.Map;

/**
 * The DefenderRenderer class is responsible for rendering defender entities on the game panel.
 * It uses the provided HealthBarUtils to draw health bars for the defenders.
 */
public class DefenderRenderer implements EntityRenderer {

    @Override
    public void draw(Graphics2D g2d, Model model, AnimationHandler animationHandler, int cellWidth, int cellHeight, int panelWidth) {
        for (Map.Entry<DefenceEntity, Position> entry : model.getAllDefendersPosition().entrySet()) {
            DefenceEntity defender = entry.getKey();
            Position position = entry.getValue();

            // Calculate the top-left corner of the grid cell
            int x = position.getCol() * cellWidth;
            int y = position.getRow() * cellHeight;

            int xOffset = (int) (cellWidth * 0.1);
            int yOffset = (int) (cellHeight * 0.1);

            BufferedImage defenderImage = (BufferedImage) animationHandler.getFrame(defender.getName(), defender.getCurrentEntityState());

            // For frames that take up 3 grid spaces (Quick fix)
            int defenderWidth = (defenderImage.getWidth() > 350)
                    ? cellWidth * 3
                    : cellWidth;

            // Draw defender image, filling the grid cell
            g2d.drawImage(defenderImage,
                    x + xOffset, y + yOffset, defenderWidth - xOffset, cellHeight - yOffset, null);

            HealthBarUtils.drawHealthBar(g2d, Color.GREEN, defender.getHealth(), defender.getMaxHealth(),
                    x,
                    y,
                    cellWidth,
                    cellHeight);
        }
    }

}
