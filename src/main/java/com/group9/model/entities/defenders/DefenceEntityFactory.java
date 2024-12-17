package com.group9.model.entities.defenders;

import com.group9.model.attacks.MeleeAttack;
import com.group9.model.attacks.RangedAttack;
import com.group9.model.entities.projectiles.ProjectileType;

public class DefenceEntityFactory {

    public static DefenceEntity createDefender(DefenderType type, float laneProgress) {
        switch (type) {
            case SHROOM:
                return new DefenceEntity(DefenderType.SHROOM, 200, 0, 0, type.getCost(), laneProgress, new MeleeAttack());
            case BOXER:
                return new DefenceEntity(DefenderType.BOXER, 150, 15, 1, type.getCost(), laneProgress, new MeleeAttack());
            case ARCHER:
                return new DefenceEntity(DefenderType.ARCHER, 50, 20, 3, type.getCost(), laneProgress, new RangedAttack(ProjectileType.ARROW));
            case SHOOTER:
                return new DefenceEntity(DefenderType.SHOOTER, 50, 30, 5, type.getCost(), laneProgress, new RangedAttack(ProjectileType.BULLET));
            default:
                throw new IllegalArgumentException("Invalid defender type: " + type);
        }
    }
}
