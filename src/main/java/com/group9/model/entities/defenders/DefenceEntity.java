package com.group9.model.entities.defenders;

import com.group9.model.entities.Entity;
import com.group9.model.entities.EntityState;

public class DefenceEntity extends Entity {

    private int cost;
    private boolean ranged;

    public DefenceEntity(Enum type,int maxHealth, int attackDamage, int range, int cost, boolean ranged) {
        super(type, maxHealth, attackDamage, range);
        this.ranged = ranged;
        this.setCurrentState(EntityState.IDLE);
    }



    public boolean isRanged() {
        return ranged;
    }

}
