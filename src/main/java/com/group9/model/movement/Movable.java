package com.group9.model.movement;

public interface Movable {
    float getLaneProgress();
    void setLaneProgress(float laneProgress);
    int getSpeed();
    MovementRule getMovementRule(); // New method to get the associated movement rule

}
