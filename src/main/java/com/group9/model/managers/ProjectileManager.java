package com.group9.model.managers;

import com.group9.model.entities.projectiles.Projectile;

public class ProjectileManager {
    private List<Projectile> projectiles = new ArrayList<>();

    public void addProjectile(Projectile projectile) {
        projectiles.add(projectile);
    }

    public void updateProjectiles() {
        for (int i = 0; i < projectiles.size(); i++) {
            Projectile projectile = projectiles.get(i);
            projectile.update();

            if (projectile.isDestroyed()) {
                projectiles.remove(i--); // Remove destroyed projectiles
            }
        }
    }

    public List<Projectile> getActiveProjectiles() {
        return projectiles;
    }
}