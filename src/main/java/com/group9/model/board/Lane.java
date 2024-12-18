package com.group9.model.board;
import com.group9.model.entities.projectiles.Projectile;
import com.group9.model.entities.characters.attackers.AttackEntity;
import com.group9.model.entities.characters.defenders.DefenceEntity;
import com.group9.model.movement.Movable;

import java.util.ArrayList;
import java.util.List;

public class Lane {
    // List to store attackers, sorted by their lane progress
    private List<AttackEntity> attackEntities;

    // List to store grid cells, each holding information about positions and defenders
    private List<GridCell> gridCells;

    private List<Projectile> projectiles;

    private List<Movable> movables; // List of all movable entities (attackers and projectiles)


    // Constructor to initialize the lane with grid cells and empty entity lists
    public Lane(int laneSize) {
        this.attackEntities = new ArrayList<>();
        this.projectiles = new ArrayList<>();
        this.movables = new ArrayList<>();

        // Initialize grid cells based on lane size
        this.gridCells = new ArrayList<>();
        for (int cellIndex = 0; cellIndex < laneSize; cellIndex++) {
            this.gridCells.add(new GridCell(cellIndex));
        }
    }

    public List<Projectile> getProjectiles() {
        return this.projectiles;
    }
    public List<GridCell> getGridCells() {
        return gridCells;
    }

    public List<AttackEntity> getAttackers() {
        return this.attackEntities;
    }
    public List<Movable> getMovables() {
        return this.movables;
    }

    // Add an attacker to the lane and mark as movable
    public void addAttacker(AttackEntity attackEntity) {
        attackEntities.add(attackEntity);
        movables.add(attackEntity);
    }

    // Add a projectile to the lane and mark as movable
    public void addProjectile(Projectile projectile) {
        projectiles.add(projectile);
        movables.add(projectile);
    }

    // Remove an attacker from the lane and movables list
    public void removeAttacker(AttackEntity attackEntity) {
        attackEntities.remove(attackEntity);
        movables.remove(attackEntity);
    }

    // Remove a projectile from the lane and movables list
    public void removeProjectile(Projectile projectile) {
        projectiles.remove(projectile);
        movables.remove(projectile);
    }

    // Add a defender to the specified grid cell in the lane
    public void setDefender(DefenceEntity defender, int col) {
        GridCell cell = this.gridCells.get(col);
        cell.setDefender(defender);
    }

    public int getNumberOfCells(){
        return this.gridCells.size();
    }

    public DefenceEntity getDefenderAtIndex(int index) {
        GridCell gridcell = gridCells.get(index);
        if (gridcell != null) {
            return gridCells.get(index).getDefender(); // Return the defender, if present
        }
        return null;
    }

    // Sort attackers based on their progress in the lane using Insertion Sort
    // A margin is used to prevent swapping if the attackers progress is very close
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

    // Helper function to determine if an attacker should move ahead based on lane progress
    private boolean shouldMoveForward(AttackEntity current, AttackEntity key, double margin) {
        return current.getLaneProgress() + margin < key.getLaneProgress();
    }


    // Debugging method
    public void printAttackEntities() {
        System.out.println("Current Order of Attack Entities:");
        for (int i = 0; i < attackEntities.size(); i++) {
            AttackEntity entity = attackEntities.get(i);
            System.out.println("Index: " + i + ", Health: " + entity.getHealth() + ", Lane Progress: " + entity.getLaneProgress());
        }
        System.out.println("------------------------------------");
    }



}
