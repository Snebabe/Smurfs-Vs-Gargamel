package com.group9.model.entities.characters.defenders;

import com.group9.model.attacks.AttackStrategy;
import com.group9.model.board.Lane;
import com.group9.model.entities.characters.Character;
import com.group9.model.entities.EntityState;

/**
 * Represents a defender entity in the game.
 */
public class DefenceEntity extends Character {

    // Constructor to set up the defender
    public DefenceEntity(String name, float maxHealth, float attackDamage, int range, float attackDelay, AttackStrategy attackStrategy) {
        super(name, maxHealth, attackDamage, range, attackDelay, attackStrategy);  // Initialize using Character constructor
        this.setCurrentState(EntityState.IDLE);  // Set initial state to IDLE

    }

    // Use the attack strategy to attack
    public boolean useAttack(Lane lane, int cellIndex) {
        return getAttackStrategy().useAttack(this, lane, cellIndex);  // Delegate to attack strategy
    }

}
