package com.group9.model.entities.defenders;

import com.group9.model.Projectile;
import com.group9.model.entities.Entity;
import com.group9.model.entities.attackers.AttackEntity;

import java.util.List;

public abstract class DefenceEntity extends Entity {
    private int cost;
    private boolean ranged;


    public DefenceEntity(int maxHealth, int attackDamage, int range, int cost, boolean ranged) {
        super(maxHealth, attackDamage, range);
        this.ranged = ranged;
    }

    public void useAttack(AttackEntity attackEntity, List<Projectile> projectiles, float laneProgress) {
        if(ranged) {
            projectiles.add(new Projectile(laneProgress, attackEntity, 1, getAttackDamage()));
        }
        else {
            super.useAttack(attackEntity);
        }
    }
}
