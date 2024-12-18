package com.group9.model.entities.characters.defenders;

import com.group9.model.attacks.AttackStrategy;
import com.group9.model.board.Lane;
import com.group9.model.entities.characters.Character;
import com.group9.model.entities.EntityState;

public class DefenceEntity extends Character {

    public DefenceEntity(String name, float maxHealth, float attackDamage, int range, float attackDelay, AttackStrategy attackStrategy) {
        super(name, maxHealth, attackDamage, range, attackDelay, attackStrategy);
        this.setCurrentState(EntityState.IDLE);

    }

    public boolean useAttack(Lane lane, int cellIndex) {
        return getAttackStrategy().useAttack(this, lane, cellIndex);
    }


}
