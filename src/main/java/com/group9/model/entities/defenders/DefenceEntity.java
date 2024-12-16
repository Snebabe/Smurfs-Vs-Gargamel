package com.group9.model.entities.defenders;

import com.group9.model.attacks.AttackStrategy;
import com.group9.model.attacks.GameContext;
import com.group9.model.board.Lane;
import com.group9.model.entities.Entity;
import com.group9.model.entities.EntityState;

import java.util.List;

public class DefenceEntity extends Entity {

    private int cost;

    public DefenceEntity(Enum type, int maxHealth, int attackDamage, int range, int cost, float laneProgess, AttackStrategy attackStrategy) {
        super(type, maxHealth, attackDamage, range, laneProgess, attackStrategy);
        this.setCurrentState(EntityState.IDLE);

    }

    public void useAttack(Lane lane, int cellIndex) {
        getAttackStrategy().useAttack(this, lane, cellIndex);
    }


}
