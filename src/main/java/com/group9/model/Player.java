package com.group9.model;

import com.group9.model.entities.defenders.DefenceEntity;

public class Player implements WaveCompleteListener{
    private int resources;
    private DefenceEntity selectedDefence;
    private int startResources = 450;

    Player(){
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
}
