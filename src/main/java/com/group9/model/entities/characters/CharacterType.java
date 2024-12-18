package com.group9.model.entities.characters;

import com.group9.model.entities.EntityType;

public class CharacterType extends EntityType {
    private float maxHealth;
    private float attackDamage;
    private int range;
    private float attackSpeed;

    // Superclass for DefenderType and AttackerType
    public CharacterType(String name, int maxHealth, int attackDamage, int range, float attackDelay) {
        this.name = name;
        this.maxHealth = maxHealth;
        this.attackDamage = attackDamage;
        this.range = range;
        this.attackSpeed = attackDelay;
    }

    public float getMaxHealth() {
        return maxHealth;
    }

    public int getAttackDamage() {
        return attackDamage;
    }

    public int getRange() {
        return range;
    }

    public float getAttackSpeed() {
        return attackSpeed;
    }


}
