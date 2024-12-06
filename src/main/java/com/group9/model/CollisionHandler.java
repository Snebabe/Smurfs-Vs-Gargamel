package com.group9.model;

import com.group9.model.entities.attackers.AttackEntity;

public class CollisionHandler {
    public static boolean checkCollisions(Projectile projectile, AttackEntity attackEntity) {
        float distance = (1-attackEntity.getLaneProgress()) - projectile.getLaneProgress();
        System.out.println("Distance to target: " + distance);

        if(distance < 0) {
            attackEntity.takeDamage(projectile.getDamage());
            projectile.setActive(false);
            System.out.println("Projectile hit target. Damage dealt: " + projectile.getDamage());
            return true;
        }
        else {
            return false;
        }
    }

}
