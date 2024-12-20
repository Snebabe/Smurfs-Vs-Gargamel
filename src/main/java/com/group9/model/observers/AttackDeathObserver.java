package com.group9.model.observers;

import com.group9.model.entities.characters.attackers.AttackEntity;


/**
 * Observer interface for handling events when an attacker dies.
 */
public interface AttackDeathObserver {

    /**
     * Handles the event when an attacker dies.
     *
     * @param attackEntity the attacker that died
     */
    void onAttackerDeath(AttackEntity attackEntity);
}
