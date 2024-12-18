package com.group9.model.movement;

import com.group9.model.board.Lane;

// Interface to define movement rules for movable entities
// Determines if a movable entity can move in the given lane based on the rule
public interface MovementRule {
    boolean canMove(Movable movable, Lane lane);
}
