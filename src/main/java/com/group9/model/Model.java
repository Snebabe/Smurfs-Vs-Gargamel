package com.group9.model;

/*
 * Interface för vad en model måste ha
 * Om det är något varje model måste ha
 * idk
 */

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Model {
    // Ha ett board
    // Tillgång till varje cell
    // getters som: getBoard

    private Board board;
    private List<Entity> entities;

    Model() {
        this.board = new Board(5, 8, 100);
    }

    /*

     */
    public Map<Entity, Position> getAllEntityPositions() {
        Map<Entity, Position> map = new HashMap<Entity, Position>();
        for(Entity entity: entities) {
            map.put(entity, entity.getPosition());
        }

        return map;
    }
}
