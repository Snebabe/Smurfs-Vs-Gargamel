package com.group9.model.entities.characters.attackers;

import com.group9.model.entities.characters.CharacterType;

/**
 * The AttackerType class represents a specific type of attacker character.
 * It extends the CharacterType class and includes additional attributes specific to attackers.
 */
public class AttackerType extends CharacterType {
    // Fields specific to the attacker type
    private int resourceReward;  // Resource rewarded when the attacker is defeated
    private int speed; // Speed of the attacker

    public AttackerType(String name, float maxHealth, float attackDamage, int range, float attackDelay, int speed, int resourceReward) {
        super(name, maxHealth, attackDamage, range, attackDelay);

        if (speed < 0) {
            throw new IllegalArgumentException("Speed must be positive");
        }
        if (resourceReward < 0) {
            throw new IllegalArgumentException("Resource reward cannot be negative");
        }
        this.resourceReward = resourceReward;
        this.speed = speed;
    }

    public int getResourceReward() {
        return resourceReward;
    }

    public int getSpeed() {
        return speed;
    }

    @Override
    public String toString() {
        // Call the toString() method of the superclass (CharacterType) to include general character properties
        return super.toString() + ", Speed: " + speed + ", Resource Reward: " + resourceReward;
    }

}


