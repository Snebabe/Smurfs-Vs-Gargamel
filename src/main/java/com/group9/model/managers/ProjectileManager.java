package com.group9.model.managers;

import com.group9.model.Observer;
import com.group9.model.board.Board;
import com.group9.model.board.Lane;
import com.group9.model.entities.attackers.AttackEntity;
import com.group9.model.entities.projectiles.Projectile;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class ProjectileManager {
    private Board board;
    private final List<AttackDeathObserver> attackDeathObservers;

    public ProjectileManager(Board board) {
        this.attackDeathObservers = new ArrayList<>();
        this.board = board;
    }

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

                // Get the attacker in front
                AttackEntity target = lane.getAttackers().getFirst();
                if (target == null) {
                    break;
                }

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

    public void notifyAttackerDeath(AttackEntity attacker) {
        System.out.println("Reward: " + attacker.getResourceReward());
        for(AttackDeathObserver observer : attackDeathObservers) {
            observer.onAttackerDeath(attacker);
        }
    }
}