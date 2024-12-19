package com.group9.model.managers;

import com.group9.model.AttackDeathObserver;
import com.group9.model.WaveCompleteListener;
import com.group9.model.entities.characters.attackers.AttackEntity;

/*
 * Manages the player's resources. Tracks the resources available and updates
 * them when attackers die or waves are completed. Provides methods to change
 * and reset resources.
 */

public class ResourceManager implements AttackDeathObserver, WaveCompleteListener {
    private int startResources = 4500;
    private int resources;

    public ResourceManager(){
        this.resources = startResources;
    }

    public int getResources() {
        return resources;
    }

    // Reset resources to starting value
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
