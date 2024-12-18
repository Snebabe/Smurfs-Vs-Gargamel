package com.group9.model.board;
import com.group9.model.entities.projectiles.Projectile;
import com.group9.model.entities.characters.attackers.AttackEntity;
import com.group9.model.entities.characters.defenders.DefenceEntity;
import com.group9.model.movement.Movable;

import java.util.ArrayList;
import java.util.List;

public class Lane {
    // List to store attackers, sorted by their xPosition
    private List<AttackEntity> attackEntities;
    // List to store gridcells
    private List<GridCell> gridCells;

    private List<Projectile> projectiles;

    private List<Movable> movables;


    public Lane(int laneSize) {
        this.attackEntities = new ArrayList<>();
        this.projectiles = new ArrayList<>();
        this.movables = new ArrayList<>();

        // Generate grid cells
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

    // Add an Attacker to the lane
    public void addAttacker(AttackEntity attackEntity) {
        attackEntities.add(attackEntity);
        movables.add(attackEntity);
    }

    public void addProjectile(Projectile projectile) {
        projectiles.add(projectile);
        movables.add(projectile);
    }

    public void removeAttacker(AttackEntity attackEntity) {
        attackEntities.remove(attackEntity);
        movables.remove(attackEntity);
    }

    public void removeProjectile(Projectile projectile) {
        projectiles.remove(projectile);
        movables.remove(projectile);
    }

    // Add a defender to the lane
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
            return gridCells.get(index).getDefender();
        }
        return null;
    }

    // Sort the attackers by lane progress using Insertion Sort with a margin
    // Margin is used to prevent defenders from swapping target because the attackers lane progress might
    // be very slightly different depending on the attacker's speed
    public void sortAttackers() {
        final double MARGIN = 0.001; // Adjust the margin as needed

        for (int i = 1; i < attackEntities.size(); i++) {
            AttackEntity key = attackEntities.get(i);
            int j = i - 1;

            // Move attackers that are ahead of the key back by one position
            while (j >= 0 && shouldMoveForward(attackEntities.get(j), key, MARGIN)) {
                attackEntities.set(j + 1, attackEntities.get(j));
                j--;
            }
            // Place the key in its correct position
            attackEntities.set(j + 1, key);
        }
    }

    // Helper function to compare with a margin
    private boolean shouldMoveForward(AttackEntity current, AttackEntity key, double margin) {
        // Move only if the current lane progress is strictly less than the key's by the margin
        return current.getLaneProgress() + margin < key.getLaneProgress();
    }


    // Debugging method to print the current state of attackEntities
    public void printAttackEntities() {
        System.out.println("Current Order of Attack Entities:");
        for (int i = 0; i < attackEntities.size(); i++) {
            AttackEntity entity = attackEntities.get(i);
            System.out.println("Index: " + i + ", Health: " + entity.getHealth() + ", Lane Progress: " + entity.getLaneProgress());
        }
        System.out.println("------------------------------------");
    }



}
