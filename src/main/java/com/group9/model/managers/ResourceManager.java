package com.group9.model.managers;

import com.group9.model.AttackDeathObserver;
import com.group9.model.WaveCompleteListener;
import com.group9.model.entities.attackers.AttackEntity;
import com.group9.model.entities.defenders.DefenceEntity;

public class ResourceManager implements AttackDeathObserver, WaveCompleteListener {
    private int startResources = 450;
    private int resources;

    public ResourceManager(){
        this.resources = startResources;
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
