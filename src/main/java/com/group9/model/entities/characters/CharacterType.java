package com.group9.model.entities.characters;

import com.group9.model.entities.EntityType;

public class CharacterType extends EntityType {
    private float maxHealth;
    private float attackDamage;
    private int range;
    private float attackSpeed; // delay between attacks

    // Superclass for DefenderType and AttackerType
    public CharacterType(String name, float maxHealth, float attackDamage, int range, float attackDelay) {
        super(name); // Set the name from the parent class
        this.maxHealth = maxHealth;
        this.attackDamage = attackDamage;
        this.range = range;
        this.attackSpeed = attackDelay;
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

    public float getAttackSpeed() {
        return attackSpeed;
    }


}
