package com.group9.controller;

import com.group9.model.entities.characters.defenders.DefenderType;
import com.group9.model.Model;
import com.group9.model.Position;

public class GameController implements InputObserver {

    public Model model;     // The model representing the game state

    // The currently selected defender type to place on the grid
    private DefenderType chosenDefender;


    public GameController(Model model) {
        this.model = model;
    }

    @Override
    public void onGridCellClicked(int row, int col) {

        // Converts the clicked cell's row and column into a Position object
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

    // Starts a new wave and logs the action
    @Override
    public void onStartWaveClicked() {
        System.out.println("Spawning Wave");
        model.startWave();
    }

    // Resets the game state and logs the action
    @Override
    public void onResetGameClicked() {
        System.out.println("Resetting game...");
        model.resetGame();
    }
}
