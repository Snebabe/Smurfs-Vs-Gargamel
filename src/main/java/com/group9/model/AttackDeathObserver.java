package com.group9.model;

import com.group9.model.entities.attackers.AttackEntity;

public interface AttackDeathObserver {
    void onAttackerDeath(AttackEntity attackEntity);
}
