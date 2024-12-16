package com.group9.model.managers;

import com.group9.model.Position;
import com.group9.model.board.Board;
import com.group9.model.entities.defenders.DefenceEntity;
import com.group9.model.entities.defenders.DefenceEntityFactory;
import com.group9.model.entities.defenders.DefenderType;

public class DefenderManager {
    private Board board;
    private ResourceManager resourceManager;

    public DefenderManager(Board board, ResourceManager resourceManager) {
        this.board = board;
        this.resourceManager = resourceManager;
    }

    public void placeDefender(DefenderType defenderType, Position position) {
        if (resourceManager.getResources() >= defenderType.getCost() && !isDefenderAt(position)) {

            DefenceEntity newDefenceEntity = DefenceEntityFactory.createDefender(defenderType, (float)position.getCol()/(board.getLaneSize()));
            board.setDefender(newDefenceEntity, position.getRow(), position.getCol());
            resourceManager.changeResources(-defenderType.getCost());
        } else if (isDefenderAt(position)) {
            System.out.println("Defender already in place");
        } else {
            System.out.println("Not enough money");
        }
    }

    public boolean isDefenderAt(Position position) {
        return board.getLanes().get(position.getRow()).getDefenderAtIndex(position.getCol()) != null;
    }
}