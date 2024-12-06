package com.group9.model;

import com.sun.source.tree.UsesTree;

public interface Movable {
    float getLaneProgress();
    void setLaneProgress(float laneProgress);
    int getSpeed();
    MovementRule getMovementRule(); // New method to get the associated movement rule

}
