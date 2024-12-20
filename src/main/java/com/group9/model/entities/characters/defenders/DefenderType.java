package com.group9.model.entities.characters.defenders;

import com.group9.model.attacks.AttackStrategy;
import com.group9.model.entities.characters.CharacterType;

/**
 * Represents a type of defender in the game.
 */
public class DefenderType extends CharacterType {
    private final int cost;
    private final AttackStrategy attackStrategy;
    private final String info;

    /**
     * Constructs a DefenderType with the specified attributes.
     *
     * @param name the name of the defender type
     * @param maxHealth the maximum health of the defender type
     * @param attackDamage the attack damage of the defender type
     * @param range the attack range of the defender type
     * @param attackDelay the delay between attacks in seconds
     * @param cost the cost of the defender type
     * @param attackStrategy the attack strategy of the defender type
     */
    public DefenderType(String name, String info, float maxHealth, float attackDamage, int range, float attackDelay, int cost, AttackStrategy attackStrategy) {
        super(name, maxHealth, attackDamage, range, attackDelay);
        this.cost = cost;
        this.attackStrategy = attackStrategy;
        this.info = info;
    }

    public int getCost() {
        return cost;
    }

    public AttackStrategy getAttackStrategy() {
        return attackStrategy;
    }

    public String getInfo() {
        return info;
    }

}

