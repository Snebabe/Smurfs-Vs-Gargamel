package com.group9.model.attacks;

import com.group9.model.board.Lane;
import com.group9.model.entities.EntityState;
import com.group9.model.entities.characters.attackers.AttackEntity;
import com.group9.model.entities.characters.defenders.DefenceEntity;

public class MeleeAttack implements AttackStrategy {

    // Executes a melee attack. Returns true if an attack is made.
    @Override
    public boolean useAttack(DefenceEntity defender, Lane lane, int cellIndex) {
        AttackEntity target = GameContext.getSingleTarget(lane, defender.getAttackRange(), cellIndex);
        if (target != null) {
            // Set defender to attack state and deal damage to the target.
            defender.setCurrentState(EntityState.ATTACK);
            target.takeDamage(defender.getAttackDamage());
            return true;
        }
        else {
            // No target found, set defender to idle state.
            defender.setCurrentState(EntityState.IDLE);
            return false;
        }
    }
}
