package com.group9.model.entities.characters;

import com.group9.model.attacks.AttackStrategy;
import com.group9.model.entities.EntityState;
import com.group9.model.entities.EntityType;

public abstract class Character extends EntityType {
    private EntityState currentState;
    private float health;
    private float maxHealth;
    private float attackDamage;
    private boolean isDead;
    private int attackRange;
    private float attackDelay;
    private AttackStrategy attackStrategy;

    public Character(String name, float maxHealth, float attackDamage, int attackRange, float attackDelay, AttackStrategy attackStrategy) {
        super(name);
        this.health = maxHealth;
        this.maxHealth = maxHealth;
        this.attackDamage = attackDamage;
        this.attackRange = attackRange;
        this.attackDelay = attackDelay;
        this.isDead = false;
        this.attackStrategy = attackStrategy;
    }

    public EntityState getCurrentState() {
        return currentState;
    }

    public AttackStrategy getAttackStrategy() {
        return attackStrategy;
    }

    public void setCurrentState(EntityState state) {
        currentState = state;
    }

    public int getAttackRange() {
        return attackRange;
    }
    public float getAttackDelay() {
        return attackDelay;
    }

    public float getAttackDamage() {
        return attackDamage;
    }

    public float getHealth() {
        return health;
    }

    public float getMaxHealth() {
        return maxHealth;
    }

    public boolean isDead() {
        return isDead;
    }


    public void useAttack(Character target) {
        // setAnimationState("attacking")
        //target.takeDamage(this.getAttackDamage());
        target.takeDamage(this.getAttackDamage());
    }

    public void takeDamage(float damage) {
        health -= damage;
        if(health <= 0) {
            isDead = true;
        }
    }


}
