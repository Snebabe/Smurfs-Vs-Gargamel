package com.group9.model.attacks;

import com.group9.model.services.PositionConverter;
import com.group9.model.board.Lane;
import com.group9.model.entities.EntityState;
import com.group9.model.entities.characters.attackers.AttackEntity;
import com.group9.model.entities.characters.defenders.DefenceEntity;
import com.group9.model.entities.projectiles.Projectile;
import com.group9.model.entities.projectiles.ProjectileFactory;
import com.group9.model.entities.projectiles.ProjectileType;
import com.group9.model.services.GameContext;

/**
 * The RangedAttack class implements the AttackStrategy interface to provide
 * a ranged attack strategy for defenders using projectiles.
 */
public class RangedAttack implements AttackStrategy {
    private final ProjectileType projectileType;

    /**
     * Constructor to set the type of projectile used.
     *
     * @param projectileType the type of projectile to be used in the attack
     */
    public RangedAttack(ProjectileType projectileType) {
        this.projectileType = projectileType;
    }

    @Override
    public boolean useAttack(DefenceEntity defender, Lane lane, int cellIndex) {
        AttackEntity target = GameContext.getSingleTarget(lane, defender.getAttackRange(), cellIndex);
        if (target != null) {
            defender.setCurrentEntityState(EntityState.ATTACK);
            float projectileStartLaneProgress = PositionConverter.defenderToLaneProgress(cellIndex, lane.getNumberOfCells());
            Projectile projectile = ProjectileFactory.createProjectile(projectileType, projectileStartLaneProgress, defender.getAttackRange(), defender.getAttackDamage());
            lane.addProjectile(projectile);
            return true;
        }
        else {
            defender.setCurrentEntityState(EntityState.IDLE);
            return false;
        }
    }

}