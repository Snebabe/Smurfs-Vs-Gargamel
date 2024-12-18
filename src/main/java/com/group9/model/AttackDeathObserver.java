package com.group9.model;

import com.group9.model.entities.characters.attackers.AttackEntity;

public interface AttackDeathObserver {
    void onAttackerDeath(AttackEntity attackEntity);
}
