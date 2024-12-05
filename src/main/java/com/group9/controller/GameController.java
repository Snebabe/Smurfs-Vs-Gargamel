package com.group9.controller;

import com.group9.model.entities.defenders.DefenderType;
import com.group9.model.Model;
import com.group9.model.Position;

public class GameController implements iController {

    public Model model;
    private DefenderType chosenDefender;

    public GameController(Model model) {
        this.model = model;
    }

    public void handleCellClick(int row, int col) {
        Position clickedPosition = new Position(row, col);

        // Check if the position is already occupied
        if (model.isDefenderAt(clickedPosition)) {
            System.out.println("Position already occupied!");
            return;
        }

        // Add a new defender to the model
        if (this.chosenDefender != null) {
            model.setDefender(this.chosenDefender, row, col);
        }

        // Notify observers (GamePanel) to update the view
        //model.notifyObservers();
    }

    public void handleDefenderClick(DefenderType defender) {
        this.chosenDefender = defender;
    }
}
