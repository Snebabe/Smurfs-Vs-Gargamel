package com.group9.view.renderers;

import com.group9.model.Model;
import com.group9.model.Position;
import com.group9.model.entities.Projectile;
import com.group9.view.AnimationHandler;

import java.awt.*;
import java.util.Map;

public class ProjectileRenderer implements EntityRenderer {

    @Override
    public void draw(Graphics2D g2d, Model model, AnimationHandler animationHandler, int cellWidth, int cellHeight, int panelWidth) {
        // Loop through all projectiles and their positions in the model
        for (Map.Entry<Projectile, Position> entry : model.getAllProjectilesPosition().entrySet()) {
            Projectile projectile = entry.getKey();
            Position position = entry.getValue();

            // Calculate the X and Y position of the projectile
            double progress = projectile.getLaneProgress(); // Get the lane progress (0 to 1)
            int projectileX = (int) (g2d.getClipBounds().width * progress);  // X position based on progress
            int projectileY = position.getRow() * (g2d.getClipBounds().height / model.getLaneAmount()); // Y position based on row

            // Draw the projectile (using black color and small oval shape)
            g2d.setColor(Color.BLACK);
            g2d.fillOval(projectileX + (g2d.getClipBounds().width / model.getLaneSize()) / 3,
                    projectileY + (g2d.getClipBounds().height / model.getLaneAmount()) / 3,
                    5, 5); // Adjust the position and size
        }
    }
}
