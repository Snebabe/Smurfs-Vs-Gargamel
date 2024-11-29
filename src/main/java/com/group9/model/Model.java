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
            List<AttackEntity> attackers = lane.getAttackers();
            for (int cellIndex = 0; cellIndex < lane.getNumberOfCells(); cellIndex++) {
                // Get the defender at the current cell index
                DefenceEntity defender = lane.getDefender(cellIndex);

                // Skip if there's no defender in this cell
                if (defender == null) {
                    continue;
                }

                AttackEntity firstAttacker = attackers.get(0);
                float targetCellIndex = lane.getAttackerCellPosition(firstAttacker); // Convert progress to grid index

                // If the attacker is within the defender's attack range
                float distance = targetCellIndex - cellIndex;
                if (distance > 0 && distance <= defender.getAttackRange()) {
                    defender.useAttack(firstAttacker);
                }
            }

            // Handle melee attackers (stop moving and attack defenders in the same position)
            for (AttackEntity attacker : attackers) {
                int attackerCellIndex = (int) lane.getAttackerCellPosition(attacker);

                // Check if there's a defender at the same cell index
                DefenceEntity defender = lane.getDefender(attackerCellIndex);

                if (defender != null) {
                    // Stop the attacker's movement and attack the defender
                    attacker.useAttack(defender);
                } else {
                    attacker.move();
                }
            }
        }

    }

    public Map<Entity, Position> getAllEntityPositions() {
        Map<Entity, Position> entityPositions = new HashMap<>();

        // Iterate through each lane in the board
        for (Lane lane : board.getLanes()) {
            // Get the Y-coordinate of the lane based on its position and the board's cell size
            int laneIndex = lane.getLaneIndex(); // Assuming lanes have an index
            int laneY = /*board.getStartingPosition().getY()*/ 0 + laneIndex * board.getCellSize();

            // Process attackers in the lane
            for (AttackEntity attacker : lane.getAttackers()) {
                // Calculate X-coordinate based on lane progress
                int attackerX = (int) (/*board.getStartingPosition().getX()*/ + lane.getAttackerCellPosition(attacker) * board.getCellSize());

                // Add attacker and its position to the map
                entityPositions.put(attacker, new Position(attackerX, laneY));
            }

            // Process defenders in the lane
            for (int cellIndex = 0; cellIndex < lane.getNumberOfCells(); cellIndex++) {
                DefenceEntity defender = lane.getDefender(cellIndex);
                if (defender != null) {
                    // Calculate X-coordinate based on cell index
                    int defenderX = /*board.getStartingPosition().getX()*/ 0 + cellIndex * board.getCellSize();

                    // Add defender and its position to the map
                    entityPositions.put(defender, new Position(defenderX, laneY));
                }
            }
        }

        return entityPositions;
    }
}
