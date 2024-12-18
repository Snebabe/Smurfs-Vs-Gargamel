package com.group9.model;

import com.group9.model.entities.characters.attackers.AttackEntity;


/*
 * Observer interface for handling events when an attacker dies.
 * Used in the observer pattern to notify relevant parts of the game system when an attacker is destroyed.
 */

public interface AttackDeathObserver {

    // This method is called when an attacker dies, allowing observers to react
    void onAttackerDeath(AttackEntity attackEntity);
}
