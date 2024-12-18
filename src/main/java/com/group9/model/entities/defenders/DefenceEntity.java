package com.group9.model.entities.defenders;

import com.group9.model.attacks.AttackStrategy;
import com.group9.model.attacks.GameContext;
import com.group9.model.board.Lane;
import com.group9.model.entities.Entity;
import com.group9.model.entities.EntityState;

import java.util.List;

public class DefenceEntity extends Entity {

    private int cost;

    public DefenceEntity(Enum type, int maxHealth, int attackDamage, int range, float attackSpeed, int cost, float laneProgess, AttackStrategy attackStrategy) {
        super(type, maxHealth, attackDamage, range, attackSpeed, laneProgess, attackStrategy);
        this.setCurrentState(EntityState.IDLE);

    }

    public boolean useAttack(Lane lane, int cellIndex) {
        return getAttackStrategy().useAttack(this, lane, cellIndex);
    }


}
