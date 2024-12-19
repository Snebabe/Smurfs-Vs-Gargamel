package com.group9.model.entities.characters.defenders;

public class DefenceEntityFactory {

    // Create a defender based on the given type
    public static DefenceEntity createDefender(DefenderType type) {
        if (type == null) {
            throw new IllegalArgumentException("Invalid defender type");
        }
        return new DefenceEntity(
                type.getName(),
                type.getMaxHealth(),
                type.getAttackDamage(),
                type.getRange(),
                type.getAttackSpeed(),
                type.getAttackStrategy());
    }
}
