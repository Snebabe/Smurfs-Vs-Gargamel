package com.group9.model.entities.characters;

import com.group9.model.entities.EntityType;

/**
 * The CharacterType is an abstract class representing a general character type in the game.
 * It extends the EntityType class and includes attributes specific to characters.
 */
public abstract class CharacterType extends EntityType {
    private final float maxHealth;
    private final float attackDamage;
    private final int range;
    private final float attackDelay;

    /**
     * Constructs a CharacterType with the specified attributes.
     *
     * @param name the name of the character type
     * @param maxHealth the maximum health of the character type
     * @param attackDamage the attack damage of the character type
     * @param range the attack range of the character type
     * @param attackDelay the delay between attacks
     */
    public CharacterType(String name, float maxHealth, float attackDamage, int range, float attackDelay) {
        super(name);
        this.maxHealth = maxHealth;
        this.attackDamage = attackDamage;
        this.range = range;
        this.attackDelay = attackDelay;
    }

    public float getMaxHealth() {
        return maxHealth;
    }

    public float getAttackDamage() {
        return attackDamage;
    }

    public int getRange() {
        return range;
    }

    public float getAttackDelay() {
        return attackDelay;
    }

}
