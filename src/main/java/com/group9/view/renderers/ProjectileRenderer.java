package com.group9.view.renderers;

import com.group9.model.Model;
import com.group9.model.Position;
import com.group9.model.entities.Projectile;
import com.group9.view.AnimationHandler;

import javax.imageio.ImageIO;
import java.awt.*;
import java.io.File;
import java.util.Map;

public class ProjectileRenderer implements EntityRenderer {
   // private Image arrow;

    private Image arrow;

    public ProjectileRenderer() {
        try {
            File file = new File(getClass().getResource("/images/arrow.png").toURI());
            arrow = ImageIO.read(file);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void draw(Graphics2D g2d, Model model, AnimationHandler animationHandler, int cellWidth, int cellHeight, int panelWidth) {
        // Loop through all projectiles and their positions in the model
        for (Map.Entry<Projectile, Position> entry : model.getAllProjectilesPosition().entrySet()) {
            Projectile projectile = entry.getKey();
            Position position = entry.getValue();

            // Calculate the X and Y position of the projectile
            double progress = projectile.getLaneProgress(); // Get the lane progress (0 to 1)
            int projectileX = (int) (cellWidth * model.getLaneSize() * progress); // X position based on progress
            int projectileY = position.getRow() * cellHeight; // Y position based on row

            // Draw the projectile (using black color and small oval shape)
            g2d.drawImage(arrow,
                    projectileX + cellWidth / 4,
                    projectileY + cellHeight / 3,
                    cellWidth / 2,
                    cellHeight / 4, null);
        }
    }
}
