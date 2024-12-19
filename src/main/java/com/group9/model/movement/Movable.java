package com.group9.model.movement;

// Interface for entities that can move in the game
// Provides methods to track progress, set speed, and get movement rules

public interface Movable {

    float getLaneProgress();
    void setLaneProgress(float laneProgress);
    int getSpeed();
    MovementRule getMovementRule(); // New method to get the associated movement rule

}
