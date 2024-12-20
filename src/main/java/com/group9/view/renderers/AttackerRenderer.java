package com.group9.view.renderers;

import com.group9.model.services.PositionConverter;
import com.group9.model.entities.characters.attackers.AttackEntity;
import com.group9.model.Position;
import com.group9.model.Model;
import com.group9.view.AnimationHandler;
import com.group9.view.services.HealthBarUtils;

import java.awt.*;
import java.util.Map;

/**
 * The AttackerRenderer class is responsible for rendering attacker entities on the game panel.
 * It uses the provided HealthBarUtils to draw health bars for the attackers.
 */
public class AttackerRenderer implements EntityRenderer {

    @Override
    public void draw(Graphics2D g2d, Model model, AnimationHandler animationHandler, int cellWidth, int cellHeight, int panelWidth) {
        for (Map.Entry<AttackEntity, Position> entry : model.getAllAttackersPosition().entrySet()) {
            AttackEntity attacker = entry.getKey();
            Position position = entry.getValue();

            // Calculate the attacker's progress along the lane and position
            int x = (int) (cellWidth * PositionConverter.attackerToCellIndex(attacker.getLaneProgress(), model.getLaneSize()));
            int y = position.getRow() * cellHeight; // Vertical position based on row

            int xOffset = (int) (cellWidth * 0.1);
            int yOffset = (int) (cellHeight * 0.1);

            // Draw defender image, filling the grid cell
            g2d.drawImage(animationHandler.getFrame(attacker.getName(), attacker.getCurrentEntityState()),
                    x + xOffset, y + yOffset, cellWidth - xOffset, cellHeight - yOffset, null);

            HealthBarUtils.drawHealthBar(g2d,Color.RED, attacker.getHealth(), attacker.getMaxHealth(),
                    x,
                    y,
                    cellWidth,
                    cellHeight);
        }
    }
}
