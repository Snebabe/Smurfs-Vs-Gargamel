package com.group9.controller;

import com.group9.model.entities.characters.defenders.DefenderType;


// Interface defining input events for the game.

public interface InputObserver {

    void onGridCellClicked(int row, int col);   // Handles grid cell click events.
    void onDefenderSelected(DefenderType type); // Handles defender selection events.
    void onStartWaveClicked();  // Starts a wave.
    void onResetGameClicked();  // Resets the game.
}
