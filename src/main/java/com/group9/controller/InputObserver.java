package com.group9.controller;

import com.group9.model.entities.characters.defenders.DefenderType;


/**
 * The InputObserver interface defines the methods that must be implemented
 * by any class that wishes to respond to user input events in the game.
 */
public interface InputObserver {

    /**
     * Handles the event when a grid cell is clicked.
     *
     * @param row the row index of the clicked cell
     * @param col the column index of the clicked cell
     */
    void onGridCellClicked(int row, int col);

    /**
     * Handles the event when a defender is selected.
     *
     * @param type the selected defender type
     */
    void onDefenderSelected(DefenderType type);

    /**
     * Handles the event when the start wave button is clicked.
     */
    void onStartWaveClicked();

    /**
     * Handles the event when the reset game button is clicked.
     */
    void onResetGameClicked();
}
