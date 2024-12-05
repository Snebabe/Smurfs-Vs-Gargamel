package com.group9.model;

import com.group9.model.entities.defenders.DefenceEntity;

public class Player {
    private int resources;
    private DefenceEntity selectedDefence;

    Player(){
        this.resources = 300;
        this.selectedDefence = null;

    }

    public int getResources() {
        return resources;
    }
    public int changeResources(int resources) {
        return this.resources += resources;
    }

}
