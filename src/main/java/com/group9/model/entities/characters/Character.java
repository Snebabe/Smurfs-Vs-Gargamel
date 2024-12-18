package com.group9.model.entities.characters;

import com.group9.model.attacks.AttackStrategy;
import com.group9.model.entities.EntityState;

public abstract class Character {
    private EntityState currentState;
    private int health;
    private int maxHealth;
    private int attackDamage;
    private boolean isDead;
    private int attackRange;
    private float attackSpeed;
    private String name;
    private AttackStrategy attackStrategy;

    public Character(String name, int maxHealth, int attackDamage, int attackRange, float attackSpeed, AttackStrategy attackStrategy) {

        this.health = maxHealth;
        this.maxHealth = maxHealth;
        this.attackDamage = attackDamage;
        this.attackRange = attackRange;
        this.attackSpeed = attackSpeed;
        this.isDead = false;
        this.name = name;
        this.attackStrategy = attackStrategy;
    }

    public String getName() {
        return this.name;
    }

    public EntityState getCurrentState() {
        return currentState;
    }

    public AttackStrategy getAttackStrategy() {
        return attackStrategy;
    }

    public void setCurrentState(EntityState state) {
        this.currentState = state;
    }

    public int getAttackRange() {
        return attackRange;
    }
    public float getAttackSpeed() {
        return attackSpeed;
    }

    public int getAttackDamage() {
        return attackDamage;
    }

    public int getHealth() {
        return this.health;
    }

    public int getMaxHealth() {
        return this.maxHealth;
    }

    public boolean isDead() {
        return this.isDead;
    }


    public void useAttack(Character target) {
        // setAnimationState("attacking")
        //target.takeDamage(this.getAttackDamage());
        target.takeDamage(this.getAttackDamage());
    }

    public void takeDamage(int damage) {
        health -= damage;
        if(health <= 0) {
            isDead = true;
        }
    }


}
