package com.group9.model.entities.characters.attackers;

import com.group9.model.entities.characters.CharacterType;

public class AttackerType extends CharacterType {
    private int resourceReward;
    private int speed;

    public AttackerType(String name, int maxHealth, int attackDamage, int range, float attackSpeed, int speed, int resourceReward) {
        super(name, maxHealth, attackDamage, range, attackSpeed);
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


