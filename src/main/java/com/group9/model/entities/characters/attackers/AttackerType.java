package com.group9.model.entities.characters.attackers;

import com.group9.model.entities.characters.CharacterType;

public class AttackerType extends CharacterType {
    private int resourceReward;
    private int speed;

    public AttackerType(String name, float maxHealth, float attackDamage, int range, float attackDelay, int speed, int resourceReward) {
        super(name, maxHealth, attackDamage, range, attackDelay);
        this.resourceReward = resourceReward;
        this.speed = speed;
    }

    public int getResourceReward() {
        return resourceReward;
    }

    public int getSpeed() {
        return speed;
    }
}

