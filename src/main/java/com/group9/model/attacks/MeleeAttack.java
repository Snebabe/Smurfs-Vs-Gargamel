package com.group9.model.attacks;

import com.group9.model.attacks.AttackStrategy;
import com.group9.model.entities.Entity;

public class MeleeAttack implements AttackStrategy {


    @Override
    public void useAttack(Entity target, int damage) {
        target.takeDamage(damage);
    }
}
