package com.group9.model.entities.characters.defenders;

import com.group9.model.attacks.AttackStrategy;
import com.group9.model.entities.characters.Character;
import com.group9.model.entities.characters.CharacterType;

public class DefenderType extends CharacterType {
    private final int cost;
    private final AttackStrategy attackStrategy;

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




/*public enum DefenderType {
    SHROOM(100,"A durable defender with high health, the Shroom blocks enemies and absorbs damage, protecting other defenders."),
    BOXER(100, "A close-combat fighter who uses powerful punches to knock back and damage enemies up close."),
    ARCHER(150, "A skilled long-range attacker, the Archer strikes from a distance to deal damage before enemies get close."),
    SHOOTER(400, "A ruthless mercenary that has a perticular distaste for Gargamels...");

    private final int cost;

    private final String description;

    DefenderType(int cost, String description) {
        this.cost = cost;
        this.description = description;
    }

    public int getCost() {
        return cost;
    }

    public String getDescription() {
        return description;
    }
}*/

