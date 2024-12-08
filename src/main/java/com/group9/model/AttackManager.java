package com.group9.model;

import com.group9.model.entities.attackers.AttackEntity;
import com.group9.model.entities.defenders.DefenceEntity;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.Iterator;
import java.util.List;

public class AttackManager {
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
    public void executeAttackCycle() {
        for (Lane lane : board.getLanes()) {
            // Sort attackers in the lane by laneProgress
            lane.sortAttackers();
            handleDefenderAttacks(lane);
            handleMeleeAttacks(lane);
            updateProjectiles(lane);
        }
    }

    public void updateProjectiles(Lane lane) {
        Iterator<Projectile> iterator = lane.getProjectiles().iterator();
        while (iterator.hasNext()) {
            Projectile projectile = iterator.next();

            // Ensure the projectile has a valid target
            if (projectile.getTarget() == null || projectile.getTarget().isDead()) {
                if (!lane.getAttackers().isEmpty()) {
                    // Assign a new target, preferably the closest one
                    AttackEntity closestTarget = lane.getAttackers().stream()
                            .min(Comparator.comparingDouble(AttackEntity::getLaneProgress))
                            .orElse(null);
                    projectile.setTarget(closestTarget);
                } else {
                    // No targets available; deactivate the projectile
                    projectile.setTarget(null);
                    projectile.update();
                    iterator.remove();
                    continue;
                }
            }

            // Update the projectile
            projectile.update();

            // Remove inactive projectiles
            if (!projectile.isActive()) {
                iterator.remove();
            }
        }

        // Remove dead attackers only after all projectiles are processed
        lane.getAttackers().removeIf(AttackEntity::isDead);






        /*lane.getAttackers().removeIf(AttackEntity::isDead);
        Iterator<Projectile> iterator = lane.getProjectiles().iterator();
        while(iterator.hasNext()) {
            Projectile projectile = iterator.next();
            if(!lane.getAttackers().isEmpty()){
                projectile.setTarget(lane.getAttackers().get(lane.getAttackers().size()-1));
            }

            projectile.update();
            if(!projectile.isActive()) {
                iterator.remove();
            }
        }*/


        /*for(Projectile projectile : lane.getProjectiles()) {
            projectile.update();
            if(!projectile.isActive()) {
                lane.getProjectiles().remove(projectile);
            }
        }*/
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
                defender.useAttack(firstAttacker, lane.getProjectiles(), (float)cellIndex/(lane.getNumberOfCells()-1));
                if (firstAttacker.isDead()) {
                    lane.removeAttacker(firstAttacker);
                    notifyAttackerDeath(firstAttacker);
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
            // Get the defender at the attacker's position
            DefenceEntity defender = lane.getDefenderAtIndex(attackerCellIndex);

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
