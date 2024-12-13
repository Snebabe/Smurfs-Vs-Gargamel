package com.group9.model.entities;

import com.group9.model.attacks.AttackStrategy;
import com.group9.model.hasAttack;

public abstract class Entity implements hasAttack {
    private EntityState currentState;
    private int health;
    private int maxHealth;
    private int attackDamage;
    private boolean isDead;
    private int attackRange;
    private Enum type;

    public Entity(Enum type, int maxHealth, int attackDamage, int attackRange) {

        this.health = maxHealth;
        this.maxHealth = maxHealth;
        this.attackDamage = attackDamage;
        this.attackRange = attackRange;
        this.isDead = false;
        this.type = type;
    }

    public Enum getType() {
        return this.type;
    }

    public EntityState getCurrentState() {
        return currentState;
    }

    public void setCurrentState(EntityState state) {
        this.currentState = state;
    }

    public int getAttackRange() {
        return attackRange;
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


    public void useAttack(Entity target) {
        // setAnimationState("attacking")
        entity.takeDamage(this.getAttackDamage());
    }

    public void takeDamage(int damage) {
        this.health -= damage;
        if(this.health <= 0) {
            isDead = true;
        }
    }


}
