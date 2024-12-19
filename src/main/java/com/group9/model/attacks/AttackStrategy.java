package com.group9.model.attacks;

import com.group9.model.board.Lane;
import com.group9.model.entities.characters.defenders.DefenceEntity;

/**
 * The AttackStrategy interface defines the method that must be implemented
 * by any class that wishes to provide an attack strategy for defenders.
 */
public interface AttackStrategy {
    boolean useAttack(DefenceEntity defender, Lane lane, int cellIndex);

}
