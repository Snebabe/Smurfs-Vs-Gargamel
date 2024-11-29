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
            List<AttackEntity> attackers = lane.getAttackers();
            if(attackers.isEmpty()) {continue;}
            for (int cellIndex = 0; cellIndex < lane.getNumberOfCells(); cellIndex++) {
                // Get the defender at the current cell index
                DefenceEntity defender = lane.getDefenderAtIndex(cellIndex);

                // Skip if there's no defender in this cell
                if (defender == null) {
                    continue;
                }

                AttackEntity firstAttacker = attackers.get(0);
                float targetLaneProgress = firstAttacker.getLaneProgress(); // Lane progress as a value between 0 and 1
                float targetCellIndex = (1 - targetLaneProgress) * lane.getNumberOfCells(); // Convert progress to grid index

                // If the attacker is within the defender's attack range
                float distance = targetCellIndex - cellIndex;
                if (distance > 0 && distance <= defender.getAttackRange()) {
                    defender.useAttack(firstAttacker);
                }
            }

            // Handle melee attackers (stop moving and attack defenders in the same position)
            for (AttackEntity attacker : attackers) {
                int attackerCellIndex = (int) Math.floor(1 - attacker.getLaneProgress()) * lane.getNumberOfCells();

                // Check if there's a defender at the same cell index
                DefenceEntity defender = lane.getDefenderAtIndex(attackerCellIndex);

                if (defender != null) {
                    // Stop the attacker's movement and attack the defender
                    attacker.useAttack(defender);
                } else {
                    attacker.move();
                }
            }
        }
    }

    public void setDefender(DefenderType defender, int row, int col) {
        board.setDefender(new DefenceEntityFactory().createDefender(defender), row, col);
    }
}
