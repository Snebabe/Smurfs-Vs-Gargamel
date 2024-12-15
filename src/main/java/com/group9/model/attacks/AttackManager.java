package com.group9.model.attacks;

import com.group9.model.*;
import com.group9.model.board.Board;
import com.group9.model.board.Lane;
import com.group9.model.entities.Entity;
import com.group9.model.entities.EntityState;
import com.group9.model.entities.attackers.AttackEntity;
import com.group9.model.entities.defenders.DefenceEntity;
import com.group9.model.entities.projectiles.Projectile;

import java.util.ArrayList;
import java.util.Iterator;
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
        System.out.println("Reward: " + attacker.getResourceReward());
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

        // Loop through all defenders in the lane
        for (int cellIndex = 0; cellIndex < lane.getNumberOfCells(); cellIndex++) {
            DefenceEntity defender = lane.getDefenderAtIndex(cellIndex);

            if (defender == null) continue;

            if (attackers.isEmpty()) {
                setAllDefendersToIDLE(lane);
                return;
            }

            defender.useAttack(lane, cellIndex);
            //TODO: Fix this
            Iterator<AttackEntity> iterator = attackers.iterator();
            while (iterator.hasNext()) {
                AttackEntity target = iterator.next();
                if (target.isDead()) {
                    iterator.remove(); // Safely remove the element
                    lane.removeAttacker(target); // Ensure the lane's state is updated
                    notifyAttackerDeath(target);
                }
            }

        }
    }

    private void setAllDefendersToIDLE(Lane lane) {
        for (int cellIndex = 0; cellIndex < lane.getNumberOfCells(); cellIndex++) {
            DefenceEntity defender = lane.getDefenderAtIndex(cellIndex);
            if (defender != null) {
                defender.setCurrentState(EntityState.IDLE);
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
                attacker.setCurrentState(EntityState.ATTACK);
                attacker.useAttack(defender);

                // Remove the defender if it's dead
                if (defender.isDead()) {
                    attacker.setCurrentState(EntityState.MOVE);
                    lane.setDefender(null, attackerCellIndex);
                }
            }
        }
    }
}
