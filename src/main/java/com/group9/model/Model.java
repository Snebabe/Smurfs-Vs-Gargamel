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

    public Model() {
        this.board = new Board(5, 8, 100);
    }

    public void update() {
        for(Lane lane: board.getLanes()) {
            lane.updateAttackers();
        }

    }

    public void attackLoop() {
        // Iterate over each lane in the board
        for (Lane lane : board.getLanes()) {
            // Sort attackers in the lane by laneProgress
            lane.sortAttackers();

            // Get the sorted list of attackers
            List<Attacker> attackers = lane.getAttackers();
            for (int cellIndex = 0; cellIndex < lane.getNumberOfCells(); cellIndex++) {
                // Get the defender at the current cell index
                DefenceEntity defender = lane.getDefender(cellIndex);

                // Skip if there's no defender in this cell
                if (defender == null) {
                    continue;
                }

                int targetLaneProgress = attacker[0].getLaneProgress(); // Lane progress as a value between 0 and 1
                int targetCellIndex = (1 - targetLaneProgress) * lane.getNumberOfCells(); // Convert progress to grid index

                // If the attacker is within the defender's attack range
                int distance = targetCellIndex - cellIndex;
                if (distance > 0 && distance <= defender.getAttackRange()) {
                    defender.useAttack(attacker[0]);
                }
            }

            // Handle melee attackers (stop moving and attack defenders in the same position)
            for (AttackEntity attacker : attackers) {
                int attackerCellIndex = Math.floor(1 - attacker.getLaneProgress()) * lane.getSize());

                // Check if there's a defender at the same cell index
                DefenceEntity defender = lane.getDefender(attackerCellIndex);

                if (defender != null) {
                    // Stop the attacker's movement and attack the defender
                    attacker.attack(defender);
                } else {
                    attacker.move();
                }
            }
        }

    }

    public Map<Entity, Position> getAllEntityPositions() {
        Map<Entity, Position> map = new HashMap<Entity, Position>();
        for(Entity entity: entities) {
            map.put(entity, entity.getPosition());
        }

        return map;
    }
}
