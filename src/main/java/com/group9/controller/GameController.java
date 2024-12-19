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
     * @param model the game model to be controlled
     */
    public GameController(Model model) {
        this.model = model;
    }

    /**
     * Handles the event when a grid cell is clicked.
     * If a defender type is selected, place the defender at the clicked position in the grid.
     *
     * @param row the row index of the clicked cell
     * @param col the column index of the clicked cell
     */
    @Override
    public void onGridCellClicked(int row, int col) {
        Position clickedPosition = new Position(row, col);

        // Add a new defender to the model
        if (this.chosenDefender != null) {
            model.placeDefender(chosenDefender, clickedPosition);
        }
    }

    /**
     * Handles the event when a defender is selected.
     * Sets the chosen defender type to the selected defender.
     *
     * @param defender the selected defender type
     */
    @Override
    public void onDefenderSelected(DefenderType defender) {
        this.chosenDefender = defender;
    }

    /**
     * Handles the event when the start wave button is clicked.
     * Initiates the spawning of a new wave in the game model.
     */
    @Override
    public void onStartWaveClicked() {
        System.out.println("Spawning Wave");
        model.startWave();
    }

    /**
     * Handles the event when the reset game button is clicked.
     * Resets the game model to its initial state.
     */
    @Override
    public void onResetGameClicked() {
        System.out.println("Resetting game...");
        model.resetGame();
    }
}