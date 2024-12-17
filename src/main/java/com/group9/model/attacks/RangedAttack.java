package com.group9.model.attacks;

import com.group9.model.attacks.AttackStrategy;
import com.group9.model.board.Lane;
import com.group9.model.entities.Entity;
import com.group9.model.entities.EntityState;
import com.group9.model.entities.attackers.AttackEntity;
import com.group9.model.entities.defenders.DefenceEntity;
import com.group9.model.entities.projectiles.Projectile;
import com.group9.model.entities.projectiles.ProjectileFactory;
import com.group9.model.entities.projectiles.ProjectileType;

import java.lang.annotation.Target;
import java.util.List;

public class RangedAttack implements AttackStrategy {
    private final ProjectileType projectileType;

    public RangedAttack(ProjectileType projectileType) {
        this.projectileType = projectileType;
    }

    @Override
    public void useAttack(DefenceEntity defender, Lane lane, int cellIndex) {
        AttackEntity target = GameContext.getSingleTarget(lane, defender.getAttackRange(), cellIndex);
        if (target != null) {
            defender.setCurrentState(EntityState.ATTACK);
            Projectile projectile = ProjectileFactory.createProjectile(projectileType, defender.getLaneProgress()+0.07f, defender.getAttackRange(), defender.getAttackDamage());
            lane.addProjectile(projectile);
        }
        else defender.setCurrentState(EntityState.IDLE);
    }

}