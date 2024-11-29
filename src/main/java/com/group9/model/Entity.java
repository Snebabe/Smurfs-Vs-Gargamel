package com.group9.model;

public abstract class Entity implements hasAttack {
    private int health;
    private int attackDamage;
    private boolean isDead;

    private int range;

    Entity(int health, int attackDamage, int attackRange) {
        this.health = health;
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


    public int getHealth() {
        return this.health;
    }

    public boolean isDead() {
        return this.isDead;
    }


    public void useAttack(Entity entity) {
        entity.takeDamage(this.getAttackDamage());
    }

    public void takeDamage(int damage) {
        this.health -= damage;
        if(this.health <= 0) {
            isDead = true;
        }
    }


}
