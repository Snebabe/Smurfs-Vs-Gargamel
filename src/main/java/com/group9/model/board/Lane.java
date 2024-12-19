package com.group9.model.board;
import com.group9.model.entities.projectiles.Projectile;
import com.group9.model.entities.characters.attackers.AttackEntity;
import com.group9.model.entities.characters.defenders.DefenceEntity;
import com.group9.model.movement.Movable;

import java.util.ArrayList;
import java.util.List;

/**
 * The Lane class represents a single lane on the game board.
 * It contains a list of grid cells, attackers, and projectiles.
 */
public class Lane {

    private final List<AttackEntity> attackEntities;
    private final List<GridCell> gridCells;
    private final List<Projectile> projectiles;
    private final List<Movable> movables; // List of all movable entities (attackers and projectiles)

    /**
     * Initializes the lane with a specified number of cells.
     *
     * @param laneSize the number of cells in the lane
     */
    public Lane(int laneSize) {
        attackEntities = new ArrayList<>();
        projectiles = new ArrayList<>();
        movables = new ArrayList<>();

        // Initialize grid cells based on lane size
        gridCells = new ArrayList<>();
        for (int cellIndex = 0; cellIndex < laneSize; cellIndex++) {
            gridCells.add(new GridCell());
        }
    }

    /**
     * Adds an attacker to the lane and marks it as movable.
     *
     * @param attackEntity the attacker to add
     */
    public void addAttacker(AttackEntity attackEntity) {
        attackEntities.add(attackEntity);
        movables.add(attackEntity);
    }

    /**
     * Adds a projectile to the lane and marks it as movable.
     *
     * @param projectile the projectile to add
     */
    public void addProjectile(Projectile projectile) {
        projectiles.add(projectile);
        movables.add(projectile);
    }

    /**
     * Removes an attacker from the lane and the movables list.
     *
     * @param attackEntity the attacker to remove
     */
    public void removeAttacker(AttackEntity attackEntity) {
        attackEntities.remove(attackEntity);
        movables.remove(attackEntity);
    }

    /**
     * Removes a projectile from the lane and the movables list.
     *
     * @param projectile the projectile to remove
     */
    public void removeProjectile(Projectile projectile) {
        projectiles.remove(projectile);
        movables.remove(projectile);
    }

    /**
     * Adds a defender to the specified grid cell in the lane.
     *
     * @param defender the defender to add
     * @param col the column index of the grid cell
     */
    public void setDefender(DefenceEntity defender, int col) {
        GridCell cell = this.gridCells.get(col);
        cell.setDefender(defender);
    }

    /**
     * Sorts attackers based on their progress in the lane using Insertion Sort.
     * A margin is used to prevent swapping if the attackers' progress is very close.
     */
    public void sortAttackers() {
        final double MARGIN = 0.001; // Margin to avoid minor differences in lane progres

        for (int i = 1; i < attackEntities.size(); i++) {
            AttackEntity key = attackEntities.get(i);
            int j = i - 1;

            // Move attackers ahead of the key to the right if they are behind
            while (j >= 0 && shouldMoveForward(attackEntities.get(j), key, MARGIN)) {
                attackEntities.set(j + 1, attackEntities.get(j));
                j--;
            }
            // Place the key in its correct position
            attackEntities.set(j + 1, key);
        }
    }

    /**
     * Helper function to determine if an attacker should move ahead based on lane progress.
     *
     * @param current the current attacker
     * @param key the attacker to compare against
     * @param margin the margin to avoid minor differences
     * @return true if the current attacker should move forward, false otherwise
     */
    private boolean shouldMoveForward(AttackEntity current, AttackEntity key, double margin) {
        return current.getLaneProgress() + margin < key.getLaneProgress();
    }

    /**
     * Gets the defender at the specified index in the lane.
     *
     * @param index the index of the grid cell
     * @return the defender at the specified index, or null if none
     */
    public DefenceEntity getDefenderAtIndex(int index) {
        GridCell gridcell = gridCells.get(index);
        if (gridcell != null) {
            return gridCells.get(index).getDefender(); // Return the defender, if present
        }
        return null;
    }

    public List<Projectile> getProjectiles() {
        return new ArrayList<>(projectiles);
    }
    public List<GridCell> getGridCells() {
        return new ArrayList<>(gridCells);
    }

    public List<AttackEntity> getAttackers() {
        return new ArrayList<>(attackEntities);
    }
    public List<Movable> getMovables() {
        return new ArrayList<>(movables);
    }
    public int getNumberOfCells(){
        return gridCells.size();
    }
}
