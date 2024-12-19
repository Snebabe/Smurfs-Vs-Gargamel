package com.group9.model.entities.characters.attackers;

import com.group9.model.entities.EntityConfiguration;

import java.util.List;
import java.util.Random;

/**
 * The AttackEntityFactory class provides factory methods to create AttackEntity instances.
 */
public class AttackEntityFactory {

    /**
     * Factory method to create an attacker based on a specified attacker type.
     *
     * @param type the type of attacker to create
     * @return a new AttackEntity based on the properties of the attacker type
     * @throws IllegalArgumentException if the type is null
     */
    public static AttackEntity createAttacker(AttackerType type) {
        // Validate that the type is not null
        if (type == null) {
            throw new IllegalArgumentException("Invalid attacker type");
        }
        // Create and return a new AttackEntity based on the properties of the attacker type
        return new AttackEntity(
                type.getName(),
                type.getMaxHealth(),
                type.getAttackDamage(),
                type.getRange(),
                type.getAttackDelay(),
                type.getSpeed(),
                type.getResourceReward()
        );

    }

    /**
     * Factory method to create a random attacker from all available types.
     *
     * @return a new AttackEntity of a randomly selected type
     */
    public static AttackEntity createRandomAttacker() {
        List<AttackerType> attackerTypeList = EntityConfiguration.getAttackerTypes();

        // Generate a random index to select an attacker type
        int randomIndex = new Random().nextInt(attackerTypeList.size());

        AttackerType randomType = attackerTypeList.get(randomIndex);

        // Create and return an attacker of the randomly selected type
        return createAttacker(randomType);
    }

}
