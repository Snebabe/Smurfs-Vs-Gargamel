package com.group9.view.renderers;

import com.group9.model.entities.defenders.DefenceEntity;
import com.group9.model.Position;
import com.group9.model.Model;

import java.awt.*;
import java.util.Map;

public class DefenderRenderer implements EntityRenderer {

    private final HealthBarUtils healthBarUtils;

    // Pass the HealthBarUtils instance to the renderer
    public DefenderRenderer(HealthBarUtils healthBarUtils) {
        this.healthBarUtils = healthBarUtils;
    }

    @Override
    public void draw(Graphics2D g2d, Model model, int cellWidth, int cellHeight, int panelWidth) {
        // Loop through all defenders and their positions
        for (Map.Entry<DefenceEntity, Position> entry : model.getAllDefendersPosition().entrySet()) {
            DefenceEntity defender = entry.getKey();
            Position position = entry.getValue();

            int x = position.getCol() * g2d.getClipBounds().width / model.getLaneSize();
            int y = position.getRow() * g2d.getClipBounds().height / model.getLaneAmount();

            // Draw defender
            g2d.setColor(Color.BLUE);
            g2d.fillRect(x + g2d.getClipBounds().width / model.getLaneSize() / 4, y + g2d.getClipBounds().height / model.getLaneAmount() / 3,
                    g2d.getClipBounds().width / model.getLaneSize() / 2, g2d.getClipBounds().height / model.getLaneAmount() / 2);

            // Use HealthBarUtils to draw the health bar
            healthBarUtils.drawHealthBar(g2d, defender.getHealth(), defender.getMaxHealth(),
                    x + g2d.getClipBounds().width / model.getLaneSize() / 4,
                    y + g2d.getClipBounds().height / model.getLaneAmount() / 7,
                    g2d.getClipBounds().width / model.getLaneSize() / 2,
                    g2d.getClipBounds().height / model.getLaneAmount() / 10);
        }
    }
}
