package com.group9.model.entities.characters.defenders;

/**
 * The DefenceEntityFactory class provides factory methods to create DefenceEntity instances based on DefenderTypes.
 */
public class DefenceEntityFactory {

    /**
     * Factory method to create a defender based on a specified defender type.
     *
     * @param type the type of defender to create
     * @return a new DefenceEntity based on the properties of the defender type
     * @throws IllegalArgumentException if the type is null
     */
    public static DefenceEntity createDefender(DefenderType type) {
        if (type == null) {
            throw new IllegalArgumentException("Invalid defender type");
        }
        return new DefenceEntity(
                type.getName(),
                type.getMaxHealth(),
                type.getAttackDamage(),
                type.getRange(),
                type.getAttackDelay(),
                type.getAttackStrategy());
    }
}
