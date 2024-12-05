package com.group9.model;

import com.group9.model.entities.attackers.AttackEntity;
import com.group9.model.entities.defenders.DefenceEntity;

import java.util.List;

public class AttackManager {
    private Board board;

    public AttackManager(Board board) {
        this.board = board;
    }

    public void executeAttackCycle() {
        for (Lane lane : board.getLanes()) {
            // Sort attackers in the lane by laneProgress
            lane.sortAttackers();
            handleDefenderAttacks(lane);
            handleMeleeAttacks(lane);
        }
    }

    private void handleDefenderAttacks(Lane lane) {
        List<AttackEntity> attackers = lane.getAttackers();
        if (attackers.isEmpty()) return;

        for (int cellIndex = 0; cellIndex < lane.getNumberOfCells(); cellIndex++) {
            DefenceEntity defender = lane.getDefenderAtIndex(cellIndex);

            if (defender == null) continue;

            AttackEntity firstAttacker = attackers.get(0);
            float targetLaneProgress = firstAttacker.getLaneProgress();
            float targetCellIndex = (1 - targetLaneProgress) * lane.getNumberOfCells();
            float distance = targetCellIndex - cellIndex;

            if (distance > 0 && distance <= defender.getAttackRange()) {
                defender.useAttack(firstAttacker);
                if (firstAttacker.isDead()) {
                    lane.removeAttacker(firstAttacker);
                    if (attackers.isEmpty()) break;
                }
            }
        }
    }

    private void handleMeleeAttacks(Lane lane) {
        List<AttackEntity> attackers = lane.getAttackers();

        for (AttackEntity attacker : attackers) {
            // Calculate the attacker's position on the grid
            int attackerCellIndex = (int) ((1 - attacker.getLaneProgress()) * lane.getNumberOfCells());

            // Ensure the attackerCellIndex is within bounds
            if (attackerCellIndex < 0 || attackerCellIndex >= lane.getNumberOfCells()) {
                attacker.move();
                continue;
            }
            System.out.println(attackerCellIndex);
            // Get the defender at the attacker's position
            DefenceEntity defender = lane.getDefenderAtIndex(attackerCellIndex);
            System.out.println(defender);

            if (defender != null) {
                // Attack the defender
                attacker.useAttack(defender);

                // Remove the defender if it's dead
                if (defender.isDead()) {
                    lane.setDefender(null, attackerCellIndex);
                }
            } else if (!lane.hasAttackerReachedDefender(attacker)) {
                // Move the attacker forward if no defender is present
                attacker.move();
            }
        }
    }
}
