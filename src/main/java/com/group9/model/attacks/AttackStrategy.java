package com.group9.model.attacks;

import com.group9.model.board.Lane;
import com.group9.model.entities.characters.defenders.DefenceEntity;

public interface AttackStrategy {
    boolean useAttack(DefenceEntity defender, Lane lane, int cellIndex);

}
