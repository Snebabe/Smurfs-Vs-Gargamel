package com.group9.controller;

import com.group9.model.entities.defenders.DefenderType;

public interface InputObserver {

    void onGridCellClicked(int row, int col);
    void onDefenderSelected(DefenderType type);
    void onStartWaveClicked();
    void onResetGameClicked();
}
