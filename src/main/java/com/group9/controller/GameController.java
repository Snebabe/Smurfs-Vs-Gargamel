package com.group9.controller;

import com.group9.model.Observer;
import com.group9.model.entities.defenders.DefenderType;
import com.group9.model.Model;
import com.group9.model.Position;

public class GameController implements InputObserver {

    public Model model;
    private DefenderType chosenDefender;

    public GameController(Model model) {
        this.model = model;
    }

    @Override
    public void onGridCellClicked(int row, int col) {
        Position clickedPosition = new Position(row, col);

        // Check if the position is already occupied
        /*if (model.isDefenderAt(clickedPosition)) {
            System.out.println("Position already occupied!");
            return;
        }*/

        // Add a new defender to the model
        if (this.chosenDefender != null) {
            model.placeDefender(chosenDefender, clickedPosition);
            //model.setDefender(this.chosenDefender, row, col);
        }
    }

    @Override
    public void onDefenderSelected(DefenderType defender) {
        this.chosenDefender = defender;
    }

    @Override
    public void onStartWaveClicked() {
        System.out.println("Spawning Wave");
        model.startWave();
    }

    @Override
    public void onResetGameClicked() {
        System.out.println("Resetting game...");
        model.resetGame();
    }
}
