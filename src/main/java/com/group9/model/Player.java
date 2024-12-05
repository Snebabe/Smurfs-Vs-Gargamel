package com.group9.model;

import com.group9.model.entities.defenders.DefenceEntity;

public class Player {
    private int resources;
    private DefenceEntity selectedDefence;

    Player(){
        this.resources = 0;
        this.selectedDefence = null;
    }

}
