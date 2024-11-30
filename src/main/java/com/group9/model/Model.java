package com.group9.model;

/*
 * Interface för vad en model måste ha
 * Om det är något varje model måste ha
 * idk
 */

import java.util.*;

public class Model {
    // Ha ett board
    // Tillgång till varje cell
    // getters som: getBoard

    private Board board;
    private List<Entity> entities;

    public Model() {
        this.board = new Board(5, 9, 100);
    }

    // Temporary function to spawn random attacker
    public void spawnAttackerRandomly() {
        Random random = new Random();
        List<Lane> lanes = board.getLanes();
        // Choose a random lane from the list of lanes
        int randomLaneIndex = random.nextInt(lanes.size());
        Lane selectedLane = lanes.get(randomLaneIndex);

        // Add the attacker to the list of attackers in the chosen lane
        selectedLane.addAttacker(new AttackGargamel(100,10,1, 1));
    }

    public Map<String, Position> getAllAttackersPosition() {
        Map<String, Position> map = new HashMap<>();
        for (int row = 0; row < board.getLaneAmount(); row++) {
            Lane lane = board.getLanes().get(row);
            for (AttackEntity attacker : lane.getAttackers()) {
                float colPosition = (1 - attacker.getLaneProgress()) * lane.getNumberOfCells();
                int xPos = (int) (colPosition * board.getCellSize());
                int yPos = row * board.getCellSize();
                map.put("Attacker", new Position(xPos, yPos));
            }
        }
        return map;
    }

    public Map<String, Position> getAllDefendersPosition() {
        Map<String, Position> map = new HashMap<>();
        for (int row = 0; row < board.getLaneAmount(); row++) {
            Lane lane = board.getLanes().get(row);
            for (int col = 0; col < lane.getNumberOfCells(); col++) {
                GridCell cell = lane.getGridCells().get(col);
                if (cell.hasDefender()) {
                    map.put("shroom", new Position(row, col));
                }
            }
        }
        return map;
    }

    public void update() {
        //attackCycle();
        moveCycle();
        /*for(Lane lane: board.getLanes()) {
            lane.updateAttackers();
        }*/
    }

    public void moveCycle() {
        for (Lane lane : board.getLanes()) {
            for (AttackEntity attacker : lane.getAttackers()) {
                // If attacker has not reached defender and it has not surpassed the goal
                boolean attackerInsideGridSpace = (1 - attacker.getLaneProgress()) * lane.getNumberOfCells() > 0;
                if (attackerInsideGridSpace && !lane.hasAttackerReachedDefender(attacker)) {
                    attacker.move();
                }
            }
        }
    }

    public void attackCycle() {
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

                // Check if attacker is inside grid space
                if (attackerCellIndex > lane.getNumberOfCells()-1) { continue; }

                // Check if there's a defender at the same cell index
                DefenceEntity defender = lane.getDefenderAtIndex(attackerCellIndex);

                if (defender != null) {
                    // Stop the attacker's movement and attack the defender
                    attacker.useAttack(defender);
                }
            }
        }
    }

    public void setDefender(DefenderType defender, int row, int col) {
        board.setDefender(new DefenceEntityFactory().createDefender(defender), row, col);
    }
}
