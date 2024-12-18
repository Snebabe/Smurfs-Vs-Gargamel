package com.group9.model.attacks;

import com.group9.model.board.Lane;
import com.group9.model.entities.EntityState;
import com.group9.model.entities.characters.attackers.AttackEntity;
import com.group9.model.entities.characters.defenders.DefenceEntity;

import java.util.List;

public class MaxHealthAOEAttack implements AttackStrategy {
    @Override
    public boolean useAttack(DefenceEntity defender, Lane lane, int cellIndex) {
        // Get all targets in the lane
        List<AttackEntity> targets = GameContext.getTargetsInRange(lane, defender.getAttackRange(), cellIndex);
        if (!targets.isEmpty()) {
            defender.setCurrentState(EntityState.ATTACK);
            for (AttackEntity target : targets) {
                int damage = target.getMaxHealth()*defender.getAttackDamage()/1000;
                target.takeDamage(damage);
            }
            return true;
        } else {
            defender.setCurrentState(EntityState.IDLE);
            return false;
        }
    }
}
