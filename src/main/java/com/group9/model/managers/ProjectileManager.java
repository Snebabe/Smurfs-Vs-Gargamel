package com.group9.model.managers;

import com.group9.model.AttackDeathObserver;
import com.group9.model.board.Board;
import com.group9.model.board.Lane;
import com.group9.model.entities.characters.attackers.AttackEntity;
import com.group9.model.entities.projectiles.Projectile;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;


/**
 * Manages the behavior and interactions of projectiles on the game board.
 * Handles projectile collisions with attackers and updates their status (damage, removal).
 */
public class ProjectileManager {
    private final Board board;
    private final List<AttackDeathObserver> attackDeathObservers;

    /**
     * Constructs a ProjectileManager with the specified game board.
     *
     * @param board the game board
     */
    public ProjectileManager(Board board) {
        attackDeathObservers = new ArrayList<>();
        this.board = board;
    }

    /**
     * Handles projectile collisions with attackers for all lanes on the board.
     */
    public void handleProjectilesCollision() {
        for (Lane lane : board.getLanes()) {
            handleLaneProjectiles(lane);
        }
    }

    /**
     * Helper method to handle projectile collisions with attackers for a specific lane.
     *
     * @param lane the lane to handle projectiles for
     */
    private void handleLaneProjectiles(Lane lane) {
        List<Projectile> projectiles = lane.getProjectiles();
        Iterator<Projectile> projectileIterator = projectiles.iterator();

        while (projectileIterator.hasNext()) {
            Projectile projectile = projectileIterator.next();

            if (isProjectileOutOfRange(projectile, lane)) {
                removeProjectile(projectileIterator, lane, projectile);
                continue;
            }

            if (lane.getAttackers().isEmpty()) {
                break;
            }

            handleProjectileCollisionWithAttacker(projectileIterator, lane, projectile);
        }
    }

    /**
     * Helper method to handle the collision of a projectile with a specific attacker in a specific lane.
     *
     * @param projectileIterator the iterator for the projectiles
     * @param lane the lane the projectile is in
     * @param projectile the projectile that collided with an attacker
     */
    private void handleProjectileCollisionWithAttacker(Iterator<Projectile> projectileIterator, Lane lane, Projectile projectile) {
        AttackEntity target = lane.getAttackers().getFirst();
        float distance = (1 - target.getLaneProgress()) - projectile.getLaneProgress();

        if (distance < 0.05) {
            removeProjectile(projectileIterator, lane, projectile);
            target.takeDamage(projectile.getDamage());

            if (target.isDead()) {
                notifyAttackerDeath(target);
                lane.removeAttacker(target);
            }
        }
    }

    /**
     * Helper method to check if a projectile is out of range in a specific lane.
     *
     * @param projectile the projectile to check
     * @param lane the lane the projectile is in
     * @return true if the projectile is out of range, false otherwise
     */
    private boolean isProjectileOutOfRange(Projectile projectile, Lane lane) {
        float laneProgressTraveled = projectile.getLaneProgress() - projectile.getStartingLaneProgress();
        float cellRangeInLaneProgress = (float) projectile.getRange() / lane.getNumberOfCells(); // Convert cell range in terms of lane progress
        return projectile.getLaneProgress() >= 1 || laneProgressTraveled > cellRangeInLaneProgress;
    }

    /**
     * Helper method to remove a projectile from a lane.
     *
     * @param projectileIterator the iterator for the projectiles
     * @param lane the lane the projectile is in
     * @param projectile the projectile to remove
     */
    private void removeProjectile(Iterator<Projectile> projectileIterator, Lane lane, Projectile projectile) {
        projectileIterator.remove();
        lane.removeProjectile(projectile);
    }

    public void addAttackDeathObserver(AttackDeathObserver observer) {
        attackDeathObservers.add(observer);
    }

    public void notifyAttackerDeath(AttackEntity attacker) {
        System.out.println("Reward: " + attacker.getResourceReward());
        for(AttackDeathObserver observer : attackDeathObservers) {
            observer.onAttackerDeath(attacker);
        }
    }
}