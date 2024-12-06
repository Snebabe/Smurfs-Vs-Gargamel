package com.group9.view.renderers;

import com.group9.model.entities.attackers.AttackEntity;
import com.group9.model.Position;
import com.group9.model.Model;

import java.awt.*;
import java.util.Map;

public class AttackerRenderer implements EntityRenderer {

    private final HealthBarUtils healthBarUtils;

    // Pass the HealthBarUtils instance to the renderer
    public AttackerRenderer(HealthBarUtils healthBarUtils) {
        this.healthBarUtils = healthBarUtils;
    }

    @Override
    public void draw(Graphics2D g2d, Model model, int cellWidth, int cellHeight, int panelWidth) {
        // Loop through all attackers and their positions
        for (Map.Entry<AttackEntity, Position> entry : model.getAllAttackersPosition().entrySet()) {
            AttackEntity attacker = entry.getKey();
            Position position = entry.getValue();

            double progress = attacker.getLaneProgress();
            int pixelX = (int) (g2d.getClipBounds().width * (1 - progress));
            int pixelY = position.getRow() * g2d.getClipBounds().height / model.getLaneAmount();

            // Draw attacker
            g2d.setColor(Color.RED);
            g2d.fillOval(pixelX + g2d.getClipBounds().width / model.getLaneSize() / 3,
                    pixelY + g2d.getClipBounds().height / model.getLaneAmount() / 3,
                    g2d.getClipBounds().width / model.getLaneSize() / 2, g2d.getClipBounds().height / model.getLaneAmount() / 2);

            // Use HealthBarUtils to draw the health bar
            healthBarUtils.drawHealthBar(g2d, attacker.getHealth(), attacker.getMaxHealth(),
                    pixelX + g2d.getClipBounds().width / model.getLaneSize() / 3,
                    pixelY + g2d.getClipBounds().height / model.getLaneAmount() / 6,
                    g2d.getClipBounds().width / model.getLaneSize() / 2,
                    g2d.getClipBounds().height / model.getLaneAmount() / 10);
        }
    }
}
