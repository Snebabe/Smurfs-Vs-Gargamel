package com.group9.model;

import com.group9.model.entities.defenders.DefenceEntity;

public class Player {
    private int resources;
    private DefenceEntity selectedDefence;
    private int currency;

    Player(){
        this.resources = 0;
        this.selectedDefence = null;
        this.currency = 0;
    }

    public int getCurrency() {
        return currency;
    }
    public int changeCurrency(int change) {
        this.currency += change;
        return change;
    }

}
