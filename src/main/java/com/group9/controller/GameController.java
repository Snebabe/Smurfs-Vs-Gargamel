package com.group9.controller;

import com.group9.model.DefenderType;
import com.group9.model.Model;

public class GameController implements iController {

    public Model model;
    private DefenderType chosenDefender;

    public GameController(Model model) {
        this.model = model;
    }

    public void handleCellClick(int row, int col) {
        model.setDefender(this.chosenDefender, row, col);
    }

    public void handleDefenderClick(DefenderType defender) {
        this.chosenDefender = defender;
    }
}
