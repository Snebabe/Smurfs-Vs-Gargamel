package com.group9.model.managers;

import com.group9.model.*;
import com.group9.model.board.Board;
import com.group9.model.board.Lane;
import com.group9.model.entities.Projectile;
import com.group9.model.entities.attackers.AttackEntity;
import com.group9.model.entities.defenders.DefenceEntity;

import java.util.ArrayList;
import java.util.List;

public class AttackManager implements Observer {
    private Board board;
    private List<AttackDeathObserver> attackDeathObservers;

    public AttackManager(Board board) {
        this.board = board;
        this.attackDeathObservers = new ArrayList<>();
    }

    public void addAttackDeathOberver(AttackDeathObserver observer) {
        attackDeathObservers.add(observer);
    }

    public void removeObserver(AttackDeathObserver observer) {
        attackDeathObservers.remove(observer);
    }

    public void notifyAttackerDeath(AttackEntity attacker) {
        for(AttackDeathObserver observer : attackDeathObservers) {
            observer.onAttackerDeath(attacker);
        }
    }


    public void resetBoard(Board board) {
        this.board = board;
    }

    @Override
    public void update() {
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
                if (defender.isRanged()) {
                    Projectile projectile = new Projectile((float)cellIndex/(lane.getNumberOfCells()-1), firstAttacker, 4, defender.getAttackDamage());
                    lane.getProjectiles().add(projectile);
                    board.addMovable(projectile, lane);
                } else {
                    defender.useAttack(firstAttacker);
                }
                if (firstAttacker.isDead()) {
                    lane.removeAttacker(firstAttacker);
                    notifyAttackerDeath(firstAttacker);
                    if (attackers.isEmpty()) break;
                }
            }
        }
    }

    private void handleMeleeAttacks(Lane lane) {

        for (AttackEntity attacker : lane.getAttackers()) {
            int attackerCellIndex = (int) ((1 - attacker.getLaneProgress()) * lane.getNumberOfCells());
            // Get the defender at the attacker's position
            DefenceEntity defender = lane.getDefenderAtIndex(attackerCellIndex);

            if (defender != null) {
                // Attack the defender
                attacker.useAttack(defender);

                // Remove the defender if it's dead
                if (defender.isDead()) {
                    lane.setDefender(null, attackerCellIndex);
                }
            }
        }
    }
}
