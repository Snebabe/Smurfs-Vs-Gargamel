package com.group9.controller;

import com.group9.model.entities.characters.defenders.DefenderType;
import com.group9.model.Model;
import com.group9.model.Position;

/**
 * The GameController class handles user input and sends update signals to the game model accordingly.
 * It implements the InputObserver interface to respond to various user actions.
 */
public class GameController implements InputObserver {

    private Model model;

    private DefenderType chosenDefender;

    /**
     * Constructs a GameController with the specified game model.
     *
     * @param model the game model to be sending updates signals to
     */
    public GameController(Model model) {
        this.model = model;
    }

    @Override
    public void onGridCellClicked(int row, int col) {
        Position clickedPosition = new Position(row, col);

        // Add a new defender to the model
        if (this.chosenDefender != null) {
            model.placeDefender(chosenDefender, clickedPosition);
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