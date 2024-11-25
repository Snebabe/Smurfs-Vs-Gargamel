package com.group9.model;

public abstract class Entity {
    private int health;
    private int attack;
    private int xPosition;
    private Lane lane;
    private boolean isDead;

    private int range;

    Entity(int health, int attack,int range, Lane lane, int xPosition) {
        this.health = health;
        this.attack = attack;
        this.xPosition = xPosition;
        this.lane = lane;
        this.isDead = false;
    }

    public int getRange() {
        return range;
    }

    public int getAttack() {
        return attack;
    }


    public int getHealth() {
        return this.health;
    }

    public boolean isDead() {
        return this.isDead;
    }

    public int getXPosition() {
        return this.xPosition;
    }
    public void setXPosition(int x) {
        this.xPosition = x;
    }
    public Lane getLane() {
        return this.lane;
    }

    public void performAction() {}

    public void takeDamage(int damage) {
        this.health -= damage;
        if(this.health <= 0) {
            isDead = true;
        }
    }


}
