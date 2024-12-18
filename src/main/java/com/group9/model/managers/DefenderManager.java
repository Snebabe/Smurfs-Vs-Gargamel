package com.group9.model.managers;

import com.group9.model.Position;
import com.group9.model.board.Board;
import com.group9.model.entities.characters.defenders.DefenceEntity;
import com.group9.model.entities.characters.defenders.DefenceEntityFactory;
import com.group9.model.entities.characters.defenders.DefenderType;

public class DefenderManager {
    private Board board;
    private ResourceManager resourceManager;  // Reference to the resource manager for tracking available resources

    public DefenderManager(Board board, ResourceManager resourceManager) {
        this.board = board;
        this.resourceManager = resourceManager;
    }

    // Method to place a defender on the board at a specific position
    public void placeDefender(DefenderType defenderType, Position position) {
        // Check if there are enough resources and if the position is free
        if (resourceManager.getResources() >= defenderType.getCost() && !isDefenderAt(position)) {

            // Create a new defender and place it on the board
            DefenceEntity newDefenceEntity = DefenceEntityFactory.createDefender(defenderType);
            board.setDefender(newDefenceEntity, position.getRow(), position.getCol());

            // Deduct the cost of the defender from resources
            resourceManager.changeResources(-defenderType.getCost());
        } else if (isDefenderAt(position)) {
            // Notify if there is already a defender at the specified position
            System.out.println("Defender already in place");
        } else {
            // Notify if there are not enough resources
            System.out.println("Not enough money");
        }
    }

    public boolean isDefenderAt(Position position) {
        return board.getLanes().get(position.getRow()).getDefenderAtIndex(position.getCol()) != null;
    }
}