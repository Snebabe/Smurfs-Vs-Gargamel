package com.group9.model.attacks;

import com.group9.model.board.Lane;
import com.group9.model.entities.EntityState;
import com.group9.model.entities.characters.attackers.AttackEntity;
import com.group9.model.entities.characters.defenders.DefenceEntity;
import com.group9.model.services.GameContext;

import java.util.List;

/**
 * The MaxHealthAOEAttack class implements the AttackStrategy interface to provide
 * an area-of-effect attack strategy based on the maximum health of the targets.
 */
public class MaxHealthAOEAttack implements AttackStrategy {

    @Override
    public boolean useAttack(DefenceEntity defender, Lane lane, int cellIndex) {
        List<AttackEntity> targets = GameContext.getTargetsInRange(lane, defender.getAttackRange(), cellIndex);

        if (!targets.isEmpty()) {
            defender.setCurrentEntityState(EntityState.ATTACK);
            for (AttackEntity target : targets) {

                int TICKS_PER_SECOND = GameContext.getTicksPerSecond();

                // Calculate damage per tick
                float damagePercentagePerTick = defender.getAttackDamage() / (100 * TICKS_PER_SECOND);
                float damage = (target.getMaxHealth()*damagePercentagePerTick);
                target.takeDamage(damage);
            }
            return true;
        } else {
            defender.setCurrentEntityState(EntityState.IDLE);
            return false;
        }
    }
}
