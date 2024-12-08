package com.group9.model;

import com.group9.model.entities.attackers.AttackEntity;
import com.group9.model.entities.defenders.DefenceEntity;

public class ResourceManager implements AttackDeathObserver,  WaveCompleteListener {
    private int startResources = 100000;
    private int resources;
    private DefenceEntity selectedDefence;

    ResourceManager(){
        this.resources = startResources;
        this.selectedDefence = null;

    }

    public int getResources() {
        return resources;
    }
    public void resetResources() {
        this.resources = startResources;
    }

    public int changeResources(int resources) {
        return this.resources += resources;
    }


    @Override
    public void onWaveComplete(int waveReward) {
        changeResources(waveReward);
    }
    @Override
    public void onAttackerDeath(AttackEntity attackEntity) {
        changeResources(attackEntity.getResourceReward());
    }

}
