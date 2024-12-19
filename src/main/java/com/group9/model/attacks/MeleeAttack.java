package com.group9.model.attacks;

import com.group9.model.board.Lane;
import com.group9.model.entities.EntityState;
import com.group9.model.entities.characters.attackers.AttackEntity;
import com.group9.model.entities.characters.defenders.DefenceEntity;
import com.group9.model.services.GameContext;

/**
 * The MeleeAttack class implements the AttackStrategy interface to provide
 * a melee attack strategy for defenders.
 */
public class MeleeAttack implements AttackStrategy {

    @Override
    public boolean useAttack(DefenceEntity defender, Lane lane, int cellIndex) {
        AttackEntity target = GameContext.getSingleTarget(lane, defender.getAttackRange(), cellIndex);
        if (target != null) {
            defender.setCurrentEntityState(EntityState.ATTACK);
            target.takeDamage(defender.getAttackDamage());
            return true;
        }
        else { // No target found
            defender.setCurrentEntityState(EntityState.IDLE);
            return false;
        }
    }
}
