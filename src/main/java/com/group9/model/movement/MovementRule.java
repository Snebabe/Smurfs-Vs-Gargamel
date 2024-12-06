package com.group9.model.movement;

import com.group9.model.board.Lane;

public interface MovementRule {
    boolean canMove(Movable movable, Lane lane);
}
