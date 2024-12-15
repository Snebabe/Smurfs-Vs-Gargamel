package com.group9.model.attacks;

import com.group9.model.attacks.AttackStrategy;
import com.group9.model.board.Lane;
import com.group9.model.entities.Entity;
import com.group9.model.entities.EntityState;
import com.group9.model.entities.attackers.AttackEntity;
import com.group9.model.entities.defenders.DefenceEntity;

import java.util.List;

public class MeleeAttack implements AttackStrategy {

    @Override
    public void useAttack(DefenceEntity defender, Lane lane, int cellIndex) {
        AttackEntity target = GameContext.getSingleTarget(lane, defender.getAttackRange(), cellIndex);
        if (target != null) {
            defender.setCurrentState(EntityState.ATTACK);
            target.takeDamage(defender.getAttackDamage());
        }
        else defender.setCurrentState(EntityState.IDLE);
    }
}
