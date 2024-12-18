package com.group9.model.entities.characters;

public class CharacterType {
    private String name;
    private int maxHealth;
    private int attackDamage;
    private int range;
    private float attackSpeed;

    // Superclass for DefenderType and AttackerType
    public CharacterType(String name, int maxHealth, int attackDamage, int range, float attackSpeed) {
        this.name = name;
        this.maxHealth = maxHealth;
        this.attackDamage = attackDamage;
        this.range = range;
        this.attackSpeed = attackSpeed;
    }

    public String getName() {
        return name;
    }

    public int getMaxHealth() {
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
