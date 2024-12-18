package com.group9.model.entities.characters.defenders;

public class DefenceEntityFactory {

    // Create a defender based on the given type
    public static DefenceEntity createDefender(DefenderType type) {
        if (type == null) {
            throw new IllegalArgumentException("Invalid defender type");
        }
        return new DefenceEntity(type.getName(), type.getMaxHealth(), type.getAttackDamage(), type.getRange(), type.getAttackSpeed(), type.getAttackStrategy());
    }


   /* public static DefenceEntity createDefender(DefenderType type, float laneProgress) {
        switch (type) {
            case SHROOM:
                return new DefenceEntity(DefenderType.SHROOM, 200, 0, 0, 0f, type.getCost(), new MeleeAttack());
            case BOXER:
                return new DefenceEntity(DefenderType.BOXER, 150, 10, 1, 0.5f,type.getCost(), new MeleeAttack());
            case ARCHER:
                return new DefenceEntity(DefenderType.ARCHER, 50, 20, 3, 1f, type.getCost(), new RangedAttack(ProjectileType.ARROW));
            case SHOOTER:
                return new DefenceEntity(DefenderType.SHOOTER, 50, 20, 5, 0.5f, type.getCost(), new RangedAttack(ProjectileType.BULLET));
            default:
                throw new IllegalArgumentException("Invalid defender type: " + type);
        }
    }*/
}
