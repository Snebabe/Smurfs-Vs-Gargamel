package com.group9.model.managers;

import com.group9.model.Position;
import com.group9.model.board.Board;
import com.group9.model.entities.characters.defenders.DefenceEntity;
import com.group9.model.entities.characters.defenders.DefenceEntityFactory;
import com.group9.model.entities.characters.defenders.DefenderType;

/**
 * Manages the placement of defenders on the game board.
 */
public class DefenderManager {
    private final Board board;
    private final ResourceManager resourceManager;


    /**
     * Constructs a DefenderManager with the specified game board and resource manager.
     *
     * @param board the game board
     * @param resourceManager the resource manager
     */
    public DefenderManager(Board board, ResourceManager resourceManager) {
        this.board = board;
        this.resourceManager = resourceManager;
    }

    /**
     * Places a defender of the specified defender type at the given position on the board.
     * Checks if there are enough resources and if the position is free before placing the defender.
     *
     * @param defenderType the type of defender to place
     * @param position the position to place the defender at
     */
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