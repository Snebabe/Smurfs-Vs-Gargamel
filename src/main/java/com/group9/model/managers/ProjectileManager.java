package com.group9.model.managers;

import com.group9.model.AttackDeathObserver;
import com.group9.model.board.Board;
import com.group9.model.board.Lane;
import com.group9.model.entities.characters.attackers.AttackEntity;
import com.group9.model.entities.projectiles.Projectile;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;


/*
 * Manages the behavior and interactions of projectiles on the game board.
 * Handles projectile collisions with attackers and updates their status (damage, removal).
 */

public class ProjectileManager {
    private final Board board;
    private final List<AttackDeathObserver> attackDeathObservers;

    public ProjectileManager(Board board) {
        this.attackDeathObservers = new ArrayList<>();
        this.board = board;
    }

    // Handle projectile collisions with attackers
    public void handleProjectilesCollision() {
        for (Lane lane : this.board.getLanes()) {
            Iterator<Projectile> projectileIterator = lane.getProjectiles().iterator();

            while (projectileIterator.hasNext()) {
                Projectile projectile = projectileIterator.next();

                // If projectile out of range (Needs testing)
                float laneProgressTraveled = projectile.getLaneProgress() - projectile.getStartingLaneProgress();
                float cellRangeInLaneProgress = (float) projectile.getRange() / lane.getNumberOfCells();
                if (projectile.getLaneProgress() >= 1 || laneProgressTraveled > cellRangeInLaneProgress) {
                    lane.getMovables().remove(projectile);
                    projectileIterator.remove();
                    continue;
                }

                // Check if attackers list is empty before accessing the first attacker
                if (lane.getAttackers().isEmpty()) {
                    break;
                }

                // Get the attacker in front
                AttackEntity target = lane.getAttackers().getFirst();

                // If projectile hit attacker
                float distance = (1 - target.getLaneProgress()) - projectile.getLaneProgress();
                if (distance < 0.05) {
                    lane.getMovables().remove(projectile);
                    projectileIterator.remove();

                    target.takeDamage(projectile.getDamage());
                    if (target.isDead()) {
                        notifyAttackerDeath(target);
                        lane.removeAttacker(target);
                    }
                }
            }
        }
    }

    // Add observer for attacker death
    public void addAttackDeathOberver(AttackDeathObserver observer) {
        attackDeathObservers.add(observer);
    }

    // Notify observers about attacker death
    public void notifyAttackerDeath(AttackEntity attacker) {
        System.out.println("Reward: " + attacker.getResourceReward());
        for(AttackDeathObserver observer : attackDeathObservers) {
            observer.onAttackerDeath(attacker);
        }
    }
}