package com.group9.model.attacks;

import com.group9.model.entities.Entity;

public interface AttackStrategy {
    void useAttack(Entity target, int damage);
}
