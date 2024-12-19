package com.group9.model.entities.characters.defenders;

import com.group9.model.attacks.AttackStrategy;
import com.group9.model.entities.characters.CharacterType;

public class DefenderType extends CharacterType {
    private final int cost; // Cost of the defender
    private final AttackStrategy attackStrategy;  // Attack strategy of the defender

    public DefenderType(String name, float maxHealth, float attackDamage, int range, float attackDelay, int cost, AttackStrategy attackStrategy) {
        super(name, maxHealth, attackDamage, range, attackDelay);
        this.cost = cost;
        this.attackStrategy = attackStrategy;
    }

    public int getCost() {
        return cost;
    }

    public AttackStrategy getAttackStrategy() {
        return attackStrategy;
    }

}

