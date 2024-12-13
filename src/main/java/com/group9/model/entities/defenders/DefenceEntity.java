package com.group9.model.entities.defenders;

import com.group9.model.attacks.AttackStrategy;
import com.group9.model.entities.Entity;
import com.group9.model.entities.EntityState;

public class DefenceEntity extends Entity {

    private int cost;

    public DefenceEntity(Enum type,int maxHealth, int attackDamage, int range, int cost, AttackStrategy attackStrategy) {
        super(type, maxHealth, attackDamage, range, attackStrategy);
        this.setCurrentState(EntityState.IDLE);
    }
}
