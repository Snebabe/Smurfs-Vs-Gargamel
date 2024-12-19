package com.group9.model.entities.characters.defenders;

import com.group9.model.attacks.AttackStrategy;
import com.group9.model.board.Lane;
import com.group9.model.entities.characters.Character;
import com.group9.model.entities.EntityState;

/**
 * Represents a defender entity in the game.
 */
public class DefenceEntity extends Character {

    /**
     * Constructs a DefenceEntity with the specified attributes.
     *
     * @param name the name of the defender
     * @param maxHealth the maximum health of the defender
     * @param attackDamage the attack damage of the defender
     * @param range the attack range of the defender
     * @param attackDelay the delay between attacks
     * @param attackStrategy the attack strategy of the defender
     */
    public DefenceEntity(String name, float maxHealth, float attackDamage, int range, float attackDelay, AttackStrategy attackStrategy) {
        super(name, maxHealth, attackDamage, range, attackDelay, attackStrategy);
        setCurrentEntityState(EntityState.IDLE);

    }

    /**
     * Uses the attack strategy to attack the target(s) in the given lane and cell index.
     *
     * @param lane the lane in which the target(s) is located
     * @param cellIndex the index of the cell in the lane where the target(s) is located
     * @return true if the attack was successful, false otherwise
     */
    public boolean useAttack(Lane lane, int cellIndex) {
        return getAttackStrategy().useAttack(this, lane, cellIndex);
    }

}
