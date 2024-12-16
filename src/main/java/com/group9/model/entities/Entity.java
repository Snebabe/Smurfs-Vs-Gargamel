package com.group9.model.entities;

import com.group9.model.attacks.AttackStrategy;

public abstract class Entity  {
    private EntityState currentState;
    private int health;
    private int maxHealth;
    private int attackDamage;
    private boolean isDead;
    private int attackRange;
    private Enum type;
    private AttackStrategy attackStrategy;
    private float laneProgress;

    public Entity(Enum type, int maxHealth, int attackDamage, int attackRange, float laneProgress, AttackStrategy attackStrategy) {

        this.health = maxHealth;
        this.maxHealth = maxHealth;
        this.attackDamage = attackDamage;
        this.attackRange = attackRange;
        this.isDead = false;
        this.type = type;
        this.laneProgress = laneProgress;
        this.attackStrategy = attackStrategy;
    }

    public float getLaneProgress() {
        return this.laneProgress;
    }
    public void setLaneProgress(float laneProgress) {
        this.laneProgress = laneProgress;
    }

    public Enum getType() {
        return this.type;
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
