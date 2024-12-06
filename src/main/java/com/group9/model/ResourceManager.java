package com.group9.model;

import com.group9.model.entities.attackers.AttackEntity;

public class ResourceManager implements AttackDeathObserver {
    private int resources;

    ResourceManager(){
        this.resources = 100000;

    }

    public int getResources() {
        return resources;
    }
    public int changeResources(int resources) {
        return this.resources += resources;
    }

    @Override
    public void onAttackerDeath(AttackEntity attackEntity) {
        changeResources(attackEntity.getResourceReward());
    }
}
