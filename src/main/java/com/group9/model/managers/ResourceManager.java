package com.group9.model.managers;

import com.group9.model.observers.AttackDeathObserver;
import com.group9.model.observers.WaveCompleteObserver;
import com.group9.model.entities.characters.attackers.AttackEntity;

/**
 * Manages the player's resources. Tracks the resources available and updates
 * them when attackers die or waves are completed. Provides methods to change
 * and reset resources.
 */
public class ResourceManager implements AttackDeathObserver, WaveCompleteObserver {
    private final int startResources = 500;
    private int resources;

    public ResourceManager(){
        resources = startResources;
    }

    public int getResources() {
        return resources;
    }

    public void resetResources() {
        resources = startResources;
    }

    public void changeResources(int resources) {
        this.resources += resources;
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
