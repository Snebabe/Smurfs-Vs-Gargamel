package com.group9.model.entities;

import com.group9.model.hasAttack;

public abstract class Entity implements hasAttack {
    private AnimationState state;
    private int health;
    private int maxHealth;
    private int attackDamage;
    private boolean isDead;
    private int attackRange;

    public Entity(int maxHealth, int attackDamage, int attackRange) {
        this.health = maxHealth;
        this.maxHealth = maxHealth;
        this.attackDamage = attackDamage;
        this.attackRange = attackRange;
        this.isDead = false;
    }

    public int getAttackRange() {
        return attackRange;
    }

    public int getAttackDamage() {
        return attackDamage;
    }

    public void setState(AnimationState state) {
        this.state = state;
    }
    
    public AnimationState getState() {
        return state;
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


    public void useAttack(Entity entity) {
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
