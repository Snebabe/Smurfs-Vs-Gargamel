package com.group9.model.attacks;

import com.group9.model.board.Lane;
import com.group9.model.entities.characters.defenders.DefenceEntity;

/**
 * The AttackStrategy interface defines the method that must be implemented
 * by any class that wishes to provide an attack strategy for defenders.
 */
public interface AttackStrategy {

    /**
     * Executes the attack strategy for a given defender on a specified lane and cell index.
     *
     * @param defender the defender entity executing the attack
     * @param lane the lane in which the attack is taking place
     * @param cellIndex the index of the cell within the lane where the attack is targeted
     * @return true if the attack was successful, false otherwise
     */
    boolean useAttack(DefenceEntity defender, Lane lane, int cellIndex);

}
