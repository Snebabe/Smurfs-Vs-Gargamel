package com.group9.model.attacks;

import com.group9.model.board.Lane;
import com.group9.model.entities.Entity;
import com.group9.model.entities.defenders.DefenceEntity;

import java.util.List;

public interface AttackStrategy {
    void useAttack(DefenceEntity defender, Lane lane, int cellIndex);

}
