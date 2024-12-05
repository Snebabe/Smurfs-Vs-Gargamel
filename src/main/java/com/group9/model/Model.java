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
    private WaveManager waveManager;
    private List<WaveCompleteListener> listeners;

    public Model() {
        this.board = new Board(5, 9, 100);
        this.waveManager = new WaveManager(new AttackEntityFactory(), board);
        listeners = new ArrayList<>();
    }

    // Observer management
    public void addWaveCompleteListener(WaveCompleteListener listener) {
        listeners.add(listener);
    }

    public void removeWaveCompleteListener(WaveCompleteListener listener) {
        listeners.remove(listener);
    }

    // Notify observers when a wave is complete
    private void notifyWaveComplete() {
        for (WaveCompleteListener listener : listeners) {
            listener.onWaveComplete();
        }
    }


    public Map<AttackEntity, Position> getAllAttackersPosition() {
        Map<AttackEntity, Position> map = new HashMap<>();
        for (int row = 0; row < board.getLaneAmount(); row++) {
            Lane lane = board.getLanes().get(row);
            for (AttackEntity attacker : lane.getAttackers()) {
                int col = (int) ((1 - attacker.getLaneProgress()) * lane.getNumberOfCells());
                map.put(attacker, new Position(row, col));
            }
        }
        return map;
    }

    public Map<DefenceEntity, Position> getAllDefendersPosition() {
        Map<DefenceEntity, Position> map = new HashMap<>();
        for (int row = 0; row < board.getLaneAmount(); row++) {
            Lane lane = board.getLanes().get(row);
            for (int col = 0; col < lane.getNumberOfCells(); col++) {
                GridCell cell = lane.getGridCells().get(col);
                if (cell.hasDefender()) {
                    map.put(cell.getDefender(), new Position(row, col));
                }
            }
        }
        return map;
    }

    public void update() {
        //attackCycle();

        // Checks if attacker needs to be spawned
        waveManager.update();
        attackCycle();
        checkWaveCleared();
        /*for(Lane lane: board.getLanes()) {
            lane.updateAttackers();
        }*/
    }

    private void checkWaveCleared() {
        boolean allAttackersCleared = true;
        for (Lane lane : board.getLanes()) {
            if (!lane.getAttackers().isEmpty()) {
                allAttackersCleared = false;
                break;
            }
        }
        if (allAttackersCleared) {
            notifyWaveComplete();
        }
    }

    public WaveManager getWaveManager() {
        return waveManager;
    }

    public void startWave() {
        waveManager.startWave();
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
                    if (firstAttacker.isDead()) {
                        lane.removeAttacker(firstAttacker);
                        // Check if still attackers alive, otherwise break
                        if(attackers.isEmpty()) {break;}
                    }
                }
            }

            // Handle melee attackers (stop moving and attack defenders in the same position)
            for (AttackEntity attacker : attackers) {
                int attackerCellIndex = (int) Math.abs(Math.floor((1 - attacker.getLaneProgress()) * lane.getNumberOfCells()));

                // Check if attacker is outside grid space
                if (attackerCellIndex > lane.getNumberOfCells()-1) {
                    attacker.move();
                    continue;
                }

                // Check if there's a defender at the same cell index
                DefenceEntity defender = lane.getDefenderAtIndex(attackerCellIndex);
                boolean attackerInsideGridSpace = (1 - attacker.getLaneProgress()) * lane.getNumberOfCells() > 0;

                //System.out.println(attackerCellIndex);
                //System.out.println(defender);

                if (defender != null) {
                    // Stop the attacker's movement and attack the defender
                    attacker.useAttack(defender);
                    if (defender.isDead()) {
                        lane.setDefender(null, attackerCellIndex);
                    }
                } else if (attackerInsideGridSpace && !lane.hasAttackerReachedDefender(attacker)){
                    attacker.move();
                }
            }
        }
    }

    public void setDefender(DefenderType defender, int row, int col) {
        board.setDefender(new DefenceEntityFactory().createDefender(defender), row, col);
    }

    public boolean isDefenderAt(Position position) {
        return getAllDefendersPosition().containsValue(position);
    }
}
